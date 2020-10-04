
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

class Graph {
    private int v;
    List<Integer> adj[];
    public Graph(int v){
        this.v = v;
        adj = new ArrayList[v];
        for(int i=0;i<adj.length;i++)
        adj[i] = new ArrayList<Integer>();
    }
    public void addEdge(int u,int v){
        adj[u].add(v);
    }
    public void bfs(int u,boolean vis[],int level[]){
        level[u] = 1;
      LinkedList<Integer> que = new LinkedList<>();
      que.add(u);
      vis[u]=true;
      while(que.size()!=0){
           u = que.poll();
           for(int v : adj[u]){
                if (!vis[v]) 
                { 
                    vis[v] = true;
                    level[v] = level[u] + 1; 
                    que.add(v); 
                } 
            }  
       }
      }
    public static void main(String args[] ) throws Exception {
      Scanner sc = new Scanner(System.in);
      int N = sc.nextInt();
      Graph G = new Graph(N+1);
      for(int i=0;i<N-1;i++){
          int u  = sc.nextInt();
          int v = sc.nextInt();
          G.addEdge(u,v);
          G.addEdge(v,u);
      }
      int x = sc.nextInt();
      boolean[] vis = new boolean[N+1];
      int[] level = new int[N+1];
      Arrays.fill(vis,false);
      Arrays.fill(level,0);
      G.bfs(1,vis,level);
      int cnt =0;
      for(int i=1;i<=N;i++){
          if(level[i]==x)
          cnt++;
      }
      System.out.println(cnt);

    }
}
