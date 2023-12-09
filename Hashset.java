package Anjaneya;

import java.util.*;


public class Hashset {
        
      public static void main(String[] args) {

       
        HashSet <Integer> HS=new HashSet<>();
            HS.add(10);
            HS.add(9);
            HS.add(11);
            HS.add(12);
            HS.add(13);
        
        Iterator<Integer> h=HS.iterator();
        while(h.hasNext()){
            System.out.print(h.next()+" ");
        }
        System.out.println();

        LinkedHashSet <Integer> LHS=new LinkedHashSet<>();
            LHS.add(10);
            LHS.add(9);
            LHS.add(11);
            LHS.add(12);
            LHS.add(13);
        
        for(int i:LHS){ System.out.print(i+" ");}
        System.out.println();
        
        TreeSet<Integer> TS=new TreeSet<>();
            TS.add(10);
            TS.add(9);
            TS.add(11);
            TS.add(12);
            TS.add(13);

        for(Iterator<Integer> M=TS.iterator();M.hasNext();){
            System.out.print(M.next()+" ");
        }
        
               



    }
}
