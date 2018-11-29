import java.util.Arrays;
import java.util.Scanner;

public class a168 {
    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE / 100;

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;
        int[][] graph = new int[n][n];
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
            }
        }
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);

        dist[s] = 0;

        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < n; nv++)
                if (!visited[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv]))
                    v = nv;
            if (v == -1) break;
            visited[v] = true;
            for (int nv = 0; nv < n; nv++)
                if (!visited[nv] && graph[v][nv] < INF && graph[v][nv] > -1)
                    dist[nv] = Integer.min(dist[nv], dist[v] + graph[v][nv]);
        }

        for (int i = 0; i < n; i ++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }

        System.out.println(dist[f]);
    }
}
