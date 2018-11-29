import java.io.*;
import java.util.*;

public class a176 {

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();

        int[][] ar = new int[n][n];
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ar[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            colors[i] = in.nextInt();
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ar[i][j] == 1 && colors[i] != colors[j])
                    count++;

            }
        }

        count /= 2;
        out.println(count);

    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a176().run();
    }
}
