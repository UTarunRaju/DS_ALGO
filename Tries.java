package Anjaneya;
import java.util.*;

public class Tries {
    static class tries{
        node head;

        tries(){
            head=new node();
        }

    }

    static class node{

        boolean EOD;
        node childrens[];

        node(){
            childrens=new node[26];
        }

    }


    static class StringA implements Comparable<StringA>{

        String S;
        StringA(String S){
            this.S=S;
        }


        @Override
        public int compareTo(StringA B){
            if(this.S.length()==B.S.length()){
                return this.S.compareTo(B.S);
            }else{
                return B.S.length()-this.S.length();
            }
        }
    } 

    
    public static void main(String[] args) {
        
        String S[]={"an", "banana", "aopp", "appql", "ap", "aqpply", "app" ,"apopla"};
        
        PriorityQueue <StringA>A= new PriorityQueue<>();
   
        tries T=new tries();
        for(int j=0;j<S.length;j++){
            A.add(new StringA(S[j]));
            node temp=T.head;
            int i;
            for(int k=0;k<S[j].length();k++){
                i=S[j].charAt(k)-'a';
                if(temp.childrens[i]==null){
                    temp.childrens[i]=new node();
                }
                temp=temp.childrens[i];
                if(k==S[j].length()-1){
                    temp.EOD=true;
                }
            }
        }

        String X="";
        boolean jump=false;
        while(!A.isEmpty()){
            X=A.poll().S;
            node temp=T.head;
            int j;
            for(int i=0;i<X.length();i++){
                j=X.charAt(i)-'a';
                if(temp.childrens[j].EOD){
                    temp=temp.childrens[j];
                }
                else{
                    break;
                }

                if(i==X.length()-1){
                    jump=true;
                }
            }

            if(jump) break;
        }

        if(jump){
              System.out.println(X);
        }else{
            System.out.println("noStringFound");
        }
      

    }

}
