package algoritmos;

public class Inversion{

    public static void main(String[] args) {
        int[] array = {8,4,3,2,1};
        System.out.println(inversion(array));
    }

    static class Array{

        int[] array;
        int inversions;

        public Array(int[] array, int inversions){
            this.array = array;
            this.inversions = inversions;
        }

    }

    /**
     * MergeSort with count of inversions.
     * @param array
     * @return
     */
    public static int inversion(int[] array){
        Array a = new Array(array, 0);
        inversion(a, 0, a.array.length - 1);
        for (int j = 0; j < a.array.length; j++) {
            System.out.println(a.array[j]);
        }   
        return a.inversions;
    }

    public static void inversion(Array array, int begin, int end){
        if (begin < end) {
            int middle = (end + begin) / 2;
            inversion(array, begin, middle);
            inversion(array, middle + 1, end);
            count(array, begin, middle, end);
        }
    }

    private static void count(Array array, int begin, int middle, int end){
        int[] sorted = new int[end - begin + 1];
        int k = 0;
        int b = begin;
        int mid = middle + 1;
        while(b < middle + 1 && mid <= end){

            if (array.array[b] > array.array[mid]) {
                array.inversions += middle - (b - 1);
                sorted[k++] = array.array[mid++];
            }
            else{
                sorted[k++] = array.array[b++];
            }

        }
        while(b < middle + 1){
            sorted[k++] = array.array[b++];
        }
        while(mid <= end){
            sorted[k++] = array.array[mid++];
        }
        copy(array, begin, end, sorted);
    }

    private static void copy(Array array, int begin, int end, int[] sorted){
        for (int i = 0; i < sorted.length; i++) {
            array.array[begin++] = sorted[i];
        }
    }
}