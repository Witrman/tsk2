import java.io.PrintWriter;
import java.util.Scanner;

public class a160 {

    void process(Scanner in, PrintWriter out) {

        int n = in.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
            }
        }

        int start = in.nextInt() - 1;
        int end = in.nextInt() - 1;

        if (start == end) {
            System.out.println("0");
            return;
        }

        boolean[] used = new boolean[n];
        int[] queue = new int[n];
        int qH = 0;
        int qT = 0;
        int v = start;

        queue[qT++] = v;
        used[v] = true;

        int[] result = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = -1;
            result[i] = -1;
        }

        d[start] = 0;

        while (qH < qT) {
            v = queue[qH++];
            for (int nv = 0; nv < n; nv++) {
                if (!used[nv] && graph[v][nv] == 1) {
                    d[nv] = d[v] + 1;
                    used[nv] = true;
                    queue[qT++] = nv;
                    if (nv == end) break;
                }
            }
        }

        int count = 0;
        result[count++] = end;
        int ind = end;

        while (d[ind] > 0) {
            for (int i = 0; i < n; i++) {
                if (d[i] != -1 && d[ind] - d[i] == 1) {
                    result[count++] = i;
                    ind = i;
                }
            }
        }

        System.out.println(d[end]);
        for (int i = n - 1; i >= 0; i--) {
            if (result[i] != -1 && d[end] != -1) {
                System.out.print(result[i] + 1);
                System.out.print(" ");
            }
        }
    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {
            process(in, out);
        }
    }

    public static void main(String[] args) {
        new a160().run();
    }
}

