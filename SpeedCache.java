
/**
 * Keeps track of the scale descriptions that have been generated to avoid unneeded repetition.
 *
 * @author Caleb Copeland
 * @version 4/5/21
 */
public abstract class SpeedCache
{
    private static String[][] cache = new String[12][462]; // enharmonics on
    private static String[] cache2 = new String[462]; //enharmonics off

    private static int num = 0;
    private static int num2 = 0;
    private static int ofs = 0;
    
    public static void setOffset(int n)
    {
        ofs = n;
        
    }
    
    /**
     * Caches a description. Each index should only be used once if all goes well.
     * @param enh Whether the description being cached is taking enharmonics into account.
     */
    public static void smartCache(String str, int ind, boolean enh)
    {
        if (enh)
        {
            cache[ofs][ind] = str;
            //log();
        }
        else
        {
            cache2[ind] = str;
            //log2();
        }
    }
    
    
    
    /**
     * Reads the cache at a certain index.
     * @param enh Whether the description being cached is taking enharmonics into account.
     */
    public static String smartCheck(int ind, boolean enh)
    {
        if (enh)
        {
            return cache[ofs][ind];
        }
        else
        {
            return cache2[ind];
        }
    }
    
    

    /**
     * Checks to see if a certain index of the cache is used or not.
     * @param enh Whether the description being cached is taking enharmonics into account.
     */
    public static boolean needsCache(int ind, boolean enh)
    {
        if (enh)
        {
            return (cache[ofs][ind]==null); 
        }
        else
        {
            return (cache2[ind]==null);
        }
    }
    
    private static void log()
    {
        num++;
        System.out.println("Stored alpha cache #" + num);
    }
    
    private static void log2()
    {
        num2++;
        System.out.println("Stored beta cache #" + num2);
    }
}
