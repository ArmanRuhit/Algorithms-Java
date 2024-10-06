import java.util.*;;

public class G04TopologicalSort {
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));

    }

    static void calculateInDegree(ArrayList<Edge>[] graph, int[] inDegree){
        for(int i=0; i<graph.length; i++){
            int vertices = i;
            for(Edge e : graph[vertices]){
                inDegree[e.dest]++;
            }
        }
    }

    static void topologicalSort(ArrayList<Edge>[] graph){
        int[] inDegree = new int[graph.length];
        
        calculateInDegree(graph, inDegree);

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        //bfs
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            System.err.print(curr+" ");

            for(Edge e : graph[curr]){
                inDegree[e.dest]--;
                if(inDegree[e.dest] == 0){
                    queue.add(e.dest);
                }
            }
        }


        System.out.println();
    }

    public static void main(String[] args) {
        int vertices = 6;
        ArrayList<Edge>[] graph = new ArrayList[vertices];
        createGraph(graph);
        topologicalSort(graph);
    }
}
