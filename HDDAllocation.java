package assignment2;

public class HDDAllocation {
    private final int[] hdds;
    private final int[] files;

    public HDDAllocation(int[] hdds, int[] files)
    {
        this.hdds = hdds;
        this.files = files;
    }

    public int[] generate_allocation() {
        int[] allocation = new int[files.length];
        int[] remainingSpace = hdds.clone(); // Create a copy of HDDs

        // Start at first file at index 0
        if (backtrack(0, allocation, remainingSpace)) {
            return allocation;
        }

        // No allocation
        return null; 
    }

    public boolean backtrack(int fileIndex, int[] allocation, int[] remainingSpace) {
        // Base case, if all files have already been allocated
        if (fileIndex == files.length) {
            return true;
        }

        // Get the size of the current file
        int currentFileSize = files[fileIndex];

        // Loop through each HDD trying to allocate the file
        for (int hddIndex = 0; hddIndex < hdds.length; hddIndex++) {
            // Check if file fits
            if (remainingSpace[hddIndex] >= currentFileSize) {
                allocation[fileIndex] = hddIndex;

                // Update the remaining space
                remainingSpace[hddIndex] -= currentFileSize;

                // Recursively backtrack to next file
                if (backtrack(fileIndex + 1, allocation, remainingSpace)) {
                    return true; // If true, move upwards 
                } else {
                    // Otherwise the allocation did not fit, remove the file
                    remainingSpace[hddIndex] += currentFileSize;
                }
            }
        }

        // If no allocation is possible
        return false;
    }
    
    public static void main(String[] args) {
        int[] hdds = {1000, 1000, 2000};
        int[] files = {300, 200, 300, 1200, 400, 700, 700 };
        int[] allocation = new HDDAllocation(hdds, files).generate_allocation();
        for (int i=0; i<allocation.length; i++) {
            System.out.println("File "+i+" has size " + files[i] + "MB and goes on HDD"+allocation[i] + ".");
        }
        for (int j=0; j<hdds.length; j++)
        {
            int space_used = 0;
            for (int i=0; i<allocation.length; i++) {
                if (allocation[i]==j) {
                    space_used += files[i];
                }
            }
            System.out.println("HDD"+ j + " space used " + space_used + "MB / " + hdds[j] + "MB.");
        }
    }
}
