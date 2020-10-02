#include<bits/stdc++.h>
using namespace std;
vector<vector<pair<int,int> > > adj;

bool marked[100005];
long long int prims(int u){
    
    long long int minimumCost = 0;
    priority_queue<pair<int,int> >pq;
    pq.push(make_pair(0,u));
    while(!pq.empty()){
        
        pair<int,int> p = pq.top();
        pq.pop();
        int v = p.second;
        if(marked[v])
          continue;
        minimumCost +=(-1*p.first);
        marked[v] = true;
        for(int i=0;i<(int)adj[v].size();i++){
            
            int x = adj[v][i].second;
            int w = adj[v][i].first;
            if(marked[x]==false)
             pq.push(make_pair(-1*w,x));
        }
    }
    return minimumCost;
}
int main(){
    int N,M;
    cin>>N>>M;
    adj.resize(N+1);
    while(M--){
        int u,v,w;
        cin>>u>>v>>w;
        adj[u].push_back(make_pair(w,v));
        adj[v].push_back(make_pair(w,u));
    }
    int minimumCost = 0;
    minimumCost = prims(1);
    cout<<minimumCost<<endl;
}
