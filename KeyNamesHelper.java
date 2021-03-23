import java.util.HashMap;
/**
 * Write a description of class KeyNamesHelper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KeyNamesHelper 
{
    // instance variables - replace the example below with your own
    private final int[] majorKey = {1,3,5,6,8,10,12};
    private final int[] minorKey = {1,3,4,6,8,9,11};
    
    static HashMap<int[],String> commonKeys;
    /**
     * I don't know why I need to put this in another class but that's just how it is i guess
     */
    public KeyNamesHelper()
    {
        commonKeys = new HashMap<int[],String>();
        commonKeys.put(majorKey, "Major");
        commonKeys.put(minorKey, "Minor");
    }
    
    public static String get(int[] key)
    {
        return commonKeys.get(key);
        
    }

}
