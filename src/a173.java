import java.io.*;
import java.util.*;

public class a173 {

    void solve(Scanner in, PrintWriter out) {

        final int INF = 100000000;

        int n = in.nextInt();
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
                if (graph[i][j] == 0 && (i != j)) {
                    graph[i][j] = INF;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] < INF && graph[k][j] < INF) {
                        graph[i][j] = Integer.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                    if (graph[i][j] < -INF) {
                        graph[i][j] = -INF;
                    }

                }
            }
        }

        int[][] res = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == INF) res[i][j] = 0;
                else res[i][j] = 1;
                for (int k = 0; k < n; k++) {
                    if ((graph[k][k] < 0) && (graph[i][k] < INF) && (graph[k][j] < INF))
                        res[i][k] = res[i][j] = res[k][j] = 2;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(res[i][j] + " ");
            }
            out.println();
        }


    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a173().run();
    }
}
