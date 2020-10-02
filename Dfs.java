
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

class Graph {
    private int v;
    public List<Integer> adj[];
    public Graph(int n){
        v= n;
      adj = new ArrayList[n];
      for(int i=0;i<adj.length;i++){
          adj[i] = new ArrayList<Integer>();
      }
    }
    public void addEdge(int u,int v){
        adj[u].add(v);
    }
    public void dfs(int u,boolean vis[]){
        vis[u] = true;
        for(int v : adj[u]){
          if(!vis[v]){
              dfs(v,vis);
          }
        }
    }
}

public class TestClass {
    
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Graph g = new Graph(n+1);
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u,v);
            g.addEdge(v,u);
        }
        int origin = sc.nextInt();
        boolean[] vis = new boolean[n+1];
        Arrays.fill(vis,false);
        g.dfs(origin,vis);
        int cnt = 0;
        for(int i=1;i<=n;i++){
            if(vis[i]==false)
            cnt++;
        }
        System.out.println(cnt);;
    }

}
