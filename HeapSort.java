package Anjaneya;

public class HeapSort {
    public static void heap(int A[],int i,int j){
      
            int l=(2*i)+1;
            int r=(2*i)+2;
            
        if(l<=j){
            heap(A, l, j);
        }
        if(r<=j){
            heap(A, r, j);
        }
          
        heapify(A, i, j);
        
    }

    public static void heapSort(int A[]){
        int temp=0;
        for(int k=A.length-1;k>=1;k--){
            heap(A, 0, k);
            temp=A[0];
            A[0]=A[k];
            A[k]=temp;
        }        
    }

    public static void heapify(int A[],int i,int j){
        if(i>=j){return;}
            int l=(2*i)+1;
            int r=(2*i)+2;
            int temp=0;
        
                if(r<=j){
                    if(A[r]<A[l]&&A[i]<A[l]){temp=A[i];A[i]=A[l];A[l]=temp;}
                    else if(A[r]>A[l]&&A[i]<A[r]){temp=A[r];A[r]=A[i];A[i]=temp;}
                }else if(l<=j){
                    if(A[l]>A[i]){temp=A[i];A[i]=A[l];A[l]=temp;}
                }
          
               heapify(A, l, j);
                  heapify(A, r, j);
            
    }
   
    public static void main(String[] args) {
            

        int A[]={11,4,6,3,9,1,7,17,8};
        heapSort(A);

        for(int i=0;i<A.length;i++){
        System.out.print(A[i]+" ");
        }

    }
}
