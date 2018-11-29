import java.io.*;
import java.util.*;

public class a175 {

    void solve(Scanner in, PrintWriter out) {
        int sv = in.nextInt();
        int n = in.nextInt();

        int p1 = 0;
        int p2 = 0;

        int[] mas = new int[sv];
        for (int i = 0; i < n; i++) {
            p1 = in.nextInt();
            p2 = in.nextInt();
            mas[p1 - 1]++;
            mas[p2 - 1]++;
        }

        for (int i = 0; i < sv; i++) {
            out.print(mas[i]);
            out.print(" ");
        }

    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a175().run();
    }
}
