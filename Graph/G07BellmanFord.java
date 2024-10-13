//find shortest path to all vertices from src for negative edges

import java.util.ArrayList;

public class G07BellmanFord {
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

        graph[1].add(new Edge(1, 2, -4));
        
        graph[2].add(new Edge(2, 3, 2));

        graph[3].add(new Edge(3, 4, 4));

        graph[4].add(new Edge(4, 1, -1));

    }

    static void createGraphUsingEdge(ArrayList<Edge> edges){
        
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 4));

        edges.add(new Edge(1, 2, -4));
        
        edges.add(new Edge(2, 3, 2));

        edges.add(new Edge(3, 4, 4));

        edges.add(new Edge(4, 1, -1));

    }

    //O(V*E)
    static void bellmanFord(ArrayList<Edge>[] graph, int src){
        int[] dist = new int[graph.length];

        for(int i = 0; i < dist.length; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        int V = graph.length;

        for(int i = 0; i < V-1; i++){
            for(int j = 0; j < graph.length; j++){
                for(Edge e : graph[j]){
                    int u = e.src;
                    int v = e.dest;
                    int w = e.weight;

                    if(dist[u] != Integer.MAX_VALUE && dist[u]+w < dist[v]){
                        dist[v] = dist[u] + w;
                    }

                }
            }
        }

        System.out.println("Using Graph Array");
        for(int i : dist){
            System.out.print(i+" ");
        }
        System.out.println();
    }


    //O(V*E)
    static void bellmanFordUsingEdge(ArrayList<Edge> edges, int src, int V){
        int[] dist = new int[V];

        for(int i = 0; i < dist.length; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }


        for(int i = 0; i < V-1; i++){
            for(Edge e : edges){
                int u = e.src;
                int v = e.dest;
                int w = e.weight;

                if(dist[u] != Integer.MAX_VALUE && dist[u]+w < dist[v]){
                    dist[v] = dist[u] + w;
                }

            }
        }

        System.out.println("Using edge arraylist");
        for(int i : dist){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int v = 5;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        int src = 0;
        bellmanFord(graph, src);

        // Alternative approach
        ArrayList<Edge> edges = new ArrayList<>();
        createGraphUsingEdge(edges);
        bellmanFordUsingEdge(edges, src, v);
    }
}
