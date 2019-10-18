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
    public boolean addEdge(int v, int w)
    {
        if(v >= this.V || w >= this.V || v < 0 || w < 0){
            return false;
        }


        if(v != w && !hasPath(w, v) && !adjacent[v].contains(w)){
            adjacent[v].add(w);
            reverseAdj[w].add(v);
            return true;
        }
        else{
            return false;
        }
    }

    public int V(){
        return V;
    }

    public ArrayList<Integer> adjacent(int v)
    { return adjacent[v]; }

    public ArrayList<Integer> reverseAdj(int v)
    { return reverseAdj[v]; }

    public boolean hasPath(int x, int y){
        DirectedDFS dfsObj = new DirectedDFS(this, x);
        return dfsObj.visited(y);
    }

    public ArrayList<Integer> lowestCommonAncestor(int x, int y)
    {
        ArrayList<Integer> lcas = new ArrayList<Integer>();
        int currentMaxDist = Integer.MAX_VALUE;
        if(x==y || x>=this.V || y>=this.V || x<0 || y<0) { return lcas; } //If invalid input return empty bag.

        DirectedDFS dfsObject = new DirectedDFS(this, x);

        dfsObject.reverseDfs(this, x);
        int xDist, yDist;

        for(int v = 0; v < this.V; v++)
        {

            if(dfsObject.revVisited(v) && hasPath(v, y))
            {
                xDist = getDistance(v, x);
                yDist = getDistance(v, y);

                if(Integer.max(xDist, yDist) < currentMaxDist)
                {
                    lcas = new ArrayList<Integer>();
                    lcas.add(v);
                    currentMaxDist = Integer.max(xDist, yDist);
                }
                else if(Integer.max(xDist, yDist) == currentMaxDist)
                {
                    lcas.add(v);
                    currentMaxDist = Integer.max(xDist, yDist);
                }
            }
        }
        return lcas;
    }

    private int getDistance(int x, int target)
    {

        if( x == target) { return 0; }
        else {
            Queue<Integer> q = new LinkedList<Integer>();
            int[] distTo = new int[this.V];
            boolean[] marked = new boolean[this.V];

            for (int v = 0; v < this.V(); v++)
            {   distTo[v] = Integer.MAX_VALUE;}

            distTo[x] = 0;
            marked[x] = true;
            q.add(x);

            while (!q.isEmpty()) {
                int v = q.remove();
                for (int w : this.adjacent(v)) {
                    if (!marked[w]) {

                        distTo[w] = distTo[v] + 1;
                        marked[w] = true;

                        q.add(w);
                    }
                }
            }

            return distTo[target];
        }
    }

    private class DirectedDFS
    {
        private boolean[] marked;
        private boolean[] revMarked;

        public DirectedDFS(DAG G, int s)
        {
            marked = new boolean[G.V()];
            revMarked = new boolean[G.V()];
            dfs(G, s);
        }

        private void dfs(DAG G, int v)
        {
            marked[v] = true;
            for (int w : G.adjacent(v))
                if (!marked[w]) dfs(G, w);
        }

        private void reverseDfs(DAG G, int v)
        {
            revMarked[v] = true;
            for (int w : G.reverseAdj(v))
                if (!revMarked[w]) reverseDfs(G, w);
        }

        public boolean visited(int v)
        { return marked[v]; }

        public boolean revVisited(int v)
        { return revMarked[v]; }
    }

   
}