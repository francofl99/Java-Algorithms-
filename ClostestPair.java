package algoritmos;

import java.util.*;

public class ClostestPair{

    public static void main(String[] args) {

        Tuple a = new Tuple(1, 2);
        Tuple b = new Tuple(5, 10);
        Tuple c = new Tuple(8, 3);
        Tuple d = new Tuple(7, 9);
        Tuple[] p = {a,b,c,d};

        Tuple aa = new Tuple(1, 2);
        Tuple cc = new Tuple(8, 3);
        
        Tuple dd = new Tuple(7, 9);
        Tuple bb = new Tuple(5, 10);


        Tuple[] q = {aa,bb,cc,dd};

        System.out.println(closestPair(p, q));
    }

    /**
     * Class wich represent a plane point
     */
    static class Tuple{

        public double x;
        public double y;

        public Tuple(double x, double y){
            this.x = x;
            this.y = y;
        }

    }
    /**
     * Copy an array into another
     * @param array array to copy
     * @param from array begin
     * @param end array end
     * @return new array with elements copied
     */
    private static Tuple[] copy(Tuple[] array, int from, int end){
        Tuple[] a = new Tuple[end - from];
        int j = from;
        for (int i = 0; i < a.length; i++) {
            a[i] = array[j];
            j++;
        }
        return a;
    }
    /**
     * Brute force calulation for closest pair distance 
     * @param p point list 
     * @return smallest distance between a point pair
     */
    public static double bruteForceCosestPair(Tuple[] p){
        double d = Double.MAX_VALUE;
        for (int i = 0; i < p.length - 1; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double distance = dist(p[j], p[i]);                
                d = Math.min(d, distance);
            }
        }
        return d;
    }
    /**
     * MergeSort
     * @param array array to sorted
     * @param begin original array begin 
     * @param end original array end
     * @param c sorted on fuction of coordinated x or y
     */
    public static void mergeSort(Tuple[] array, int begin, int end, char c){
        if (begin < end) {
            int middle = (end + begin) / 2;
            mergeSort(array, begin, middle, c);
            mergeSort(array, middle + 1, end, c);
            merge(array, begin, middle, end, c);
        }
    }
    /**
     * Merge fuction for mergeSort
     * @param array array to sorted
     * @param begin array begin 
     * @param middle array middle
     * @param end array end
     * @param c sorted on fuction of coordinated x or y
     */
    private static void merge(Tuple[] array, int begin, int middle, int end, char c){
        Tuple[] sorted = new Tuple[end - begin + 1];
        int k = 0;
        int b = begin;
        int mid = middle + 1;
        if (c == 'x') {
            while(b < middle + 1 && mid <= end){
                if (array[b].x > array[mid].x) {
                    sorted[k++] = array[mid++];
                }
                else{
                    sorted[k++] = array[b++];
                }
            }    
        }
        else{
            while(b < middle + 1 && mid <= end){
                if (array[b].y > array[mid].y) {
                    sorted[k++] = array[mid++];
                }
                else{
                    sorted[k++] = array[b++];
                }
            }
        }
        while(b < middle + 1){
            sorted[k++] = array[b++];
        }
        while(mid <= end){
            sorted[k++] = array[mid++];
        }
        copy(array, begin, end, sorted);
    }
    /**
     * Copy an array to other array
     * @param array array to modified
     * @param begin begin of element range 
     * @param end end of element range
     * @param sorted sorted auxiliar array
     */
    private static void copy(Tuple[] array, int begin, int end, Tuple[] sorted){
        for (int i = 0; i < sorted.length; i++) {
            array[begin++] = sorted[i];
        }
    }
    /**
     * Solves the closest-pair problem
     * @param p An array of n ≥ 2 points sorted in nondecreasing order of their * x coordinates     
     * @param q An array Q of the same points sorted in nondecreasing order *  * of the y coordinates
     * @return Euclidean distance between the closest pair of points
     */
    public static double closestPair(Tuple[] p, Tuple[] q){
        // range condition
        if (p.length < 2) {
            throw new RuntimeException("Cant of points must be >= 2");
        }
        // order p and q
        mergeSort(p, 0, p.length - 1, 'x');
        mergeSort(q, 0, q.length - 1, 'y');
        // calculate
        return closestPairRecursive(p, q);
    }
    /**
     * Auxiliar closestPair Function. Solves the closest-pair problem by
     * divide and conquer technique      
     */
    public static double closestPairRecursive(Tuple[] p, Tuple[] q){
        // Base case
        if (p.length <= 3) {
            return bruteForceCosestPair(p);
        }
        // Recursion solved
        Tuple[] pl = copy(p, 0, p.length / 2);
        Tuple[] pr = copy(p, p.length / 2, p.length);
        Tuple[] ql = copy(q, 0, q.length / 2);
        Tuple[] qr = copy(q, q.length / 2, q.length);
        // Assign distances
        double dl = closestPairRecursive(pl, ql);
        double dr = closestPairRecursive(pr, qr);
        // Take smallest distance between parts
        double d = Math.min(dl, dr);
        // Take array medium element
        double m = p[p.length / 2 - 1].x;
        // Assign division line points
        Tuple[] s = new Tuple[p.length];
        int i = 0;
        int j = 0;
        while(j < q.length){
            if (Math.abs(q[j].x - m) < d) {
                s[i++] = q[j++];
            }
        } 
        // Final value
        return Math.min(d, stripClosest(s, j, d));
    }
    /**
     * Calculate distance of points on medium line and complete the calculate of
     * closest pair problem
     * @return final value of closest pair on the plane
     */
    private static double stripClosest(Tuple[] s, int n, double d){
        double min = d;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && (s[j].y - s[i].y) < min; j++) {
                if (dist(s[i], s[j]) < min) {
                    min = dist(s[i], s[j]);
                }
            }
        }
        return min;
    }
    /**
     * Calculate distance between two points
     * @param a first point 
     * @param b second point
     * @return distance between them
     */
    private static double dist(Tuple a, Tuple b){
        return Math.sqrt(((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)));
    }
}