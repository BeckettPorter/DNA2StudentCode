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
        long targetHash = hashSingleString(target);
        int currentNumFound = 0;
        int maxFound = 0;
        long currentHash = hashSingleString(sequence.substring(0, target.length()));
        int targetLength = target.length();
        long adjustedRadix = 1;

        for (int i = 0; i < targetLength - 1; i++)
        {
            adjustedRadix *= RADIX;
        }

        for (int i = 0; i < sequence.length() - target.length(); i++)
        {
            if (currentHash == targetHash)
            {
                if (++currentNumFound > maxFound)
                {
                    maxFound = currentNumFound;
                }
                i += target.length() - 1;
            }
            else
            {
                currentNumFound = 0;
            }
            currentHash = hashNextLetter(sequence.charAt(i), sequence.charAt(i + targetLength), currentHash, adjustedRadix);
        }

        return maxFound;
    }

    // Something wrong with this I think.
    private static long hashNextLetter(char oldChar, char nextChar, long currentHash, long adjustedRadix)
    {
        // Remove the hash value for the first char.
        currentHash = ((currentHash) - mapChar(oldChar) * adjustedRadix) * RADIX;

        // Then add the next char
        currentHash += mapChar(nextChar);

        return currentHash;
    }

    private static long hashSingleString(String str)
    {
        long hash = 0;

        for (int i = 0; i < str.length(); i++)
        {
            hash = (RADIX * hash + mapChar(str.charAt(i)));
        }
        return hash;
    }

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




























    // While loop that runs as long as the index is within the length of the sequence.
//        while (index <= sequence.length() - strLength)
//        {
//            // Only bother checking for the STR if sequence at the current index is the same as the start of the STR.
//            if (sequence.charAt(index) == firstSTRLetter && STR.equals(sequence.substring(index, index + strLength)))
//            {
//                // If incrementing the current number of STRs found in a row would be greater than the current max
//                // number found in a row, set this new amount to the max found.
//                if (++currentNumFound > maxFound)
//                {
//                    maxFound = currentNumFound;
//                }
//                // Skip ahead by strLength to avoid unnecessary checks.
//                index += strLength;
//            }
//            // If the check for the STR failed, just set the current number of sequences found in a row to 0
//            // and increment index.
//            else
//            {
//                currentNumFound = 0;
//                index++;
//            }
//        }
//        return maxFound;
//    }
}
