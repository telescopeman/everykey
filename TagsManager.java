
/**
 * Write a description of class Tag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TagsManager
{
    // instance variables - replace the example below with your own
    public static final String[][] ALLTAGS = new String[][]{
        {"Modes of Major"},
        {"Modes of Harmonic Minor","Jazz Scales"},
        {"Modes of Melodic Minor","Jazz Scales"},
        {"Modes of the Neapolitan scales","Jazz Scales"},
        {"Modes of the Double Harmonic scale","World Scales"},
        {"Modes of the Persian scale","World Scales"},
        {"Modes of Hungarian major","Constructed Scales"},
        
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
        return ALLTAGS[index];
        
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
