
/**
 * Write a description of class Tag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TagsManager
{
    // instance variables - replace the example below with your own
    
    
    public static String[][] tagsOrdered = new String[][]{
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
        {"World Scales"}
        
    };

    /**
     * Constructor for objects of class Tag
     */
    public TagsManager()
    {
        // initialise instance variables
        
    }
    
    public static String[] getTagGroup(int index)
    {
        return tagsOrdered[index];
        
    }
    
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
    
    public static String curl(String[] tagGroup)
    {
        String f = "";
        for (String str : tagGroup)
        {
            f += ("{" + str + "}");
            
        }
        return f;
        
    }
}
