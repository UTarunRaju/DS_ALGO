package Anjaneya;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graphs {

    static class pair implements Comparable<pair>{
        int Ver;
        int Dis;

        pair(int Ver,int Dis){
            this.Dis=Dis;
            this.Ver=Ver;
        }

        @Override
        public int compareTo(pair X){
            return this.Dis-X.Dis;
        }

    }
    
    static class Edge implements Comparable<Edge>{
        int src ;
        int des;
        int wgt;
        
        Edge(int src,int des,int wgt){
            this.src=src;
            this.des=des;
            this.wgt=wgt;
        }

        @Override
        public int compareTo(Edge A){
            return this.wgt-A.wgt;
        }
    }

    static class graphs{
        private Edge temp;
        ArrayList<Edge>[] EdgeList;
        int V;
        graphs(int V){
            this.V=V;
            EdgeList=new ArrayList[V];
        }

        public void add(int src,int des,int wgt){
            temp=new Edge(src, des, wgt);
            if(EdgeList[temp.src]==null){
                EdgeList[temp.src]=new ArrayList<>();
            }
            EdgeList[temp.src].add(temp);
        }

        public ArrayList<Edge> getListOfEdges(int V){
            return EdgeList[V];
        }

        public void bfs(){
            boolean check[]=new boolean[V];
            for(int k=0;k<V;k++){
                if(!check[k]){
                    bfsutil(k, check);
                }
            }
        }

        private void bfsutil(int k,boolean check[]){

  
            Queue <Integer> Q=new LinkedList<>();
            Q.add(k);
            check[k]=true;
            int temp;
            while(!Q.isEmpty()){
                temp=Q.peek();
                for(Edge i:EdgeList[temp]){
                        if(!check[i.des]){
                            Q.add(i.des);
                            check[i.des]=true;
                        }
                }
                System.out.println(Q.poll());
            }

        }

        public void dfs(){
            boolean []check=new boolean[V];
            System.out.println(0); check[0]=true;
            for(int j=0;j<V;j++){
                dfsutil(EdgeList[j],check);     
            }
            
        }
   
        private void dfsutil(ArrayList<Edge> EL, boolean[] check) { 

            for(Edge k:EL){
                if(!check[k.des]){
                    check[k.des]=true;
                    System.out.println(k.des);
                    dfsutil(EdgeList[k.des],check);
                }
            } 
        }

        public boolean hasPath(int src,int dest){
            boolean check[]=new boolean[V];
            check[src]=true;
            return hp(src,dest,check);
        }

        private boolean hp(int src,int dest,boolean check[]){

            if(src==dest) return true;

            for(Edge i:EdgeList[src]){
                if(!check[i.des]){
                    check[i.des]=true;
                    if(hp(i.des,dest,check)){
                        return true;
                    }
                }
            }

            return false;
        }       
    
    }

    public static void TopologicalSort_DFS(graphs G){
         boolean check[]=new boolean[6];
        Stack<Integer> Q= new Stack<>();

        for(int j=0;j<G.V;j++){
            if(!check[j]){
                TS_DFS(G,j,check,Q);
            }
        }

        while(!Q.isEmpty()){
            System.out.println(Q.pop());
        }
    }

    public static void TS_DFS(graphs A,int i,boolean[] check,Stack<Integer> Q){
        check[i]=true;
        if(A.EdgeList[i]==null){Q.add(i); return;  }

        for(Edge j:A.EdgeList[i]){

            if(!check [j.des]){                
                TS_DFS(A, j.des, check, Q);
            }       
        }
        Q.add(i);return;
    }

    public static void TopologicalSort_BFS(graphs G){
       int in_Deg[]=new int[G.V];
       
       for(int i=0;i<G.V;i++){
                if(!(G.EdgeList[i]==null)){
                     for(Edge j:G.EdgeList[i]){
                            in_Deg[j.des]++;
                        }
                }
              
       }
       
       Queue<Integer> Q=new LinkedList<>();  
       
       for(int i=0;i<G.V;i++){
            if(in_Deg[i]==0){
                Q.add(i);
            }
       }
       
       int temp=0;
       while(!Q.isEmpty()){
          temp=Q.poll();
          System.out.println(temp);
          if(!(G.EdgeList[temp]==null)){
                    for(Edge j:G.EdgeList[temp]){
                        in_Deg[j.des]--;
                        if(in_Deg[j.des]==0){
                            Q.add(j.des);
                        }
                    }
            }
       }
       
       
    }

    public static void All_Path_Source_Dest(int s,int d, graphs G){
        Queue<Integer>Q= new LinkedList<>();
        Q.add(s);
        apsd(s, d, G, Q);
    }

    public static void apsd(int s,int d,graphs G,Queue<Integer>Q){
        if(s==d){
           System.out.println(Q);
        }
           
        if(!(G.EdgeList[s]==null)){
            for(Edge i:G.EdgeList[s]){
                Q.add(i.des);
                apsd(i.des, d, G, Q);
                Q.remove(i.des);
            }
        }        
    }
    
    public static void ShortestPath_frome_Source(graphs G){
        //dijkstra's algorithm
        int Dist[]=new int [G.V];
        boolean check[]=new boolean[G.V];
        PriorityQueue<pair> PQ=new PriorityQueue<>();
        PQ.add(new pair(0, 0));
        pair temp;
        while(!PQ.isEmpty()){
            temp=PQ.poll();
            if(G.EdgeList[temp.Ver]==null||check[temp.Ver]){continue;}
            check[temp.Ver]=true;
            for(Edge i:G.EdgeList[temp.Ver]){
                if(Dist[i.des]==0){
                    Dist[i.des]=Dist[i.src]+i.wgt;
                }else{
                    Dist[i.des]=Math.min(Dist[i.des],Dist[i.src]+i.wgt);
                }
                PQ.add(new pair(i.des, Dist[i.des]));
            }
        }

        for(int j=0;j<G.V;j++){
            System.out.println(Dist[j]);
        }
    } 

    public static graphs find_MST(graphs G){

        //Prim's Algorithm
        PriorityQueue<Edge> PQ= new PriorityQueue<>();
        graphs R= new graphs(G.V);
        boolean check[]= new boolean[G.V];
        check[0]=true;
        int count=0;
        int back=0;
        Edge temp;
        while(count!=G.V){
            for(Edge e:G.EdgeList[back]){
                if(!check[e.des]){
                    PQ.add(e);
                }
            }
            if(PQ.isEmpty()){
                return R;
            }

            temp=PQ.poll();
            while(!PQ.isEmpty()){               
                if(!check[temp.des]){
                    break;
                }
                temp=PQ.poll();
            }
            if(check[temp.des]){
                return R;
            }

            if(R.EdgeList[temp.src]==null){
               R.EdgeList[temp.src]=new ArrayList<>();
            }
            R.EdgeList[temp.src].add(temp);
            if(R.EdgeList[temp.des]==null){
               R.EdgeList[temp.des]=new ArrayList<>();
            }
            R.EdgeList[temp.des].add(new Edge(temp.des, temp.src, temp.wgt));
            back=temp.des;
            count++;
            check[temp.des]=true;

        }
        return R;
    }

    public static void Bellman_Ford(graphs G){

        int v=G.V;
        int R[]=new int [v];
        for(int j=1;j<v;j++){
            R[j]=Integer.MAX_VALUE;
        }
        
        for(int i=1;i<v;i++){
            for(int j=0;j<v;j++){
                if(G.EdgeList[j]==null){continue;}
                for(Edge e:G.EdgeList[j]){
                    if(R[e.src]==Integer.MAX_VALUE){
                        continue;
                    }

                    if(R[e.des]==Integer.MAX_VALUE){
                        R[e.des]=R[e.src]+e.wgt;
                    }
                    else{
                        if(R[e.des]>R[e.src]+e.wgt){
                            R[e.des]=R[e.src]+e.wgt;
                        }

                    }
                }

            }
        }

        for(int i:R){
            System.out.print(i+" ");
        }
    }

    public static int  Kruskals_Algorithm_MST_DisjointSet(PriorityQueue<Edge>PQ,int V){
        DisjointSet DS=new DisjointSet(V);
        int R=0;
        int count=0;//important no of edges
        Edge temp;
        while (count!=V-1) {
            temp=PQ.poll();
            if(DS.find(temp.src)==DS.find(temp.des)){
                continue;
            }
            else{
                DS.union(temp.src,temp.des);
                count++;
                R+=temp.wgt;
            }
        }

        return R;

    }

    public static void StronglyConnectedComponents(graphs g){
        //Kosaraju's Algorithm
        
        boolean visited[]=new boolean[g.V];
        Stack <Integer>S= new Stack<>();
        for(int i=0;i<g.V;i++){
            
            if(!visited[i])sscTopSort(i, g, S, visited);
        }
      
        
        graphs transpose= new graphs(g.V);

        for(int j=0;j<g.V;j++){
            if(g.EdgeList[j]==null){
                continue;
            }
            for(Edge e:g.EdgeList[j]){
                transpose.add(e.des, e.src, 0);   
            }
        }
       
        visited=new boolean[g.V];
        Queue<Integer> Q= new LinkedList<>();
        int temp;
        while(!S.isEmpty()){
            temp=S.pop();
            if(!visited[temp]){
                Q=new LinkedList<>();
                sscDFS(temp,visited,transpose,Q);
            }
        }
    }
    
    private static void sscTopSort(int r,graphs g,Stack<Integer>Q,boolean check[]){

        check[r]=true;
        if(g.EdgeList[r]==null){
                    Q.add(r);
                return;
        }
        for(Edge e:g.EdgeList[r]){
            if(!check[e.des]){
                sscTopSort(e.des, g, Q, check);
            }
        }
        Q.add(r);
        return;
    }

    private static void sscDFS(int r,boolean visited[],graphs g,Queue<Integer> R){

        R.add(r);
        visited[r]=true;
        if(g.EdgeList[r]==null){
            System.out.println(R);
            return;
        }
        
        int k=0;
        for(Edge e:g.EdgeList[r]){
            if(!visited[e.des]){
                sscDFS(e.des, visited, g, R);
                k++;
            }
        }

        if(k==0){
            System.out.println(R);
            return;
        }
    }
   
    public static void findBridges(graphs g){
            boolean visited[]=new boolean[g.V];
            for(int i=0;i<g.V;i++){
                if(!visited[i]){
                     findBridges_recurs(-1,0,g,visited,new int[g.V],new int[g.V],0);
                }
            }
       
    }

    private static void findBridges_recurs(int back,int i,graphs g,boolean visited[],int dt[],int low[],int time){
        visited[i]=true;
        time++;
        dt[i]=time;
        low[i]=time;
        if(g.EdgeList[i]==null){
            return;
        }

        for(Edge e:g.EdgeList[i]){
            if(e.des==back){
                continue;
            }
            if(visited[e.des]){
                low[i]=Math.min(low[i],dt[e.des]);
            }else{
                findBridges_recurs(i,e.des, g, visited, dt, low, time);
                low[i]=Math.min(low[i], low[e.des]);
                if(dt[i]<low[e.des]){
                    System.out.println(i+" "+e.des);
                }
            }
        }
    }
   
    public static void findArticulationPoint(graphs g){
        boolean visited[]=new boolean[g.V];
        boolean found[]=new boolean[g.V];
            for(int i=0;i<g.V;i++){
                if(!visited[i]){
 findArticulationPoint_recurs(-1,0,g,visited,new int[g.V],new int[g.V],0,found);                }
            }
       
    }

    public static void findArticulationPoint_recurs(int back,int i,graphs g,boolean visited[],int dt[],int low[],int time,boolean found[]){
        time++;
        visited[i]=true;
        dt[i]=time;
        low[i]=time;

        if(g.EdgeList[i]==null){
            return;
        }

        int childrens=0;
        
        for(Edge e:g.EdgeList[i]){
            if(e.des==back)continue;
            if(visited[e.des])low[i]=Math.min(low[i], dt[e.des]);
            else{
                findArticulationPoint_recurs(i,e.des,g,visited,dt,low,time,found);
                if(back!=-1&&dt[i]<=low[e.des]){
                       if(!found[i]) {System.out.println(i); found[i]=true;}
                }
                if(back==-1){
                    childrens++;
                }
            }
        }

        if(childrens>1){
            if(!found[i]){ System.out.println(i);found[i]=true;}
        }
    }
    public static void main(String[] args) {
              
       graphs g= new graphs(5);
       g.add(1, 0, 0);
       g.add(0, 1, 0);
       g.add(1, 2, 0);
       g.add(2, 1, 0);
       g.add(0, 2, 0);
       g.add(2, 0, 0);
       g.add(0, 3, 0);
       g.add(3, 0, 0);
       g.add(3, 4, 0);
       g.add(4, 3, 0);
      
       
       findArticulationPoint(g);
    //    TopologicalSort_DFS(g);
      
    }

  

}
