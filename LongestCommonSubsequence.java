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
        // TODO: Task 3
        throw new RuntimeException("Not yet implemented!");
    }

    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        String Z = new LongestCommonSubsequence(X,Y).compare();
        System.out.println("The longest common subsequence of '" +X + "' and '" + Y + "' is '" + Z + "'.");
    }
}
