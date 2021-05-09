import java.util.Scanner;

public class SequenceSimilarity {

    public int[] findSeqSimilarity(String source, String target) {
        int m = source.length();
        int n = target.length();

        int count = 0;
        int minEditDistance = Integer.MAX_VALUE;
        int startIdx = 0;
        int length = 0;

        // Find sequence similarity between target sequences starting at different
        // index and the source sequence.
        for (int start = 0; start < n; start++) {
            int[] info = findSeqDiff(source, target.substring(start));
            int editDistance = info[0];
            if (editDistance < minEditDistance) {
                minEditDistance = editDistance;
                count = info[1];
                startIdx = start;
                length = info[2];
            }
            else if (editDistance == minEditDistance) {
                count += info[1];
                // length = Math.max(info[2], length);
                // length = info[2];
            }
        }

        return new int[]{startIdx, length, count};
    }

    /**
     * extract information from the dp table
     * @param source
     * @param target
     * @return int[3] array
     * int[0] is the min index of the similar sequence with minimum edit distance,
     * int[1] is the number of similar sequences with the same length,
     * int[2] is the maximum length of such similar sequences.
     */
    private int[] findSeqDiff(String source, String target) {
        int[][] dp = findSeqDiffMatrix(source, target);
        int m = dp.length - 1;
        int count = 1;
        int min = Integer.MAX_VALUE;
        int length = 0;
        for (int j = 1; j < dp[0].length; j++) {
            int cur = dp[m][j];
            if (cur < min) {
                min = cur;
                count = 1;
                length = j;
            }
            else if (cur == min) {
                count++;
                // length = Math.max(length, j);
                length = j;
            }
        }
        return new int[]{min, count, length};
    }

    /**
     * Construct the dp table for edit difference calculation
     * @param source
     * @param target
     * @return (source.length() + 1 * target.length() + 1) dimensional dp table
     */
    private int[][] findSeqDiffMatrix(String source, String target) {
        int m = source.length();
        int n = target.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                        Math.min(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String source = sc.nextLine();
        String target = sc.nextLine();
        SequenceSimilarity test = new SequenceSimilarity();

        int[] result = test.findSeqSimilarity(source, target);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
