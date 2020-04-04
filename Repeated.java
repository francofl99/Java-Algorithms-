package algoritmos;

public class Repeated{

    public static void main(String[] args) {
        
        int[] array = {1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1};

        int[] list = repeated(array);

        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i]);
        }

    }

    static class Array{

        public int[] array;
        public int size;

        public Array(int[] array, int size){
            this.array = array;
            this.size = size;
        }

    }
    public static int[] repeated(int[] array){
        Array list = new Array(array, array.length);
        for (int i = 0; i < list.size; i++) {
            int j = i + 1;
            while (j < list.size) {
                if (list.array[i] == list.array[j]) {
                    remove(list, j);                    
                }
                else{
                    j++;
                }
            }
        }
        return copy(list);
    }
    private static int[] copy(Array list){
        int[] array = new int[list.size];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.array[i];
        }
        return array;
    }
    private static void remove(Array list, int j){
        for (int i = j + 1; i < list.size; i++) {
            list.array[i - 1] = list.array[i];
        }
        list.size--;
    }
}