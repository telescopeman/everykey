import java.util.HashMap;
/**
 * Class that helps with enharmonics.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnharmonicsHelper
{
    // instance variables - replace the example below with your own
    private int x;
    
    private static HashMap<String,String> enharmonics;
    /**
     * Sets up the class.
     */
    public EnharmonicsHelper()
    {
        enharmonics = new HashMap<String,String>();
        enharmonics.put("D♭", "C♯");
        
        enharmonics.put("E♭", "D♯");
        enharmonics.put("E", "D♯♯");
        
        enharmonics.put("F", "E♯");
        
        
        enharmonics.put("G♭", "F♯");
        
        enharmonics.put("G", "F♯♯");
        
        enharmonics.put("A♭", "G♯");
        
        
        enharmonics.put("B♭", "A♯");
        
    }

    
    /**
     * Gets the enharmonic equivalent of a given note.
     */
    public String getEnharmonic(String note)
    {
        String name = enharmonics.get(note);
        
        if (name == null)
        {
            return "";
        }
        else
        {
            return name;
        }
    }
}
