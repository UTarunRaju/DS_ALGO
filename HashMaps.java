package Anjaneya;
import java.util.*;

import Anjaneya.PrioQueue.Path;
public class HashMaps {
    
    public static void main(String[] args) {
        
        HashMap <String,String> Map= new HashMap<>();
        
         Map.put("DEL", "GOA");
        Map.put("CHA","BAN");
         
         Map.put("GOA", "CHA");
         Map.put("MUM", "DEL");
         
        String next=" ";

        
         for(String M:Map.keySet()){
            if(!Map.values().contains(M)){
                next=Map.get(M);
                System.out.print(M+" -> "+next);
                Map.remove(M);
                break;
            }
        }

    
        while(!Map.isEmpty()){
             next=Map.remove(next);
             System.out.print(" -> "+next);
        }
        
    }


}
