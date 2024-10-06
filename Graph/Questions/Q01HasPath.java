// returns true if there a path exist from src to dest

import java.util.ArrayList;

public class Q01HasPath {

    static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));

        graph[6].add(new Edge(6, 5, 1));

    }

    static boolean hashPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited){
        if(src == dest){
            return true;
        }

        visited[src] = true;

        for(Edge e : graph[src]){
            if(!visited[e.destination] && hashPath(graph, e.destination, dest, visited)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int vertices = 7;
        ArrayList<Edge>[] graph = new ArrayList[vertices];
        boolean[] visited = new boolean[graph.length];
        createGraph(graph);
        int src = 0;
        int dest = 5;

        System.out.print(hashPath(graph, src, dest, visited));
    }
}

class Edge{
    int src;
    int destination;
    int weight;

    public Edge(int src, int destination, int weight){
        this.src = src;
        this.destination = destination;
        this.weight = weight;
    }
}
