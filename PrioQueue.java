package Anjaneya;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;



public class PrioQueue {

    static class num implements Comparable<num>{
        int n;
        num(int n){
            this.n=n;
        }

        @Override
        public int compareTo(num b){
            return this.n-b.n;
        }
    }
    static class M implements Comparable<M>{
        int idx;int data;
        M(int idx,int data){
            this.idx=idx;this.data=data;
        }

        @Override
        public int compareTo(M x){
            return x.data-this.data;
        }
    }

    public int minMum(int val[][],int i,int j,boolean check[][],int prev ,int sum,int X,int Y){

        if(check[i][j]==true||i>=X||i<0||j>=Y||j<0){return Integer.MAX_VALUE;}
        if(sum<Math.abs(val[i][j]-prev)){sum=Math.abs(val[i][j]-prev);}

        if(i==X-1&&j==Y-1){return sum;}
        check[i][j]=true;

        int up=minMum(val,i,j-1,check,val[i][j],sum,X,Y);
        int down=minMum(val,i,j+1,check,val[i][j],sum,X,Y);
        int left=minMum(val,i-1,j,check,val[i][j],sum,X,Y);
        int right=minMum(val,i+1,j,check,val[i][j],sum,X,Y);

        return Math.min(Math.min(left, right),Math.min(up,down));
    }
    public int[] maxSlidingWindow(int[] N, int k) {
        LinkedList<Integer> A=new LinkedList<>();

            int R[]=new int[N.length-k+1];
            int j=0;
           
                for(int i=0;i<N.length;i++){

            int m=A.size()-1;            
            while(!A.isEmpty()&&m>=0){
                 if(N[A.get(m)]<N[i]){
                    A.removeLast();
                 }  
            }
                       
                       
                        A.addLast(i);
                    

                    if(i>=k-1){
                            R[j]=N[A.getFirst()];
                            j++;
                    }

                }
            return R;                   
    }


    public static long connectRopes(int[] arr)
    {
        PriorityQueue<Long> PQ= new PriorityQueue<>();
        for(int i=0;i<arr.length;i++){
             PQ.add((long)arr[i]);   
        }

        long sum=0;
        long val=0;
        while(PQ.size()!=1){
            sum*=2;
            val=PQ.remove()+PQ.remove();
            sum+=val;
            PQ.add(val);
        }
        
        return sum;
    }
    static class dist implements Comparable<dist>{
        int val;
        int key;

        dist(int key,int val){
            this.key=key;
            this.val=val;
        }

        @Override
        public int compareTo(dist D2){
            return this.val-D2.val;
        }
    }

    
    public static int minCostConnectPoints(int[][] P) {
        
        PriorityQueue<dist> PQ=new PriorityQueue<>();
    
        HashMap<Integer,pair> HM= new HashMap<>();
          boolean A[]=new boolean[P.length+1];

          int sum=0;
          
          for(int i=1;i<P.length;i++)
          {
            PQ.add(new dist(i,Math.abs(P[0][0]-P[i][0])+Math.abs(P[0][1]-P[i][1])));
            HM.put(i,new pair(P[i][0],P[i][1]));
          }

          int n=PQ.peek().key;
          pair m=HM.remove(n);
          A[n]=true;
          sum+=PQ.peek().val;
          PQ.remove();
          while(true){

            for(int i:HM.keySet()){
                PQ.add(new dist(i,Math.abs(m.a-HM.get(i).a)+Math.abs(m.b-HM.get(i).b)));
            }

            while(true){
               if(!A[PQ.peek().key]){
                    sum+=PQ.peek().val;
                    A[PQ.peek().key]=true;    
                    n=PQ.peek().key;
                    m=HM.remove(n);
                    break;               
               }else{
                    PQ.remove();
               }
            }

            if(HM.isEmpty()){
                break;
            }

          }
       return sum;
    }

    static class Path implements Comparable<Path>{
        String from;
        String to;
        int weight;
        Path(String from,String to,int weight){
            this.to=to;
            this.from=from;
            this.weight=weight;
        }

        @Override
        public  int compareTo(Path b){
            if(this.to.equals(b.from)){
                this.weight=b.weight+1;
                return 1;
            }else if(this.from.equals(b.to)){
                b.weight=this.weight+1;
                return -1;
            }
            return 0;
        }
    }

    static class Point implements Comparable<Point>{
        
        int id;
        int x;
        int y;
        int dist;

        Point(int id,int x,int y){
            this.id=id;
            this.x=x;
            this.y=y;
            dist=x*x+y*y;
        }

        @Override
        public int compareTo(Point P2){
            return this.dist-P2.dist;
        }
    }
    static class Student implements Comparable<Student>{
        String Name;
        int rank;

        public Student(String Name,int rank){
                this.Name=Name;
                this.rank=rank;
        }

        @Override
        public int compareTo(Student b){
                 int r=this.rank-b.rank;
                 if(r!=0){
                    return r;
                 }else{
                    return this.Name.charAt(0)-b.Name.charAt(0);
                 }
        }
}

    public static void main(String[] args) {
        PriorityQueue<Path> pq=new PriorityQueue<>(Comparator.reverseOrder());
        
         pq.add(new Path("DEL", "GOA",0));
        pq.add(new Path("CHA","BAN",0));
         
            pq.add(new Path("GOA", "CHA",0));
         pq.add(new Path("MUM", "DEL",0));
      

        while(!pq.isEmpty()){
        System.out.println(pq.peek().from+" -> "+pq.peek().to);
        pq.remove();
        }

        //Note
        // * compareTo() function must return comparision b/w the two object 
        //   so each object shoud be comparable to every other object.
        // * bigger  the integer returned best is the object (this.) compared 
        //   to the object passed in the function.
        // * The order is always in Assending order, Comparator.reverseOrder()
        //   can be passed inside PriorityQueue for decending order.
        
    }
}
