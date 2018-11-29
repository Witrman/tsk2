import java.io.*;
import java.util.*;

public class a174 {

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int sum = 0;
        for (int i = 0; i < n * n; i++) {
            sum += in.nextInt();
        }
        out.print(sum / 2);
    }

    void run() {
        try (Scanner in = new Scanner(System.in);
             PrintWriter out = new PrintWriter(System.out)) {

            solve(in, out);
        }
    }

    public static void main(String[] args) {
        new a174().run();
    }
}
