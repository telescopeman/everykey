
/**
 * Keeps track of the scale descriptions that have been generated to avoid unneeded repetition.
 *
 * @author Caleb Copeland
 * @version 4/5/21
 */
public class SpeedCache extends TheoryObj
{
    // instance variables - replace the example below with your own
    private static String[] cache = new String[462];
    
    private static String[] cache2 = new String[462];

    private static int num = 0;
    private static int num2= 0;
    // public static void cache(String str, int ind)
    // {
        // cache[ind] = str;
        // //log();
    // }
    
    // public static void cache2(String str, int ind)
    // {
        // cache2[ind] = str;
        // //log();
    // }
    
    public static void smartCache(String str, int ind, boolean enh)
    {
        
        if (enh)
        {
            cache[ind] = str;
            log();
        }
        else
        {
            cache2[ind] = str;
            log2();
        }
    }

    
    
    public static String smartCheck(int ind, boolean enh)
    {
        
        if (enh)
        {
            return cache[ind];
        }
        else
        {
            return cache2[ind];
        }
        
    }

    public static boolean needsCache(int ind,boolean enh)
    {
        if (enh)
        {
            return (cache[ind]==null); 
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
