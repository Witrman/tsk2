import java.io.*;
import java.util.*;

public class а162 {

    static int n;
    static int m;

    void solve(Scanner in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        int[][] array = new int[n][m];
        int[][] dests = new int[n][m];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                Point current = new Point(i, j);

                if (array[i][j] == 1) {
                    dests[i][j] = 0;
                    continue;
                }

                boolean[][] used = new boolean[n][m];
                Point[] queue = new Point[n * m];
                int qh = 0;
                int qt = 0;
                Point v = new Point(i, j);
                queue[qt++] = v;
                used[v.x][v.y] = true;

                lbl:
                while (qh < qt) {
                    v = queue[qh++];
                    Point[] nvs = v.getPossiblePoints();
                    for (int nvc = 0; nvc < 4; nvc++) {
                        if (nvs[nvc] != null && !used[nvs[nvc].x][nvs[nvc].y]) {
                            Point nv = nvs[nvc];

                            queue[qt++] = nv;
                            used[nv.x][nv.y] = true;

                            if (array[nv.x][nv.y] == 1) {

                                dests[i][j] = current.getDistance(nv);
                                break lbl;

                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dests[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new а162().run();
    }

    public class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getDistance(Point dest) {
            return Math.abs(this.x - dest.x) + Math.abs(this.y - dest.y);
        }

        Point[] getPossiblePoints() {
            Point[] result = new Point[4];
            int count = 0;
            if (this.x + 1 < n) {
                result[count++] = new Point(this.x + 1, this.y);
            }
            if (this.x - 1 >= 0) {
                result[count++] = new Point(this.x - 1, this.y);
            }
            if (this.y - 1 >= 0) {
                result[count++] = new Point(this.x, this.y - 1);
            }
            if (this.y + 1 < m) {
                result[count++] = new Point(this.x, this.y + 1);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            return result.append(this.x).append(" ").append(this.y).toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        }
    }
}
