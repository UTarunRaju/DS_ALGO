package Anjaneya;

import java.util.ArrayList;
import java.util.*;

class node{
    int data;
    node left;
    node right;
    int height;
   public node(int data)
    {
            this.data=data;
    }
}



class obj{

    int max=0;
    int min=0;
    int count=0;
    boolean r;
    node Node;
    public obj(int max,int min,int count,boolean r,node Node){this.max=max; this.min=min; this.count=count; this.r=r;this.Node=Node;}
}

public class BinarySearchTree {

    public static void inOrder(node root)
    {
        if(root==null)
        {
            return ;
        }

        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public static boolean find(node root,int k)
    {
        if(root==null)
        {
            return false;
        }
        else if(root.data==k)
        {
            return true;
        }
        
        return (find(root.left, k)||find(root.right, k));
    }

    public static node insert(node root,int k)
    {
        if(root==null)
        {
            root=new node(k);
        }
        else if(root.data>k)
        {
            root.left=insert(root.left, k);
        }
        else
        {
            root.right=insert(root.right, k);
        }

        return root; 
    }

    public static void printInRange(int a, int b,node root)
    {
            if(root==null)
            {
                return;
            }
           
            if(root.data>b)
            {
                printInRange(a, b, root.left);
            }else  
            if(root.data>=a&&root.data<=b)
            {
                   printInRange(a, b, root.left);
                System.out.print(root.data+" ");
             
                printInRange(a, b, root.right);
            }
            else
            {
                printInRange(a, b, root.right);
            }

    }

    public static void root_to_leaf(node root,ArrayList<Integer> arr)
    {
        if(root==null){return;}
        arr.add(root.data);
        if(root.left==null&&root.right==null){System.out.println(arr);}
        root_to_leaf(root.left, arr);
        root_to_leaf(root.right, arr);
         arr.remove(arr.size()-1);
    }
   
    public static node deleteNode (node root, int a)
    {
            if(root==null)
            {
                return null;
            }
            else if(root.data==a)    {
                if(root.left==null && root.right==null){ return null; }
                if(root.left==null)  { return root.right;    }
                else if(root.right==null) { return root.left;      }
                else {      
                   
                    node temp  = root.right;
                    if(temp.left==null) {
                        temp.left=root.left;
                        return temp;
                    }
                    else {
                        while ( temp.left.left != null ) { temp = temp.left; }
                        node temp2=temp.left;
                        temp.left=null;
                        temp2.left=root.left;
                        temp2.right=root.right;
                        return temp2;
                    }                   
                   
                }             
               
            }
            else{
                if(root.data>a)  root.left=deleteNode(root.left, a);
                else root.right=deleteNode(root.right, a);
                return root;
            }




    }

    static final int COUNT = 10;
    static void print2DUtil(node root, int space)
    {
         space += COUNT;
        // Base case
        if (root == null)
        {

             System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print("null" + "\n");
        return;
        }
    
 
        // Increase distance between levels
       
 
        // Process right child first
        print2DUtil(root.right, space);
 
        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.data + "\n");
 
        // Process left child
        print2DUtil(root.left, space);
    }
 
    static void print2D(node root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
   
    public static node mirrorBst(node root)
    {
        if(root==null)
        {
            return null;
        }

        node temp=root.left;
        root.left=mirrorBst(root.right);
        root.right=mirrorBst(temp);
        return root;
    }
    
    public static boolean ValidBST(node root,int min,int max)
    {
            if(root==null)
            {
                return true;
            }
            
            if(root.data<max&& root.data>min)
            {
                return ValidBST(root.left, min, root.data)&&ValidBST(root.right,root.data,max);
            }
           
                return false;
            


    }

    public static void inorder(node a,ArrayList<Integer>R)
    {   
        if(a==null) return;
        inorder(a.left,R);
        R.add(a.data);
        inorder(a.right,R);
    }

    public static node BSTtoBalancedBST(node r)
    {
            ArrayList<Integer> B=new ArrayList<>();
            inorder(r,B);
            Integer A[]=new Integer[B.size()];
            B.toArray(A);
           
            return BalancedBST(A, 0, A.length-1);
    }
   
    public static node arrayToBalancedBST(int A[])
    {
            Arrays.sort(A);
            return BalancedBST(A,0,A.length-1);
    }

    public static node BalancedBST(Integer A[],int st,int en)
    {
        if(st>en) {return null; }
        int mid=(en+st)/2;
        node r=new node(A[mid]);
        r.left=BalancedBST(A, st, mid-1);
        r.right=BalancedBST(A, mid+1, en);
        return r;
    }

    public static node BalancedBST(int A[],int st,int en)
    {
        if(st>en) {return null; }
        int mid=(en+st)/2;
        node r=new node(A[mid]);
        r.left=BalancedBST(A, st, mid-1);
        r.right=BalancedBST(A, mid+1, en);
        return r;
    }

    public static node LargestBST(node a)
    {
            obj A=ObjBST(a);
            return A.Node;

    }

    public static int sizeLargestBST(node a)
    {
            obj A=ObjBST(a);
            return A.count;

    }

    public static obj ObjBST(node a)
    {
        obj R=null;
        if(a==null) return R;
        if(a.left==null&&a.right==null) { R=new obj(a.data,a.data,1,true,a);  return R; }
        else if(a.left==null){
            R=ObjBST(a.right);
            if(a.data<R.min&&R.r) {
                R.Node=a;
                R.min=a.data;
                R.count++;
            }               
        }
        else if(a.right==null){
            R=ObjBST(a.left);
            if(a.data>R.max&&R.r) {
                R.Node=a;
                R.max=a.data;
                R.count++;
            }         
        }
        else{
            R=ObjBST(a.left);
            obj S=ObjBST(a.right);
            if(R.r&&S.r&&R.max<a.data&&S.min>a.data){
                    R.max=S.max;
                    R.count+=S.count+1;
                    R.Node=a;
            }else{
                    if(R.count<S.count){R.count=S.count;R.Node=S.Node;}
                    R.r=false;
            }
            
        } 

            return R;
    }

    public static node mergeBST(node a,node b)
    {
            ArrayList<Integer> A=new ArrayList<>();
            inorder(a,A);
            ArrayList<Integer> B=new ArrayList<>();
            inorder(b,B);

            ArrayList <Integer>C=new ArrayList<>();
            int i=0,j=0;
            while(i<A.size()||j<B.size()){
                if(i<A.size()&&j<B.size()){
                    if(A.get(i)<B.get(j)){
                        C.add(A.get(i));
                        i++;
                    }
                    else {
                        C.add(B.get(j));
                        j++;
                    }
                }
                else if(i==A.size()){
                    C.addAll(B.subList(j, B.size()));
                    j=B.size();
                }else {
                    C.addAll(A.subList(i, A.size()));
                    i=A.size();
                }
            }
            
            Integer R[]=new Integer[A.size()+B.size()];
            C.toArray(R);
            return BalancedBST(R, 0,R.length-1);
    }


    public static node insertAVL(node r,int a)
    {
            if(r==null){node R=new node(a);R.height=1;return R;}
            if(r.data>a){
                r.left=insertAVL(r.left, a);               
                int bf=r.left.height-((r.right==null)?0:r.right.height);
                if(bf>=2||bf<=-2){return selfBalance(r);}
                else{r.height  =Math.max(r.left.height,(r.right==null)?0:r.right.height)+1;} 
                return r;                
            }else{
                r.right=insertAVL(r.right, a);               
                int bf=r.right.height-((r.left==null)?0:r.left.height);
                if(bf>=2||bf<=-2){return selfBalance(r);}
                else{r.height  =Math.max(r.right.height,(r.left==null)?0:r.left.height)+1;} 
                return r;  
            }


    }

    public static node selfBalance(node p)
    {
        node q=null;
        if(p.left==null){q=p.right;}
        else if(p.right==null){q=p.left;}
        else{q=(p.right.height>p.left.height)?p.right:p.left;}

        node r=null;
        if(q.left==null){r=q.right;}
        else if(q.right==null){r=q.left;}
        else{r=(q.right.height>q.left.height)?q.right:q.left;}

        if(p.left==q&&q.left==r){
            node temp=q.right;
            q.right=p;
            p.left=temp;
            p.height=1;
            return q;
        }else if(p.left==q&&q.right==r){
            node temp1=r.left;
            node temp2=r.right;
            r.left=q;
            r.right=p;
            q.right=temp1;
            p.left=temp2;
            r.height=2;
            p.height=1;
            q.height=1;
            return r;
        }else if(p.right==q&&q.right==r){
            node temp=q.left;
            q.left=p;
            p.right=temp;
            p.height=1;
            return q;
        }else{
            node temp1=r.left;
            node temp2=r.right;
            r.right=q;
            r.left=p;
            q.left=temp2;
            p.right=temp1;
            r.height=2;
            p.height=1;
            q.height=1;
            return r;
        }

    }
    public static void main(String[] args) {
      

        int A[]={10,20,30,40,50,25};
        node a=null;
        for(int i=0;i<A.length;i++)
        {
            a=insertAVL(a, A[i]);
        }
        
    
      
      print2D(a);

    // node a=new node(2);
    // node b=new node(1); a.left=b;
    // node c=new node(4); a.right=c;


    // node d=new node(9);
    // node e=new node(3);d.left=e;
    // node f=new node(12); d.right=f;
   
    // print2D(mergeBST(a, d));
       
    }
    
}
