import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q02CheapestFlightWithinKSteps {
    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};

        int src = 0, dest = 3, k = 1;
        System.out.println(cheapestFlight(n, flights, src, dest, k));
    }

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

    static void createGraph(int flights[][], ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] flight : flights){
            int src = flight[0];
            int dest = flight[1];
            int weight = flight[2];

            graph[src].add(new Edge(src, dest, weight));
        }
    }

    static int cheapestFlight(int n, int flights[][], int src, int dest, int k){
        ArrayList<Edge>[] graph = new ArrayList[n];
        createGraph(flights, graph);

        int dist[] = new int[n];
        for(int i = 0; i < n; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Info> q = new LinkedList<>();

        q.add(new Info(src, 0, 0));

        while (!q.isEmpty()) {
            Info current = q.remove();

            if(current.stops > k){
                break;
            }

            for(Edge e : graph[current.vertices]){
                int u = e.src;
                int v = e.dest;
                int w = e.weight;

                if(current.cost+w < dist[v] && current.stops <= k){
                    dist[v] = current.cost+w;
                    q.add(new Info(v, dist[v], current.stops+1));
                }
            }
        }

        if(dist[dest] == Integer.MAX_VALUE){
            return -1;
        } else {
            return dist[dest];
        }
    }

    
    static class Info {
        int vertices;
        int cost;
        int stops;

        public Info(int v, int c, int s){
            this.vertices = v;
            this.cost = c;
            this.stops = s;
        }
        
    }
}
