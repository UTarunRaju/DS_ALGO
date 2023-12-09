package Anjaneya;
import java.util.*;

class 
NodeI
{
        
    Node A;
        int x;

        
        NodeI(
            Node A, int x)
        {
                this.A=A;
                this.x=x;
        }
}

class pair
{

    int a;
    int b;


    pair(int a, int b)
    {
        this.a=a;
        this.b=b;
    }
}

class 
Node
{
    
    Node left,right;

    int val;
    int pos;
    
    Node(int val)
    {
            this.val=val;
    }

}

class mac
{
    int dia;
    int len;

    mac(int dia,int len)
    {
        this.dia=dia;
        this.len=len;
    }
}
class BinaryTree
{
   private static 
   Node root;
   
   protected int mno=0;

   public static LinkedList<Integer>G=new LinkedList<>();

    public void PreOrder(
        Node root)
    {
        if(root==null)
        {
            return ;
        }

        System.out.print(root.val);
        PreOrder(root.left);
        PreOrder(root.right);
    }
    
    public void PreOrderMO(
        Node root)
    {
        if(root==null)
        {
            System.out.print("-1"+" ");
            return;
        }

        System.out.print(root.val+" ");
        PreOrderMO(root.left);
        PreOrderMO(root.right);
    }

    public void inOrder(
        Node root)
    {
        if(root==null)
        {
            return ;
        }

        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }

    public void postOrder(
        Node root)
    {
        if(root==null)
        {
            return ;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+" ");
       
    }

    public void PrintList()
    {
        for(int k:G)
        {
            System.out.print(k+" ");
        }
        System.out.println();
    }
   
    static int id=-1;
    public BinaryTree(int A[])
    {
            root=createBT(A);
    }
    
    public BinaryTree(
        Node a)
    {
        root=a;
    }
    
    public   
    Node createBT(int A[])
    {
        id++;
        
        Node g= new 
        Node(A[id]);
        

            if(A[id]==-1)
            {
                return null;
            }


            g.left=createBT(A);
            g.right=createBT(A);

            if(g.left==null&&g.right==null)
            {
                    G.add(g.val);
            }
        return g;
    } 

    public int height(Node head)
    {
            if(head==null)
            {
                    return 0;
            }
            else
            {
                return 1+Math.max(height(head.left), height(head.right));
            }
    }

    public int noOfNodes(Node head)
    {
            if(head==null)
            {
                    return 0;
            }
            else
            {
                return 1+noOfNodes(head.left)+noOfNodes(head.right);
            }
    }

    public int sumOfNodes(Node head)
    {
            if(head==null)
            {
                    return 0;
            }
            else
            {
                return head.val + sumOfNodes(head.left) + sumOfNodes(head.right);
            }
    }

    public void levelOrder(
        Node head)
        {
            
                if(head==null)
                {
                    System.out.println("null");
                }
            
                Queue<
                Node> A= new LinkedList<>();

                A.add(head);
                A.add(null);

                while(true)
                {
                    if(A.peek()==null)
                    {
                      A.remove();
                      System.out.println();
                      if(A.isEmpty())
                      {
                        break;
                      }
                      else
                      {
                        A.add(null);
                      }
                    }
                    else
                    {
                        if(A.peek().left!=null)
                        {
                           A.add(A.peek().left);
                        }
                        if(A.peek().right!=null)
                        {
                          A.add(A.peek().right);
                        }
                        System.out.print(A.peek().val+" ");
                        A.remove();
                    }
                    
                    
                }

        }

    public boolean subTreeOfTree(
        Node a)
    {
           return findA(root,a);
    }

    private boolean findA(
        Node r, 
    Node a) {
        
        if(r==null)
        {
            return false;
        }
        if(r.val==a.val)
        {
            return chek(r,a) ;
        }
        boolean L=findA(r.left, a);
        boolean R=findA(r.right,a);

        return L||R;
       
    }

    private boolean chek(
        Node r,
    Node a)
    {
        if(r==null&&a==null)
        {
            return true;
        }
        if((r==null&&a!=null)||(r!=null&&a==null))
        {
            return false;
        }
        if(r.val==a.val)
        {
            boolean L=chek(r.left, a.left);
            boolean R=chek(r.right, a.right);
            return L&&R;
        }
        
        return false; 
    }

    public int Diameter()
    {
        mac A=DD(root);
        return Math.max(A.dia,A.len);
    }
    
    public static HashMap<Integer,Integer> HM= new HashMap<>();

    public static void topView(
        Node a)
    {
        HM=new HashMap<>();
        topView_sifer(a, 0);
       Integer A []=new Integer [HM.size()];
        HM.keySet().toArray(A);
        Arrays.sort(A);
     for(int k:A)
     {
            System.out.println(HM.get(k));
     }
        
    }

    public static void topView_sifer(
        Node head,int k)
    {
        if(head!=null){
        if(!HM.containsKey(k)){HM.put(k,head.val);}
      topView_sifer(head.right, k+1);
        topView_sifer(head.left, k-1);
         }
    }
    
    public static void setmap(LinkedList<
    Node>A)
    {
        if(A.isEmpty())
        {
            return;
        }
        
        Node k=A.getFirst();
        A.removeFirst();
        if(!HM.containsKey(k.pos))
        {
                HM.put(k.pos,k.val);
        }

        if(k.left!=null)
        {
            k.left.pos=k.pos-1;
            A.add(k.left);
        }

        if(k.right!=null)
        {
        k.right.pos=k.pos+1;
        A.add(k.right);
        }
        
        setmap(A);
       


    }

    public static void  k_levell(
        Node a,int i,int j)
    {
        if(a!=null)
        {
            if(i==j)
            { 
                System.out.print(a.val+" ");
            }
            else 
            {
                    k_levell(a.left, i+1, j);
                     k_levell(a.right, i+1, j);
            }
        }

    }
    
    public mac DD(
        Node root)
    {
      if(root==null)
      {
        mac a=new mac(0,0);
        return a;
      }
      else
      {
        mac a=DD(root.left);
        mac b=DD(root.left);
        int maxd=Math.max(a.dia,b.dia);
        
        int maxl=Math.max(a.len,b.len);
        maxd=Math.max(maxd, a.len + b.len +1);

        return new mac(maxd,maxl+1);
      }

    }

    public static  int k=0;
    public static 
    Node Lowest_Common_Ancestor(
        Node root ,int l,int r)
    {
        if(root==null||root.val==l||root.val==r)
        {
            return root;
        }

        
        Node a=Lowest_Common_Ancestor(root.left, l, r);
        
        Node b=Lowest_Common_Ancestor(root.right, l, r);

            if(a==null)
            {
                return b;
            }
            if(b==null)
            {
                return a;
            }


            return root;
    }

    public static pair minDistance(
        Node R, int a, int b)
    {
            if(R==null)
            {
                return null;
            }
            if(R.val==a||R.val==b)
            {

                pair l=new pair(1, 0);
                return l;
            }

            pair l=minDistance(R.left, a, b );
            pair r=minDistance(R.right, a, b);


            if(l==null&&r==null)
            {
                return null;
            }

            if(l==null)
            {
                if(r.b==0)
                {
                    r.a++;
                }
                return r;
            }
            if(r==null)
            {
                if(l.b==0)
                {
                    l.a++;
                }
            
                return l;
            }


            pair h=new pair(r.a+l.a, 1);
            return h;
    }


    public static 
    NodeI K_ansister(
        Node r,int a, int k)
    {
        if(r==null)
        {
            return null;
        }
        if(r.val==a)
        {
            
            NodeI h=new 
            NodeI(r,0);
            return h;
        }

        
        NodeI L=K_ansister(r.left,a,k);
        
        NodeI R=K_ansister(r.right,a,k);

      
    
        
        if(L==null&& R!=null)
        {
            if(R.x==k)
            {
                return R;
            }
            else
            {
                R.x++;
                R.A=r;
                return R;
            }
        }

        if(R==null&& L!=null)
        {
            if(L.x==k)
            {
                return L;
            }
            else
            {
                L.x++;
                L.A=r;
                return L;
            }
        }
   
        
            return null;

    }

    public static 
    Node sumTree(
        Node a)
    {
        if(a==null)
        {
            return null;
        }
        
        
        Node L=a.left;
        
        Node R=a.right;
        int sum=0;
        if(L!=null)
        {
            sum+=L.val;
           L=sumTree(L);
            sum+=L.val;
        }
        if(R!=null)
        {
            sum+=R.val;
            R=sumTree(R);
                sum+=R.val;
        }


         
         
        
     

        
        Node re=new 
        Node(sum);
        re.left=L;
        re.right=R;
        return re;
        

    }

    public static 
    Node invertTree(
        Node R)
    {
        if(R==null)
        {
            return null;
        }

        
        Node temp=R.left;
        R.left=invertTree(R.right);
        R.right=invertTree(temp);

        return R;
    }

    public static boolean isLeaf(
        Node R)
    {
            if(R.left==null&&R.right==null)
            {
                return true;
            }

            return false;
    }

    public static 
    Node Delete_leaves(
        Node R,int k)
    {
           if(R==null)
           {
            return R;
           }
           else if(R.left==null&&R.right==null)
           {
             if(R.val==k) return null;
             else return R;            
           }
           else if(R.left==null)
           {
             R.right=Delete_leaves(R.right, k);
           }
           else if(R.right==null)
           {
            R.left=Delete_leaves(R.left, k);
           }
           else
           {
             R.right=Delete_leaves(R.right, k);
              R.left=Delete_leaves(R.left, k);
           }

           if(R.left==null&&R.right==null)
           {
             if(R.val==k) return null;
             else return R;            
           }
           else
           {
            return R;
           }
    }

    public static HashMap<
    Node ,Stack<Integer>>mapN=new HashMap<>();

    public static Stack<Integer> subtreeStack(
        Node r)
    {
        
            Stack<Integer> R=new Stack<>();
            if(r==null) return R;
            
            R.push(r.val);
            if(r.left==null&&r.right==null)
            {
                    mapN.put(r, R);
                return R;
            }
            else if(r.left!=null&&r.right!=null)
            {
                R.addAll(subtreeStack(r.left));
                R.addAll(subtreeStack(r.right));
            }
            else if(r.left==null)
            {
                R.addAll(subtreeStack(r.right));
            }
            else if(r.right==null)
            {
                R.addAll(subtreeStack(r.left));
            }
            

            mapN.put(r, R);
        

        return R;

    }

    public static boolean checkStack(Stack<Integer>A,Stack<Integer>B)
    {
            if(A.size()!=B.size())
            {
                return false;
            }
            else
            {
                for(int i=0;i<A.size();i++)
                {
                    if(A.get(i)!=B.get(i)) return false;
                }
                return true;
            }
    }

    public static LinkedList<LinkedList<
    Node>> DuplicateSubTree (
        Node b)
    {

        LinkedList <LinkedList<
        Node>>R= new LinkedList<>();
            subtreeStack(b);
            
            Node keyS[]=new 
            Node[mapN.size()];
            mapN.keySet().toArray(keyS);
            for(int i=0;i<mapN.size()-1;i++)
            {        
                LinkedList<
                Node> r=new LinkedList<>();
                r.add(keyS[i]);  
                if(keyS[i]!=null)
                {      
                 for(int j=i+1;j<mapN.size();j++)
                    {                
                        if(keyS[j]!=null&&keyS[i].val==keyS[j].val)
                        {
                                if(checkStack(mapN.get(keyS[i]),mapN.get(keyS[j])))
                                {
                                     r.add(keyS[j]); 
                                    keyS[j]=null;
                                }
                        }
                    }
                }
                if(r.size()>1)
                {
                    R.add(r);
                }
            }

            return R;
    }

    public static void printTree(Node a)
    {        
       String R="";
       R=R.concat( Integer.toString(a.val)+" --> { ");
       boolean t=false;
       boolean w=false;

       if(a.left!=null)     t=true;
       if(a.right!=null)    w=true;

       if(t) R=R.concat(Integer.toString(a.left.val)+" , ");
       else R=R.concat(" null , ");
   

       if(w) R=R.concat(Integer.toString(a.right.val)+" }");
       else R=R.concat("null }");


       System.out.println(R);

       if(t) printTree(a.left);    
       if(w) printTree(a.right);    
        

    }

    public static int maxPathSum(
        Node a)
    {
        a=maximumSumPath(a);
        return (a!=null)?a.val:Integer.MIN_VALUE;
    }

    public static int maxz=0;
    public static 
    Node maximumSumPath( Node a)
    {
        if(a==null) return null;
        int sum=0;
        if(a.left==null&&a.right==null) {if(maxz<a.val) maxz=a.val; return a; }
        else if(a.left!=null&&a.right!=null)
        {
            a.left=maximumSumPath(a.left);  
            a.right=maximumSumPath(a.right);
            sum=a.val+a.left.val+a.right.val;
            a.val=Math.max(a.val+Math.max(a.right.val, a.left.val),a.val);
            maxz=Math.max(maxz,Math.max(sum,a.val));
            return a;
        }
        else if(a.left==null)
        {
            a.right=maximumSumPath(a.right);;
            a.val=Math.max(a.val, a.val+a.right.val);
            maxz=Math.max(a.val,maxz);
            return a;
        }
        else
        {
            a.left=maximumSumPath(a.left);;
            a.val=Math.max(a.val,a.val+a.left.val);
            maxz=Math.max(a.val,maxz);
            return a;
        }
        

    }

    public static Node remove_leaf_OfValK(Node root,int k)
    {
        if(root==null)
        {
            return null;
        }

        if(root.left!=null&&root.right!=null)
        {
           root.left= remove_leaf_OfValK(root.left, k);
            root.right=  remove_leaf_OfValK(root.right, k);
        }
        else if(root.left!=null&&root.right==null)
        {
               root.left= remove_leaf_OfValK(root.left, k);
        }
        else if(root.left==null&&root.right!=null)
        {
              root.right=remove_leaf_OfValK(root.right, k);
        }
        
        if(root.left==null&&root.right==null)
        {
            if(root.val==k)
            {
                root=null;
            }
        }

        return root;
        
    }



    public static void main(String[] args) {

    //              1
    //         2        3
    //    3      5   3    3


      Node a=new Node(1);
      Node b=new Node(2);
      Node c=new Node(3);
      Node d=new Node(3);
      Node e=new Node(5);
      Node f=new Node(3);
      Node g=new Node(3);
        
   a.left=b;
   a.right=c;
   b.left=g;
   b.right=e;
   c.left=d;
   c.right=f;
 
   remove_leaf_OfValK(a, 3);
      
     printTree(a);
       
    }
}
