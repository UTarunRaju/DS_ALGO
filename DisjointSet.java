package Anjaneya;

public class DisjointSet {
    
    private int rank[];
    private int parent[];
    public DisjointSet(int size){
        rank=new int[size];
        for(int i=0;i<size;i++){
            rank[i]=0;
        }
        parent=new int[size];
        for(int i=0;i<size;i++){
            parent[i]=i;
        }
    }

    public int find(int a){
        if(parent[a]==a){
            return a;
        }
      
        // return find(parent[a]);
        // its normal method of long recursion

        return parent[a]=find(parent[a]);
        // its path compression optimisation its stores leader in parents
    

    } 

    public void union(int a,int b){
        int leaderA=find(a);
        int leaderB=find(b);

        if(rank[leaderA]==rank[leaderB]){
            parent[leaderB]=leaderA;
            rank[leaderA]++;
        }else if(rank[leaderA]>rank[leaderB]){
            parent[leaderB]=leaderA;
        }else{
            parent[leaderA]=leaderB;
        }
    }


}
