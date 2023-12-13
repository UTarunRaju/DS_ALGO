package Anjaneya;
import java.util.*;
public class DynamicProgramming {

static class pair{
    int a, b;
    pair(int a,int b){
        this.a=a;
        this.b=b;
    }
}

public static int fibunachi_memoization(int i,int S[]){
    if(i==0||i==1){
        return i;
    }

    if(S[i]!=0){
        return S[i];
    }

    S[i]=fibunachi_memoization(i-1, S)+fibunachi_memoization(i-2, S);
    return S[i];
}

public static int fibunachi_tabulation(int i){
    int dp[]=new int[i+1];
    dp[0]=0;
    dp[1]=1;
    for(int j=2;j<=i;j++){
        dp[j]=dp[j-1]+dp[j-2];
    }

    return dp[i];
}

public static int climbingStairs_tabulation(int a){
    if(a==1||a==2||a==0){
        return a;
    }

    int A[]=new int[a+1];
    A[1]=1;
    A[2]=2;
    A[0]=0;
    for(int j=3;j<=a;j++){
        A[j]=A[j-2]+A[j-1];
    }

    return A[a];
}

public static int climbingStairs_memoization(int a,int A[]){
    if(A[a]!=0){
        return A[a];
    }
    if(a<3){
        A[a]=a;
        return A[a];
    }
    if(a==3){
        A[a]=4;
        return A[a];
    }
    

    A[a]=climbingStairs_memoization(a-2,A)+climbingStairs_memoization(a-1,A)+climbingStairs_memoization(a-3,A);
   return A[a];
}

public static int knapsack_memoization(int i,int val[],int wt[],int restWeight,int S[][]){
    if(i>=val.length){
        return 0;
    }
    if(S[i][restWeight]!=0){
        if(S[i][restWeight]==-1){
            return 0;
        }
        return S[i][restWeight];
    }
    int max=Integer.MIN_VALUE;
    int temp;
    for(int j=i;j<val.length;j++){
        if(wt[j]<restWeight){
           temp=knapsack_memoization(j+1, val, wt, restWeight-wt[j],S);
           max=Math.max(val[j]+temp,max);
        }
        if(wt[j]==restWeight){
           max=Math.max(val[j],max);
        }
    }

    if(max==Integer.MIN_VALUE){
        S[i][restWeight]=-1;
        return 0;
    }

    S[i][restWeight]=max;
    return max;
}

public static int knapsack_tabulation(int bag,int val[],int wt[]){
    
    int S[][]=new int[wt.length][bag+1];

    for(int j=0;j<=bag;j++){
        if(wt[wt.length-1]<=j){
            S[wt.length-1][j]=val[wt.length-1];
        }
    }

    for(int j=wt.length-2;j>=0;j--){
        for(int k=0;k<=bag;k++){
            if(j==0){k=bag;}
            if(wt[j]<=k){
                S[j][k]=Math.max(val[j]+S[j+1][k-wt[j]],S[j+1][k]);
            }else{
                S[j][k]=S[j+1][k];
            }
        }
    }


    return S[0][bag];
}

public static boolean TargetSum_TAB(int A[],int sum){

    boolean [][] S=new boolean [A.length][sum+1];

    for(int i=0;i<=sum;i++){
            if(i==A[A.length-1]){
                S[A.length-1][i]=true;
            }
    }


    for(int i=A.length-2;i>=0;i--){
        for(int j=0;j<=sum;j++){
            if(j>A[i]){
                S[i][j]= S[i+1][j-A[i]]||S[i+1][j];
            }
            if(j==A[i]){
               S[i][j]=true;
            }
            if(j<A[i]){
                S[i][j]=S[i+1][j];
            }
        }
    }



    return S[0][sum];
}

public static int UnboundKnapsack_TAB(int val[],int wt[],int k)
{
    int S[]=new int[k+1];
    S[0]=0;

    TreeMap<Integer,Integer> TS=new TreeMap<>();

    for(int i=0;i<val.length;i++){
        TS.put(wt[i],val[i]);
    }

    int max=0;
    for(int i=1;i<=k;i++){
        for(int j:TS.keySet()){
            if(j>i) break;
            max=Math.max(max,TS.get(j)+S[i-j]);
        }
        S[i]=max;
    }

    return S[k];

}

public static int CoinChange_TAB(int coin[],int k){
    Arrays.sort(coin);
    int S[][]=new int[coin.length][k+1];
    int sum;
    for(int i=0;i<coin.length;i++){
       for(int j=0;j<=k;j++){
        if(j==0){
            S[i][j]=1;
        }else if(i==0){
            if(j%coin[i]==0){
            S[i][j]=1;
            }
        }else{
            if(j>=coin[i]){
                sum=0;
                for(int l=0;l<=i;l++){
                    sum+=S[l][j-coin[i]];
                }
                S[i][j]=sum;
            }
        }
       }
    }
   
    return S[coin.length-1][k];
}

public static int rodcutting_Tab(int length[],int price[],int rodLength){
    if(rodLength==0){
        return 0;
    }
    int S[][]=new int[length.length][rodLength+1];
    //i=size of the part
    //j=left size
    int sum=0;
    int temp=0;
    for(int i=0;i<S.length;i++){
        S[i][0]=0;
        for(int j=1;j<=rodLength;j++){
           if(j>=length[i]){
            S[i][j]+=price[i];
            temp=0;
            for(int k=0;k<=i;k++){
                temp=Math.max(temp,S[k][j-length[i]]);
            }

            S[i][j]+=temp;
           }

        }
        sum+=S[i][rodLength];
    }

    return sum;
}

public static int Longest_Common_Subsequence_Tab(String A,String B){
   
    int S[][]=new int[A.length()][B.length()];

    for(int i=0;i<A.length();i++){
        for(int j=0;j<B.length();j++){
            if(A.charAt(i)==B.charAt(j)){
                S[i][j]=1+S[i-1][j-1];
                if(i==0||j==0){
                    S[i][j]=1;
                }
            }else{
                if(j==0&&j==0){
                    S[i][j]=0;
                }
                else if(j==0){
                    S[i][j]=S[i-1][j];
                }
                else if(i==0){
                    S[i][j]=S[i][j-1];
                }else{
                    S[i][j]=Math.max(S[i][j-1],S[i][j]);
                }
            }
            
        }
    }

    return S[A.length()-1][B.length()-1];

}

public static int Longest_Common_Substring_Tab(String A,String B){
    int S[][]=new int[A.length()][B.length()];

    int max=0;
    for(int i=0;i<A.length();i++){
        for(int j=0;j<B.length();j++){
            if(A.charAt(i)==B.charAt(j)){
                if(i==0||j==0){
                    S[i][j]=0;
                }else{
                    S[i][j]=1+S[i-1][j-1];
                    max=Math.max(max, S[i][j]);
                }
            }
        }
    }

    return max;
}

public static int Edit_Distance_tab(String A,String B){
  
    int S[][]=new int[A.length()+1][B.length()+1];

    for(int i=-1;i<A.length();i++){
        for(int j=-1;j<B.length();j++){
            if(i==-1){
                S[i+1][j+1]=j+1;
            }else if(j==-1){
                S[i+1][j+1]=S[i][j+1]+1;
            }else if(A.charAt(i)==B.charAt(j)){
                S[i+1][j+1]=S[i][j];
            }else{
                S[i+1][j+1]=Math.min(S[i][j+1],Math.min(S[i][j],S[i+1][j]))+1;
            }
        }    
    }

    return S[A.length()][B.length()];
}

public static void Minimum_No_Deletion_Addition(String A,String B){

    pair S[][]=new pair[B.length()+1][A.length()+1];

    for(int i=-1;i<B.length();i++){
        for(int j=-1;j<A.length();j++){
            if(i==-1&&j==-1){
                S[i+1][j+1]=new pair(0, 0);
            }else if(i==-1){
                S[i+1][j+1]=new pair(0, S[i+1][j].b+1);
            }else if(j==-1){
                S[i+1][j+1]=new pair(S[i][j+1].a+1,0);
            }else if(B.charAt(i)==A.charAt(j)){
                S[i+1][j+1]=S[i][j];
            }else{
                if(S[i][j+1].a+S[i][j+1].b<S[i+1][j].a+S[i+1][j].b){
                    S[i+1][j+1]=new pair(S[i][j+1].a+1, S[i][j+1].b);  
                }else{
                    S[i+1][j+1]=new pair(S[i+1][j].a, S[i+1][j].b+1);   
                }
            }
        }    
    }

    System.out.println("I :"+S[B.length()][A.length()].a+" D :"+S[B.length()][A.length()].b);
}

public static boolean Wildcard_Matching(String s,String pattern){

    StringBuilder SB= new StringBuilder("");
            
    for(int j=0;j<pattern.length();j++){
        if(j==0)SB.append(pattern.charAt(j));
        else{
           if(pattern.charAt(j)=='*'){
                if(SB.charAt(SB.length()-1)!='*'){
                    SB.append(pattern.charAt(j));
                }
           }else{
                SB.append(pattern.charAt(j));
           }
        }
    }

    pattern=SB.toString();
    // System.out.println(pattern);

    if(pattern.length()==0){
        if(s.length()==0){
            return true;
        }
                return false;
            }
    int [][]S=new int[pattern.length()][s.length()+1];
    
    for(int i=0;i<pattern.length();i++){
        for(int j=-1;j<s.length();j++){
            if(i==0){
                if(pattern.charAt(i)=='*'){
                    S[i][j+1]=1;
                }
                else if(pattern.charAt(i)=='?'){
                    if(j==0){
                        S[i][j+1]=1;
                    }
                }else{
                    if(j==0){
                        if(pattern.charAt(i)==s.charAt(j)){
                            S[i][j+1]=1;
                        }
                    }
                }
            }else{
                if(pattern.charAt(i)=='*'){
                    if(j==-1){
                        S[i][j+1]=S[i-1][j+1];
                    }else{
                        S[i][j+1]=Math.max(S[i-1][j],Math.max(S[i-1][j+1],S[i][j]));
                    }                    
                }
                else if(pattern.charAt(i)=='?'){
                    if(j>-1){
                        S[i][j+1]=S[i-1][j];
                    }
                }else{
                    if(j>-1){
                        if(pattern.charAt(i)==s.charAt(j)){
                            S[i][j+1]=S[i-1][j];
                        }
                    }
                }
            }
        }
    }

//     for(int i=0;i<pattern.length();i++){
//         for(int j=0;j<=s.length();j++){
//             System.out.print(S[i][j]+" ");

//         }System.out.println();
// }

if(S[pattern.length()-1][s.length()]==0){
    return false;
}

    return true;
}

public static int Catalans_Number(int k){
    if(k==1||k==2){
        return 1;
    }

    int S[]=new int[k];
    S[0]=1;S[1]=1;
    int m,n;
    for(int j=2;j<k;j++){
        m=0;
        n=j-1;
        while(n>=m){
            if(m==n){
                S[j]+=S[m]*S[n];
            }else{
                S[j]+=2*(S[m]*S[n]);
            }
           
            m++;n--;
        }
    }

    return S[k-1];
}
public static void main(String[] args) {

System.out.println( Catalans_Number(3));   
   
}


}


