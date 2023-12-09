package Anjaneya;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class HashMapCode {

    static class node <K,V>{

        node <K,V> next;
        K key;
        V val;

        node(K key, V val){
            this.key=key;
            this.val=val;
        }   
    }
    static class HashMap<K,V>{
        
        private int n;
        private int N;
        private int lb;
        LinkedList<node<K,V>> bucket[];
        HashMap(){
            n=0;N=4;bucket=new LinkedList[N];lb=2;

            for(int i=0;i<N;i++){
                bucket[i]=new LinkedList<node<K,V>>();
            }
        }

        public node<K,V> ListIndexOf(K key, LinkedList<node<K, V>> linkedList) {
          for(node<K,V> a:linkedList){
            if(a.key==key){
                return a;
            }
          }
            return null;
        }

        public ArrayList<K> KeySet(){

            ArrayList<K> G=new ArrayList<>();
            int i=-1;
             for(LinkedList<node<K,V>> R:bucket){
                i++;
                for(node<K,V> M:R){
                 G.add(M.key);
                }
            }

            return G;
        }

        public V get(K key){
            int bi=Math.abs(key.hashCode()%N);
            node li=ListIndexOf(key,bucket[bi]);

            if(li==null){
                        return null;
                    }else{
                        return (V)li.val;
                    }
        }

        public boolean containsKey(K key){
            int bi=Math.abs(key.hashCode()%N);
            node li=ListIndexOf(key, bucket[bi]);
            if(li ==null)return false;
            else return true; 

        }
        public V remove(K key){
            int bi=Math.abs(key.hashCode()%N);
           int l=0;
           for(node a:bucket[bi]){
           
            if(a.key==key){
                break;
            }
             l++;
           }
          
           if(l>bucket[bi].size()-1){
                return null;
           }
           else{
            n--;
            return bucket[bi].remove(l).val;
           }

           
        }
        public void put(K key,V val){
                    int bi=Math.abs(key.hashCode()%N);
                    node li=ListIndexOf(key,bucket[bi]);
                   
                    if(li==null){
                        bucket[bi].add(new node<K,V>(key, val));
                    }else{
                        li.val=val;
                    }
                   
                    n++;
                    if(n/N>lb){
                        
                        LinkedList<node<K,V>> temp[]=bucket;
                        N*=2;
                        bucket=new LinkedList[N];
                    
                        for(int i=0;i<N;i++){
                            bucket[i]=new LinkedList<node<K,V>>();
                        }

                        for(LinkedList<node<K,V>> R:temp){
                            for(node<K,V> M:R){
                            bucket[Math.abs(M.key.hashCode()%N)].add(M);
                            }
                        }
                    }

                }

        public void print(){
            int i=-1;
             for(LinkedList<node<K,V>> R:bucket){
                i++;
                for(node<K,V> M:R){
                 System.out.println(i+" "+M.key+" "+M.val);
                }
            }
        }

        public boolean isEmpty(){
            return n==0;
        }
    
        
        }

    
public static void main(String[] args) {
    
    HashMap<String,Integer> Map=new HashMap<>();
 
    
  
    System.out.println(Map.isEmpty());
    
 
}


    
}
