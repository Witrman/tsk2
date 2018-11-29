import java.util.*;
import java.util.stream.Stream;

public class a178 {
    static final int INF = 30000;

    public static class Edge {
        int v, cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static boolean bellmanFord(List<Edge>[] graph, int s, int[] dist, int[] pred) {
        Arrays.fill(pred, -1);
        Arrays.fill(dist, INF);
        dist[s] = 0;
        int n = graph.length;
        boolean updated = false;
        for (int step = 0; step < n; step++) {
            updated = false;
            for (int u = 0; u < n; u++) {
                if (dist[u] == INF) continue;
                for (Edge e : graph[u]) {
                    if (dist[e.v] > dist[u] + e.cost) {
                        dist[e.v] = dist[u] + e.cost;
                        dist[e.v] = Math.max(dist[e.v], -INF);
                        pred[e.v] = u;
                        updated = true;
                    }
                }
            }
            if (!updated)
                break;
        }
        return updated == false;
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
                if (dist[u] == INF) continue;
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
        int v = in.nextInt();
        List<Edge>[] graph = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

        for (int i = 0; i < v; i++) {
            graph[in.nextInt() - 1].add(new Edge(in.nextInt() - 1, in.nextInt()));
        }

        int[] dist = new int[n];
        int[] pred = new int[n];
        int s = 0;

        bellmanFord(graph, s, dist, pred);

        for (int i = 0; i < n; i++) {
            System.out.print(dist[i] + " ");
        }
    }
}