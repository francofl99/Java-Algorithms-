public class combinatorio {


    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        System.out.println(combinatorio(n,m));
    }

    public static int combinatorio(int n, int m) {

        int[][] comb = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) comb[i][j] = 0;
                else {
                    if (j == 0 || i == j) comb[i][j] = 1;
                    else {
                        comb[i][j] = comb[i-1][j-1] + comb[i-1][j]; 
                    }
                }
            }
        }
        return comb[n][m];
    }

}
