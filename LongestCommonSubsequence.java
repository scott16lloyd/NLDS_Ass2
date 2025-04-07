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
        // Create a table the current LCS and the final LCS
        // + 1 used to accomodate for the base case 0
        int[][] c = new int[X.length() + 1][Y.length() + 1]; // Store length of current LCS
        String[][] s = new String[X.length() + 1][Y.length() + 1]; // Store final LCS

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
                    c[i][j] = c[i - 1][j - 1] + 1; // Add 1 to the diagonal value
                    s[i][j] = s[i - 1][j - 1] + X.charAt(i - 1); // Add the matched character to the LCS
                } else {
                    // Otherwise take the maximum of two previous cases
                    if (c[i - 1][j] >= c[i][j - 1]) {
                        c[i][j] = c[i - 1][j]; // Value from cell above
                        s[i][j] = s[i - 1][j]; // LCS from cell above
                    } else {
                        c[i][j] = c[i][j - 1]; // Value from cell on the left
                        s[i][j] = s[i][j - 1]; // LCS from cell on the left
                    }
                }
            }
        }
        return s[X.length()][Y.length()]; // Return LCS
    }

    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        String Z = new LongestCommonSubsequence(X,Y).compare();
        System.out.println("The longest common subsequence of '" +X + "' and '" + Y + "' is '" + Z + "'.");
    }
}
