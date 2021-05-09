import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class SquareSum {

    public static boolean isSquareSum(int num) {
        // find the maximum of one component
        int maxVal = (int) Math.sqrt(num);

        // add all squared values from 0 to maxVal in a set
        Set<Integer> squareNums = new HashSet<>();
        for (int val = 0; val <= maxVal; val++) {
            squareNums.add(val * val);
        }

        // treat as a two-sum problem
        for (int val : squareNums) {
            if (squareNums.contains(num - val)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int num = sc.nextInt();
            if (num < 0) {
                System.out.println("Invalid input: should be non-negative!");
                continue;
            }
            System.out.println(SquareSum.isSquareSum(num));
        }
    }
}
