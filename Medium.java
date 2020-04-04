package algoritmos;

import javax.management.RuntimeErrorException;

public class Medium{

    public static void main(String[] args) {
        
        int[] array = { 4, 1, 10, 8, 7, 12, 9, 2, 15};
        int k = (array.length / 2) + 1;
        
        //Find the k'th = 5 smallest element
        System.out.println(quickSelect(array, 0, array.length - 1, k));

    }

    /**
     * Solves the selection problem by recursive partition-based algorithm
     * @param array sub array[l..r] input of orderable elements  
     * @param l lower bound
     * @param r upper bound
     * @param k k'th smallest element to find
     * @return k'th element desired
     */
    public static int quickSelect(int[] array, int l, int r, int k){
        if (l > r) {
            throw new RuntimeException("Index out of bound");
        }
        int s = lomutoPartition(array, l, r);
        if ( s == l + k - 1 ) return array[s];
        else if ( s > l + k - 1 ) return quickSelect(array, l, s - 1, k);
        else{
            return quickSelect(array, s + 1, r, k - 1 - s);
        } 
    }

    /**
     * Partitions subarray by Lomuto’s algorithm using first element as pivot
     * @param array sub array[l..r]
     * @param l lower bound
     * @param r upper bound
     * @return Partition of A[l..r] and the new position of the pivot
     */
    public static int lomutoPartition(int[] array, int l, int r){
        int p = array[l];
        int s = l;
        for (int i = l + 1; i <= r; i++) {
            if (array[i] < p) {
                s = s + 1;
                swap(array, i, s);
            }
        }
        swap(array, s, l);
        return s;
    } 

    public static void swap(int[] array, int i, int s){
        int aux = array[s];
        array[s] = array[i];
        array[i] = aux;
    }
}