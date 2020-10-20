public class Solution {
    class Edge implements Comparable<Edge> {
        public int v,w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
        public int compareTo(Edge other) {
            return this.w - other.w;
        }
    }
    
    public int prims(int u, int A, Map<Integer, List<Edge>> adj) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] marked = new boolean[A + 1];
        Arrays.fill(marked, false);
        int minimumCost = 0;
        pq.add(new Edge(u, 0));
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int v = edge.v;
            if(marked[v])
             continue;
            minimumCost += edge.w;
            marked[v] = true;
            for(Edge edg : adj.get(v)) {
            int x = edg.v;
            int w = edg.w;
            if(marked[x]==false)
             pq.add(new Edge(x, w));
            }
        }
        return minimumCost;
    }
 // A is the number of vertices and B contains edge(u,v,w), 
 // we have to return the minimumCost by including all the vertices
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for(ArrayList<Integer> list : B) {
            if (!adj.containsKey(list.get(0))) {
                adj.put(list.get(0), new ArrayList<Edge>());
            }
            if (!adj.containsKey(list.get(1))) {
                adj.put(list.get(1), new ArrayList<Edge>());
            }
            adj.get(list.get(0)).add(new Edge(list.get(1), list.get(2)));
            adj.get(list.get(1)).add(new Edge(list.get(0), list.get(2)));
        }
        int minimumCost = 0;
        minimumCost = prims(1, A, adj);
        return minimumCost;
    }
}
