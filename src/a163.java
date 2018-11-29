import java.io.*;
import java.util.*;

public class a163 {

    class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        ArrayList<Point> getPossibleMoves() {
            ArrayList<Point> result = new ArrayList<>();
            if (this.x + 2 < 8 && this.y + 1 < 8) result.add(new Point(x + 2, y + 1));
            if (this.x + 2 < 8 && this.y - 1 >= 0) result.add(new Point(x + 2, y - 1));
            if (this.x + 1 < 8 && this.y + 2 < 8) result.add(new Point(x + 1, y + 2));
            if (this.x + 1 < 8 && this.y - 2 >= 0) result.add(new Point(x + 1, y - 2));
            if (this.x - 2 >= 0 && this.y + 1 < 8) result.add(new Point(x - 2, y + 1));
            if (this.x - 2 >= 0 && this.y - 1 >= 0) result.add(new Point(x - 2, y - 1));
            if (this.x - 1 >= 0 && this.y + 2 < 8) result.add(new Point(x - 1, y + 2));
            if (this.x - 1 >= 0 && this.y - 2 >= 0) result.add(new Point(x - 1, y - 2));
            return result;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) Math.pow(x, 3.0 / 2.0);
            result = prime * result + (int) Math.pow(y, 5.0 / 3.0);
            return result;
        }
    }

    static HashSet<Point> getPossibleForAll(HashSet<Point> points) {

        HashSet<Point> result = new HashSet<>();

        for (Point p : points) {
            result.addAll(p.getPossibleMoves());
        }

        return result;

    }

    void solve(Scanner in, PrintWriter out) {

        String input = in.nextLine();
        int x1 = (int) input.charAt(0) - 97;
        int y1 = (int) input.charAt(1) - 49;

        int x2 = (int) input.charAt(3) - 97;
        int y2 = (int) input.charAt(4) - 49;

        Point first = new Point(x1, y1);
        Point second = new Point(x2, y2);

        if (first.equals(second)) {
            out.print(0);
            return;
        }

        int count = 1;

        HashSet<Point> movesFirst = new HashSet<>();
        HashSet<Point> movesSecond= new HashSet<>();

        movesFirst.addAll(first.getPossibleMoves());
        movesSecond.addAll(second.getPossibleMoves());

        while (true) {

            for (Point p : movesFirst) {
                if (movesSecond.contains(p)) {
                    out.print(count);
                    return;
                }
            }

            count++;

            HashSet<Point> buffer1= new HashSet<>();
            buffer1.addAll(movesFirst);

            HashSet<Point> buffer2= new HashSet<>();
            buffer2.addAll(movesSecond);

            movesFirst.clear();
            movesSecond.clear();

            movesFirst.addAll(getPossibleForAll(buffer1));
            movesSecond.addAll(getPossibleForAll(buffer2));

            if (count > 100) {
                out.print(-1);
                return;
            }


        }





    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a163().run();
    }
}
