import java.io.*;
import java.util.*;

public class а161 {

    class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            return res.append(this.x).append(" ").append(this.y).toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            return (((Point) obj).x == this.x && ((Point) obj).y == this.y);
        }
    }

    Point[] turns(Point p, int n) {
        Point[] result = new Point[8];
        int count = 0;

        if (p.x + 1 < n && p.y + 2 < n) {
            result[count++] = new Point(p.x + 1, p.y + 2);
        }
        if (p.x + 2 < n && p.y + 1 < n) {
            result[count++] = new Point(p.x + 2, p.y + 1);
        }
        if (p.x + 2 < n && p.y - 1 >= 0) {
            result[count++] = new Point(p.x + 2, p.y - 1);
        }
        if (p.x + 1 < n && p.y - 2 >= 0) {
            result[count++] = new Point(p.x + 1, p.y - 2);
        }

        if (p.x - 1 >= 0 && p.y - 2 >= 0) {
            result[count++] = new Point(p.x - 1, p.y - 2);
        }
        if (p.x - 2 >= 0 && p.y - 1 >= 0) {
            result[count++] = new Point(p.x - 2, p.y - 1);
        }
        if (p.x - 2 >= 0 && p.y + 1 < n) {
            result[count++] = new Point(p.x - 2, p.y + 1);
        }
        if (p.x - 1 >= 0 && p.y + 2 < n) {
            result[count++] = new Point(p.x - 1, p.y + 2);
        }

        return result;


    }

    void solve(Scanner in, PrintWriter out) {

        int n = in.nextInt();

        Point start = new Point(in.nextInt() - 1, in.nextInt() - 1);
        Point end = new Point(in.nextInt() - 1, in.nextInt() - 1);
        boolean[][] used = new boolean[n][n];
        Point[] queue = new Point[n * n];
        int[][] d = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = -100000;
            }
        }

        int qH = 0;
        int qT = 0;

        Point v = start;
        used[v.x][v.y] = true;
        d[v.x][v.y] = 0;

        queue[qT++] = start;
        Point[] pts = turns(start, n);

        while (qH < qT) {
            v = queue[qH++];
            Point[] nvs = turns(v, n);
            for (int nvc = 0; nvc < 8; nvc++) {
                Point nv = nvs[nvc];
                if (nv != null && !used[nv.x][nv.y]) {
                    d[nv.x][nv.y] = d[v.x][v.y] + 1;

                    used[nv.x][nv.y] = true;
                    queue[qT++] = nv;
                }
            }
        }

        int count = 0;

        Point[] result = new Point[d[end.x][end.y] + 1];
        result[count++] = new Point(end.x + 1, end.y + 1);


        pts = turns(end, n);
        v = end;

        for (int i = 0; i < pts.length; i++) {
            if (pts[i] != null && d[v.x][v.y] - d[pts[i].x][pts[i].y] == 1) {

                v = pts[i];
                pts = turns(v, n);
                result[count++] = new Point(v.x + 1, v.y + 1);
                i = -1;
            }
        }

        if (!start.equals(end))
            result[result.length-1] = new Point(start.x + 1, start.y + 1);

        System.out.println(d[end.x][end.y]);
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.println(result[i]);
        }

    }


    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new а161().run();
    }
}


