
/**
 * Helps with common String methods
 *
 * @author Caleb Copeland
 * @version 4/6/21
 * @since 4/6/21
 */
public abstract class StringHelper
{
    
    public static int[] getEnclosers(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return new int[]{-1,-1};
        }
        int ind1 = str.indexOf(special.substring(0,1));
        return new int[]{ind1,str.indexOf(special.substring(1,2),ind1)};
    }
    
    public static String enclose(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return str;
        }
        return special.charAt(0) + str + special.charAt(1);
    }
    
    public static String quickSubstring(String name, int[] pts)
    {
        String newName =  name.substring(0,pts[0]) + name.substring(pts[1]+1);
        return newName;

    }
}
