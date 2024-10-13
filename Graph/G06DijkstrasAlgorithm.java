//find shortest path to all vertices from src

import java.util.ArrayList;
import java.util.PriorityQueue;

public class G06DijkstrasAlgorithm {
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

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        
        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));

    }

    static class Pair implements Comparable<Pair>{
        int node;
        int path;

        public Pair(int n, int p){
            this.node = n;
            this.path = p;
        }

        @Override
        public int compareTo(Pair o) {
            return this.path - o.path;
        }
    }

    //O(V+ElogV) for priority queue
    //O(V^2) for without priority queue
    static void dijkstra(ArrayList<Edge>[] graph, int src){
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for(int i = 0; i < graph.length; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Pair(src, 0));

        while(!priorityQueue.isEmpty()){
            Pair current = priorityQueue.remove();

            if(!visited[current.node]){
                visited[current.node] = true;

                for(Edge e : graph[current.node]){
                    int u = e.src;
                    int v = e.dest;
                    int weight = e.weight;

                    if(dist[u]+weight < dist[v]){
                        dist[v] = dist[u]+weight;
                        priorityQueue.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        for(int i : dist){
            System.out.print(i+" ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        int v = 6;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        int src = 0;
        dijkstra(graph, 0);
    }


}
