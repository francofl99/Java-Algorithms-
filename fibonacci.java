public class fibonacci {


    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        System.out.println(fibonacci(x));
    }

    public static int fibonacci(int x) {

        int t1 = 1;
        int t2 = 1;
        for (int i = 2; i <= x; i++) {
           int aux = t1+t2;
           t1 = t2;
           t2 = aux; 
        }
        return t2; 
        /*
        if (x == 0) return 1;
        if (x == 1) return 1;
        return (fibonacci(x-1)+fibonacci(x-2));
        */
    }

}
