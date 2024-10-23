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
    public static final int RADIX = 4;

    public static int STRCount(String sequence, String STR)
    {
        return searchHashes(sequence, STR);
    }

    private static int searchHashes(String sequence, String target)
    {
        // Instance variables.
        final int targetLength = target.length();
        final long targetHash = hashSingleString(target);
        final long adjustedRadix = (long) Math.pow(RADIX, targetLength - 1);
        int currentNumFound = 0;
        int maxFound = 0;
        long currentHash = hashSingleString(sequence.substring(0, target.length()));


        // Go through the entire sequence character by character.
        for (int i = 0; i < sequence.length() - target.length(); i++)
        {
            if (currentHash == targetHash)
            {
                // Check if 1 added to the current max streak found would be greater than our previously found max,
                // and if so, then set the current number to the max found.
                if (++currentNumFound > maxFound)
                {
                    maxFound = currentNumFound;
                }

                // Add the hashes for the subsequent letters that are being skipped after a match is found.
                for (int j = 0; j < targetLength; j++)
                {
                    currentHash = hashNextLetter
                            (sequence.charAt(i + j), sequence.charAt(i + j + targetLength), currentHash, adjustedRadix);
                }

                // Skip forward i by the targetLength - 1 to skip past the match.
                i += targetLength - 1;
            }
            else
            {
                // If no match was found, set the current number of matches found to 0 and just continue and hash
                // the next letter.
                currentNumFound = 0;
                currentHash = hashNextLetter
                        (sequence.charAt(i), sequence.charAt(i + targetLength), currentHash, adjustedRadix);
            }
        }

        // Fix for the case in which it returns one minus the answer if all the streaks are in a row.
        if (currentNumFound + 1 > maxFound)
        {
            return currentNumFound + 1;
        }
        // Return the highest number of target matches found.
        return maxFound;
    }

    // Method that shifts the hash by 1 char in a sequence, removing the hash from the leftmost character and adding
    // the hash from the newly added rightmost character. This prevents the need to recalculate the entire hash
    // unnecessarily.
    private static long hashNextLetter(char oldChar, char nextChar, long currentHash, long adjustedRadix)
    {
        // Remove the hash value for the first char.
        currentHash = ((currentHash) - mapChar(oldChar) * adjustedRadix);

        // Then add the hash for the new char.
        currentHash = ((currentHash * RADIX) + mapChar(nextChar));

        return currentHash;
    }

    // Method that gives me a hash for a single entire string, used to calculate the initial targetLength letters
    // in the sequence.
    private static long hashSingleString(String str)
    {
        long hash = 0;

        for (int i = 0; i < str.length(); i++)
        {
            hash = ((hash * RADIX) + mapChar(str.charAt(i)));
        }
        return hash;
    }

    // Helper method that maps the chars of a DNA sequence to values from 0 to 3.
    private static int mapChar(char c)
    {
        switch (c)
        {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return -1;
    }
}
