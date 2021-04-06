
/**
 * Manages the tags of scales.
 *
 * @author Caleb Copeland
 * @version 4/5/21
 */
public abstract class TagsManager
{
    // instance variables - replace the example below with your own
    
    /**
     * The tags.
     */
    public static final String[][] tagsOrdered = new String[][]{
        {"Modes of Major"},
        {"Modes of Harmonic Minor","Jazz Scales"},
        {"Modes of Melodic Minor","Jazz Scales"},
        {"Modes of the Neapolitan scales","Deeper Jazz Scales"},
        {"Modes of the Double Harmonic scale","World Scales"},
        {"Modes of the Persian scale","World Scales"},
        {"Modes of Hungarian major","World Scales"},
        {"Modes of the Enigmatic scale","Constructed Scales"},
        {"Modes of the Harmonic Major","Deeper Jazz Scales"},
        {"Modes of Major ♭2","Deeper Jazz Scales","World Scales"},
        {"Modes of Major ♭5","Deeper Jazz Scales","World Scales"},
        {"World Scales"},
        {"Modes of Altered Harmonic Major","Deeper Jazz Scales"},
        {"Modes of Altered Minor","Deeper Jazz Scales"},
        {"Modes of the Elephant Scale","Constructed Scales"}
        
    };

    /**
     * Gets the tags of a group of scales at a certain index.
     */
    public static String[] getTagGroup(int index)
    {
        return tagsOrdered[index];
        
    }
    
    /**
     * Gets a list of every unique tag.
     */
    public static String[] getAllTags()
    {
        String[] result = new String[]{};
        for(String[] layer : tagsOrdered)
        {
            for (String tag : layer)
            {
                boolean addable = true;
                for (String t : result)
                {
                    if (t.equals(tag))
                    {
                        addable = false;
                    }
                }
                if (addable)
                {
                    result = ArrayHelper.addX(result,tag);
                }
                //System.out.println(tag);
            }
        }
        
        return result;
    }
    
    /**
     * Formats a tag to have curly brackets around it so that it can be properly parsed by other parts of the program.
     */
    public static String curlAll(String[] tagGroup)
    {
        String f = "";
        for (String str : tagGroup)
        {
            f += StringHelper.enclose(str,"{}");
            //("{" + str + "}");
            
        }
        return f;
    }
    
    
}
