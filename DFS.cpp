#include<bits/stdc++.h>
using namespace std;
vector<vector<int> > adj;
bool vis[100005];
void dfs(int x){
    vis[x]= true;
    for(int i=0;i<adj[x].size();i++){
        int u = adj[x][i];
        if(!vis[u])
           dfs(u);
    }
}
int main(){
    int N,M;
    cin>>N>>M;
    adj.resize(N+1);
    memset(vis,false, N+1);
    while(M--){
        int u,v;
        cin>>u>>v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    int x;
    cin>>x;
    dfs(x);
    int cnt =0;
    for(int i=1;i<=N;i++){
        if(!vis[i])
        cnt++;
    }
    cout<<cnt<<endl;
}
