package assignment2;

import java.util.LinkedList;

public class RodCutting {
    private final int[] prices;

    public RodCutting(int[] prices)
    {
        this.prices = prices;
    }

    public LinkedList<Integer> best_cuts()
    {
        // Initialise arrays with 0 values
        int[] revenue = new int[prices.length + 1]; // +1 includes length of 0
        int[] optimalFirstCut = new int[prices.length + 1];

        revenue[0] = 0; // Revenue of length 0 = 0

        //  For each rod of length j
        for (int j = 1; j <= prices.length; j++) {
            int maxRevenue = Integer.MIN_VALUE;

            // Try each possible first cut
            for (int i = 1; i <= j; i++) {
                // Calculate revenue if cut is made of length i
                // prices[i-1] = price of rod length i
                int currentRevenue = prices[i-1] + revenue[j-i];

                // If currentRevenue is the best so far
                if (currentRevenue > maxRevenue) {
                    maxRevenue = currentRevenue;
                    optimalFirstCut[j] = i; // Best cut so far
                }
            }
            // Best revenue for length j
            revenue[j] = maxRevenue;
        }
        // Reconstruct solution
        LinkedList<Integer> bestCuts = new LinkedList<>();
        int n = prices.length;

        // While theres rod to cut
        while (n > 0) {
            // Add optimal cut at length n
            bestCuts.add(optimalFirstCut[n]);
            // Reduce the leftover length of the rod by the amount cut
            n = n - optimalFirstCut[n];
        }

        return bestCuts;
    }

    public static void main(String[] args) {
        int[] prices = {  1, 5, 8, 9, 12, 14, 17, 19, 20, 21 };
        LinkedList<Integer> cuts = new RodCutting(prices).best_cuts();
        System.out.println("The best cuts for a rod of length " + prices.length + "m are");
        int total_price=0;
        for (Integer cut : cuts)
        {
            System.out.println(" - " + cut + "m selling at €"+prices[cut-1]);
            total_price += prices[cut-1];
        }
        System.out.println("The overall price is €"+total_price+".");
    }
}