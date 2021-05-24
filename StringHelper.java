
/**
 * Helps with common String methods.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 * @since 4/6/21
 */
public abstract class StringHelper
{
    /**
     * Gets the locations of two special enclosing characters in a string.
     */
    public static int[] getEnclosers(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return new int[]{-1,-1};
        }
        return getEnclosers(str,special.charAt(0),special.charAt(1));
    }

    /**
     * Gets the locations of two special enclosing characters in a string.
     */
    public static int[] getEnclosers(String str, char special1, char special2)
    {
        if (special1 ==(special2))
        {
            return new int[]{-1,-1};
        }
        int ind1 = str.indexOf(special1);
        return new int[]{ind1,str.indexOf(special2)};
    }

    public static String enclose(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return str;
        }
        return enclose(str,special.charAt(0),special.charAt(1));
    }
    
    public static String enclose(String str, char special1, char special2)
    {
        return special1 + str + special2;
    }

    /**
     * Gets a String excluding the parts between indexes
     */
    public static String quickSubstring(String name, int[] pts)
    {
        String newName =  name.substring(0,pts[0]) + name.substring(pts[1]+1);
        return newName;
    }
    
    /**
     * Filters out tags from a string.
     */
    public static String filterOutTags(String name)
    {
        int[] pt = StringHelper.getEnclosers(name,"[]");
        String dispName;
        if (pt[0] > -1)
        {
            dispName =  StringHelper.quickSubstring(name,pt);
        }
        else
        {
            dispName =  name;
        }
        for(boolean i = true; i;)
        {
            pt = StringHelper.getEnclosers(dispName,"{}");
            i = false;
            if (pt[0] > -1 && pt[1] > -1)
            {
                dispName = StringHelper.quickSubstring(dispName,pt); 
                i = true;
            }
        }
        return dispName;
    }
    
    /**
     * Expands an array into a sentence-like list of the terms. Ex: ["X","Y","Z"] -> "X, Y, and Z."
     * @param terminator The word right before the last term. Usually 'and' or 'or'.
     */
    public static String arrayToString(String[] terms, String terminator, boolean hasPeriod)
    {
        String name = "";
        int counter = 0;
        
        System.out.println(terms.length);
        for (String term : terms)
        {
            name = name + term;
            
            if (counter < terms.length - 2) // most
            {
                name = name + ", ";
            }
            else if (counter == terms.length - 2) //second to last
            {
                if (terms.length > 2)
                {
                    name = name + ",";
                }
                
                name = name + " " + terminator + " ";
            }
            else if (hasPeriod)// last
            {
                name = name + ".";
            }

            counter++;
        }
        return name;
    }
}
