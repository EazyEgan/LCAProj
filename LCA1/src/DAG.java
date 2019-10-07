import java.util.*;


public class DAG {

    private final int V;
    private final ArrayList<Integer>[] adjacent;

    private final ArrayList<Integer>[] reverseAdj;


    public DAG(int V)
    {
        this.V = V;
        adjacent = (ArrayList<Integer>[]) new ArrayList[V];

        reverseAdj = (ArrayList<Integer>[]) new ArrayList[V];

        for (int v = 0; v < V; v++)
        {
            adjacent[v] = new ArrayList<Integer>();

            reverseAdj[v] = new ArrayList<Integer>();
        }
    }

   
}