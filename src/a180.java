import java.util.*;
import java.util.stream.Stream;

public class a180 {

    static final int INF = 100000;

    public static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static int[] findNegativeCycle(List<Edge>[] graph) {
        int n = graph.length;
        int[] pred = new int[n];
        Arrays.fill(pred, -1);
        int[] dist = new int[n];
        int last = -1;
        for (int step = 0; step < n; step++) {
            last = -1;
            for (int u = 0; u < n; u++) {
                if (dist[u] == INF) {
                    continue;
                }
                for (Edge e : graph[u]) {
                    if (dist[e.v] > dist[u] + e.cost) {
                        dist[e.v] = Math.max(dist[u] + e.cost, -INF);
                        dist[e.v] = Math.max(dist[e.v], -INF);
                        pred[e.v] = u;
                        last = e.v;
                    }
                }
            }
            if (last == -1)
                return null;
        }
        for (int i = 0; i < n; i++) {
            last = pred[last];
        }
        int[] p = new int[n];
        int cnt = 0;
        for (int u = last; u != last || cnt == 0; u = pred[u]) {
            p[cnt++] = u;
        }
        int[] cycle = new int[cnt];
        for (int i = 0; i < cycle.length; i++) {
            cycle[i] = p[--cnt];
        }
        return cycle;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Edge>[] graph = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
        int[][] input = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                input[i][j] = in.nextInt();
                if (input[i][j] != INF) {
                    graph[i].add(new Edge(j, input[i][j]));
                }
            }
        }

        int[] dist = new int[n];
        int[] pred = new int[n];
        int s = 0;

        int[] cycle = null;

        try {
            cycle = findNegativeCycle(graph);
        } catch (Exception e) {
            System.out.print("NO");
            return;
        }
        if (cycle == null || cycle.length == 0) {
            System.out.print("NO");
            return;
        }

        System.out.println("YES");
        System.out.println(cycle.length + 1);

        for (int i = 0; i < cycle.length; i++) {
            System.out.print(cycle[i] + 1 + " ");
        }
        System.out.print(cycle[0] + 1);

    }
}