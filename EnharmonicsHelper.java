import java.util.HashMap;
/**
 * Write a description of class EnharmonicsHelper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnharmonicsHelper
{
    // instance variables - replace the example below with your own
    private int x;
    
    static HashMap<String,String> enharmonics;
    /**
     * Constructor for objects of class EnharmonicsHelper
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
