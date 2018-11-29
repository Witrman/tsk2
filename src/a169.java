import java.util.Arrays;
import java.util.Scanner;

public class a169 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nb = in.nextInt();
        int INF = Integer.MAX_VALUE / 10;

        int[] citiesPrices = new int[nb];
        for (int i = 0; i < nb; i++) {
            citiesPrices[i] = in.nextInt();
        }

        int[][] graph = new int[nb][nb];
        boolean[] vis = new boolean[nb];
        int[] dist = new int[nb];

        Arrays.fill(dist, INF);
        Arrays.fill(vis, false);

        dist[0] = 0;

        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = -1;
                }
            }
        }

        int rn = in.nextInt();
        int first, second;

        for (int i = 0; i < rn; i++) {
            first = in.nextInt() - 1;
            second = in.nextInt() - 1;

            graph[first][second] = citiesPrices[first];
            graph[second][first] = citiesPrices[second];
        }

        for (; ; ) {
            int v = -1;
            for (int nvi = 0; nvi < nb; nvi++) {
                if (!vis[nvi] && dist[nvi] < INF && (v == -1 || dist[v] > dist[nvi]))
                    v = nvi;
            }
            if (v == -1) break;
            vis[v] = true;

            for (int nvi = 0; nvi < nb; nvi++) {
                if (!vis[nvi] && graph[v][nvi] < INF && graph[v][nvi] > -1)
                    dist[nvi] = Integer.min(dist[nvi], dist[v] + graph[v][nvi]);
            }
        }

        for (int i = 0; i < nb; i++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }

        System.out.println(dist[nb - 1]);

    }
}
