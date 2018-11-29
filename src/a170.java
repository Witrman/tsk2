import java.util.*;

public class a170 {
    public static void main(String[] args) {
        final int INF = Integer.MAX_VALUE / 10;

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int from = in.nextInt() - 1;
        int to = in.nextInt() - 1;

        int routesNum = in.nextInt();
        Route[] routes = new Route[routesNum];
        Cell[][] graph = new Cell[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = new Cell(new Route(0, 0));
                } else {
                    graph[i][j] = new Cell(new Route(-1, -1));
                }
            }
        }

        for (int i = 0; i < routesNum; i++) {
            int fromRoute = in.nextInt() -1;
            int startTime = in.nextInt();
            int toRoute = in.nextInt() -1;
            int endTime = in.nextInt();

            Route r = new Route(startTime, endTime);
            graph[fromRoute][toRoute].routes.add(r);
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int time = 0;

        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        dist[from] = 0;

        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < n; nv++) {
                if (!visited[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) {
                    v = nv;
                }
            }

            if (v == -1) break;
            visited[v] = true;

            for (int nv = 0; nv < n; nv++) {
                Route min = graph[v][nv].getMin(dist[v]).isPresent() ? graph[v][nv].getMin(dist[v]).get() : null;
                if (min == null) continue;
                if (!visited[nv] && min.endTime < INF && min.endTime > -1) {
                    dist[nv] = Integer.min(dist[nv], min.endTime);
                }

            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }



    }
}

class Route {

    public int startTime;
    public int endTime;

    public Route(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}

class Cell {

    public ArrayList<Route> routes = new ArrayList<>();

    public Cell(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public Cell(Route route) {
        routes.clear();
        this.routes.add(route);
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public Route get() {
        return routes.get(0);
    }

    public Optional<Route> getMin(int startTime) {

        return this.routes.stream().filter(route -> route.startTime >= startTime)
                .min(Comparator.comparingInt(route -> route.endTime));
    }
}