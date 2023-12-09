package Anjaneya;


import java.util.*;
public class Heaps {    

   

    static class heap{
         ArrayList<Integer> R;
         int IDX;
         
         public heap(){
                R=new ArrayList<>();
                IDX=-1;
         }
         public void add(int a){     
              IDX++;  
              R.add(a);
                
              int idx=IDX;
              while(R.get((idx-1)/2)>R.get(idx)&&idx>=0){
                int temp=R.get((idx-1)/2);              
                R.set((idx-1)/2,R.get(idx) );
                R.set(idx,temp);
                idx=(idx-1)/2;
              }
                
         }
         public int peek(){
                return R.get(0);
              
         }
         public void heapify(){

               int x=0;
                int l=(2*x)+1;
                int r=(2*x)+2;
                int temp;
              

              while(x<=IDX){
                  if(r<=IDX){
                     if(R.get(l)<R.get(r)&&R.get(x)>R.get(l)){
                            temp=R.get(l);
                            R.set(l, R.get(x));
                            R.set(x,temp);
                            x=l;
                     }else if(R.get(l)>R.get(r)&&R.get(x)>R.get(r)){
                            temp=R.get(r);
                            R.set(r, R.get(x));
                            R.set(x,temp);
                            x=r;
                     }else{
                            x=IDX+1;
                     }                     
                  }
                  else if(l<=IDX){
                     if(R.get(x)>R.get(l)){
                            temp=R.get(l);
                            R.set(l, R.get(x));
                            R.set(x,temp);
                            x=l;
                     }else{
                             x=IDX+1;
                     }
                  }else{  x=IDX+1;  }
               
                  l=(2*x)+1;
                  r=(2*x)+2;

              }
         }
         public ArrayList<Integer> sort(int i){

              ArrayList<Integer> n=R;               
              ArrayList<Integer> m=new ArrayList<>(); 
              while(!R.isEmpty())
              {
                     if(i==1) m.add(R.get(0)); 
                     else m.add(0,R.get(0));
                     removeMin();
              }
              R=n;
              return m;
         } 
         public void removeMin(){
              if(IDX==-1){return;}
                R.set(0,R.get(IDX));
                R.remove(IDX);
                IDX--;
                heapify();
               
         }
         
         public boolean isEmpty(){
                     if(IDX==-1){return true;};
                     return false;
         }
    }
    
    public static void main(String[] args) {
    
        int A[]={8,6,5,15,1,3,2,10,7};

        heap H=new heap();
        for(int i=0;i<A.length;i++){
                H.add(A[i]);
        }
      
       System.out.println(H.R);
  
       
       System.out.println( H.sort(1));



    }

}
