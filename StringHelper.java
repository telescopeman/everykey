
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
        return name.substring(0,pts[0]) + name.substring(pts[1]+1);
    }
    
    /**
     * Filters out tags from a string.
     */
    public static String filterOutTags(String name)
    {
        int[] pt = StringHelper.getEnclosers(name,"[]");
        String display_Name;
        if (pt[0] > -1)
        {
            display_Name =  StringHelper.quickSubstring(name,pt);
        }
        else
        {
            display_Name =  name;
        }
        for(boolean i = true; i;)
        {
            pt = StringHelper.getEnclosers(display_Name,"{}");
            i = false;
            if (pt[0] > -1 && pt[1] > -1)
            {
                display_Name = StringHelper.quickSubstring(display_Name,pt);
                i = true;
            }
        }
        return display_Name;
    }
    
    /**
     * Expands an array into a sentence-like list of the terms. Ex: ["X","Y","Z"] -> "X, Y, and Z."
     * @param terminator The word right before the last term. Usually 'and' or 'or'.
     */
    public static String arrayToString(String[] terms, String terminator, boolean hasPeriod)
    {
        StringBuilder name = new StringBuilder();
        int counter = 0;

        for (String term : terms)
        {
            name.append(term);
            
            if (counter < terms.length - 2) // most
            {
                name.append(", ");
            }
            else if (counter == terms.length - 2) //second to last
            {
                if (terms.length > 2)
                {
                    name.append(",");
                }
                
                name.append(" ").append(terminator).append(" ");
            }
            else if (hasPeriod)// last
            {
                name.append(".");
            }

            counter++;
        }
        return name.toString();
    }


    /**
     * Formats a tag to have curly brackets around it so that it can be properly parsed by other parts of the program.
     */
    public static String curlAll(String[] tagGroup)
    {
        StringBuilder f = new StringBuilder();
        for (String str : tagGroup)
        {
            f.append(StringHelper.enclose(str, "{}"));

        }
        return f.toString();
    }

}
