import java.io.*;
import java.util.*;

public class a172 {

    void solve(Scanner in, PrintWriter out) {

        int n = in.nextInt();
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
                if (graph[i][j] == -1) graph[i][j] = Integer.MAX_VALUE;
            }
        }


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] < Integer.MAX_VALUE && graph[k][j] < Integer.MAX_VALUE)
                        graph[i][j] = Integer.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != Integer.MAX_VALUE)
                    max = Integer.max(max, graph[i][j]);
            }
        }

        out.print(max);


    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a172().run();
    }
}
