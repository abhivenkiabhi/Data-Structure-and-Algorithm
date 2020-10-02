#include <bits/stdc++.h>
using namespace std;
vector<vector<int> > adj;
int level[100005];
bool vis[100005];
void bfs(int head){
    queue<int> qu;
    qu.push(head);
    while(!qu.empty()){
        int u = qu.front();
        qu.pop();
        vis[u]= true;
        for(int i=0;i<(int)adj[u].size();i++){
            int v = adj[u][i];
            if(!vis[v]){
                level[v]= 1+level[u];
                qu.push(v);
            }
        }
    }
}
int main()
{
    int n;
    cin>>n;
   
    adj.resize(n+1);
    int N = n-1;
    while(N--)
    {
        int x,y;
        cin>>x>>y;
        adj[x].push_back(y);
        adj[y].push_back(x);
    }
    int x;
    cin>>x;
    level[1]=1;
    bfs(1);
    int cnt =0;
    for(int i=1;i<=n;i++){
        if(level[i]==x){
            cnt++;
        }
    }
    
   cout<<cnt<<endl;
   return 0;
   
}
