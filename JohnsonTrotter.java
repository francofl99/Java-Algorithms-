package algoritmos;

import java.util.*;

public class JohnsonTrotter{

    public static void main(String[] args) {

        int[] array = {5,4,8,9,7,6,3,2,1};
        int[] order = slowSort(array);

        for (int i = 0; i < order.length; i++) {
            System.out.println(order[i]);
        }

    }

    public static int[] slowSort(int[] array){
        ArrayList<Number[]> permutatinos = johnsonTrotter(array);
        Iterator<Number[]> iter = permutatinos.iterator();
        while(iter.hasNext()){
            Number[] permutation = iter.next();
            boolean isOrder = true;
            for (int i = 0; i < permutation.length - 1; i++) {
                if (permutation[i].number > permutation[i + 1].number) {
                    isOrder = false;
                    break;
                }

            }
            if (isOrder) return permutationToArray(permutation);
        }
        return null;
    }

    private static int[] permutationToArray(Number[] permutation){
        int[] per = new int[permutation.length];
        for (int i = 0; i < per.length; i++) {
            per[i] = permutation[i].number;
        }
        return per;
    }

    private static void show(ArrayList<Number[]> permutations){
        Iterator<Number[]> iter = permutations.iterator();
        while(iter.hasNext()){
            Number[] per = iter.next();
            for (int i = 0; i < per.length; i++) {
                if (i == 0) {
                    System.out.print("[");
                }
                if (i == per.length - 1) {
                    System.out.print(per[i].number + "]");
                }
                else{
                    System.out.print(per[i].number + ", ");
                }
            }
            System.out.println();
        }
        System.out.println("\nTotal permutations number: " + permutations.size());
    }

    private static Number[] init(int[] array){
        int n = largestItem(array);
        Number[] numbers = new Number[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new Number(i + 1, -1);
        }
        return numbers;
    }

    private static int largestItem(int[] array){
        int n = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > n) n = array[i];
        }
        return n;
    }

    public static ArrayList<Number[]> johnsonTrotter(int[] array){
        if (array.length < 0) {
            throw new RuntimeException("Number must be non-negative");
        }
        Number[] numbers = init(array);
        ArrayList<Number[]> permutations = new ArrayList<Number[]>();
        permutations.add(numbers);
        int mobile;
        do{
            Number[] permutation = permutations.get(permutations.size() - 1);
            mobile = hasMobile(permutation);
            if ( mobile != -1 ) {
                createPermutation(permutation, mobile, permutations);
            }
        }while( mobile != -1 );

        return permutations;
    }

    private static void createPermutation(Number[] permutation, int mobile, ArrayList<Number[]> permutations){
        Number[] per = newPermutation(permutation, mobile);
        permutations.add(per);
    }

    private static Number[] newPermutation(Number[] permutation, int mobile){
        Number[] per = permutation.clone();
        mobile = swap(per, mobile);
        reverse(per, mobile);
        return per;
    }

    private static void reverse(Number[] per, int mobile){
        for (int i = 0; i < per.length; i++) {
            if (per[i].number > per[mobile].number) {
                per[i].arrow *= -1;
            }
        }
    }

    private static int swap(Number[] per, int mobile){
        Number aux = per[mobile];
        if (per[mobile].arrow == -1) {
            per[mobile] = per[mobile - 1];
            per[mobile - 1] = aux;
            mobile = mobile - 1;
            return mobile;
        }
        else{
            per[mobile] = per[mobile + 1];
            per[mobile + 1] = aux;
            mobile = mobile + 1;
            return mobile;
        }
    }

    private static int firstMobile(Number[] permutation){
        for (int i = 0; i < permutation.length; i++) {
            if (mobile(permutation, i)) {
                return i;
            }
        }
        return -1;
    }

    private static int hasMobile(Number[] permutation){
        int pos = firstMobile(permutation);
        if (pos == -1) return pos;
        Number mobile = permutation[pos];
        for (int i = 0; i < permutation.length; i++) {
            if (mobile(permutation, i)){
                if (mobile.number < permutation[i].number) {
                    mobile = permutation[i];
                    pos = i;
                }
            }
        }
        return pos;
    }

    public static boolean mobile(Number[] permutation, int pos){
        if (pos > 0 && permutation[pos].arrow == -1) {
            if (permutation[pos].number > permutation[pos - 1].number) return true;
        }
        else if (pos < permutation.length - 1 && permutation[pos].arrow == 1){
            if( permutation[pos].number > permutation[pos + 1].number) return true;
        }
        return false;
    }

    static class Number{

        public int number;
        public int arrow;

        public Number(int number, int arrow){
            this.arrow = arrow;
            this.number = number;
        }

    }
}