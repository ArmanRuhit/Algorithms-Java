import java.util.ArrayList;

/**
 * Example
 */
public class G01Example {

    public static void main(String[] args) {
        int vertices = 5;
        ArrayList<Edge>[] graph = new ArrayList[vertices];

        for(int i = 0; i < vertices; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 5));

        graph[1].add(new Edge(1, 0, 5));
        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 3));

        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[2].add(new Edge(2, 4, 4));

        graph[3].add(new Edge(3, 1, 3));
        graph[3].add(new Edge(3, 2, 1));

        graph[4].add(new Edge(4, 2, 2));


        //2's neighbour
        System.out.println("2's neighbour");
        for(Edge e : graph[2]){
            System.out.println(e.destination);
        }
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


