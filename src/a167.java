import java.util.Scanner;

public class a167 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int first, second;
        int max = -1;
        int index = -1;
        for (int i = 1; i <= n; i++) {
            first = in.nextInt();
            second = in.nextInt();
            if (second == 1 && first > max) {
                max = first;
                index = i;
            }
        }
        System.out.println(index);
    }
}
