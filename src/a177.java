import java.io.*;
import java.util.*;

public class a177 {

    void solve(Scanner in, PrintWriter out) {

        int n = in.nextInt();
        int[][] ar = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ar[i][j] = in.nextInt();
            }
        }

        int min = 3000;
        int first = 0;
        int second = 0;
        int third = 0;

        min = 3000;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (min > ar[i][j] + ar[i][k] + ar[j][k]) {
                        min = ar[i][j] + ar[i][k] + ar[j][k];
                        first = i;
                        second = j;
                        third = k;
                    }
                }
            }

        }

        first++;
        second++;
        third++;

        out.println(first + " " + second + " " + third);

    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a177().run();
    }
}
