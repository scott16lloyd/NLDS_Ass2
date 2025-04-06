package assignment2;

public class LongestCommonSubsequence {
    private final String X;
    private final String Y;

    public LongestCommonSubsequence(String X, String Y)
    {
        this.X = X;
        this.Y = Y;
    }

    public String compare() {
        int[][] c = new int[X.length() + 1][Y.length() + 1];
        String[][] s = new String[X.length() + 1][Y.length() + 1];

        // Initialise first row and column
        for (int i = 0; i <= X.length(); i++) {
            c[i][0] = 0;
            s[i][0] = "";
        }

        for (int j = 0; j <= Y.length(); j++) {
            c[0][j] = 0;
            s[0][j] = "";
        }

        // Loop through table row by row
        for (int i = 1; i <= X.length(); i++) {
            for (int j = 1; j <= Y.length(); j++) {
                // If two characters are equal increase the LCS length
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    s[i][j] = s[i - 1][j - 1] + X.charAt(i - 1);
                } else {
                    // Otherwise take the maximum of two previous cases
                    if (c[i - 1][j] >= c[i][j - 1]) {
                        c[i][j] = c[i - 1][j];
                        s[i][j] = s[i - 1][j];
                    } else {
                        c[i][j] = c[i][j - 1];
                        s[i][j] = s[i][j - 1];
                    }
                }
            }
        }
        return s[X.length()][Y.length()];
    }

    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        String Z = new LongestCommonSubsequence(X,Y).compare();
        System.out.println("The longest common subsequence of '" +X + "' and '" + Y + "' is '" + Z + "'.");
    }
}
