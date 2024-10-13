// creats a minimum spanning tree which is a subgraph of undirected weighted graph, where the subgraph 
// will not have any cycle,
// the total weight will be minimum
// wiil be connected to all vertices 

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * G08PrimsAlgorithm
 */
public class G08PrimsAlgorithm {

    static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.weight = w;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        
        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));


    }

    static class Pair implements Comparable<Pair>{
        int vertices;
        int cost;

        public Pair(int v, int c){
            this.vertices = v;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }

    static void prims(ArrayList<Edge>[] graph){
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> pairs = new PriorityQueue<>();
        
        pairs.add(new Pair(0, 0));
        int finalCost = 0;
        while (!pairs.isEmpty()) {
            Pair current = pairs.remove();
            if(!visited[current.vertices]){
                visited[current.vertices] = true;
                finalCost+= current.cost;
                for(Edge e : graph[current.vertices]){
                    pairs.add(new Pair(e.dest, e.weight));
                }
            }
        }

        System.out.println("Final Weight: "+finalCost);

    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        prims(graph);
    }
}