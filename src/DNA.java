/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Beckett Porter
 * Completed on: 10/9/2024
 *</p>
 */

public class DNA
{
    public static int STRCount(String sequence, String STR)
    {
        // Instance variables, I just made the STR length a final variable here because it will never be changed.
        final int strLength = STR.length();
        int index = 0;
        int currentNumFound = 0;
        int maxFound = 0;

        // While loop that runs as long as the index is within the length of the sequence.
        while (index <= sequence.length() - strLength)
        {
            // Only bother checking for the STR if sequence at the current index is the same as the start of the STR.
            if (sequence.charAt(index) == STR.charAt(0) && sequence.substring(index, index + strLength).equals(STR))
            {
                // If incrementing the current number of STRs found in a row would be greater than the current max
                // number found in a row, set this new amount to the max found.
                if (++currentNumFound > maxFound)
                {
                    maxFound = currentNumFound;
                }
                // Skip ahead by strLength to avoid unnecessary checks.
                index += strLength;
            }
            // If the check for the STR failed, just set the current number of sequences found in a row to 0
            // and increment index.
            else
            {
                currentNumFound = 0;
                index++;
            }
        }
        return maxFound;
    }
}
