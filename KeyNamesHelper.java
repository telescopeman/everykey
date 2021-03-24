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
    private final int[] lydian = {1,3,5,7,8,10,12};
    
    
    
    
    static HashMap<int[],String> commonKeys;
    
    static HashMap<String,String> stringKeys;
    /**
     * I don't know why I need to put this in another class but that's just how it is i guess
     */
    public KeyNamesHelper()
    {
        commonKeys = new HashMap<int[],String>();
        commonKeys.put(majorKey, "Major");
        commonKeys.put(minorKey, "Minor");
        commonKeys.put(lydian, "Lydian");
        
        stringKeys = new HashMap<String,String>();
        stringKeys.put("C, D, E, F, G, A, and B.", "Major (Ionian)"); //the modes of major
        stringKeys.put("C, D, Eb, F, G, Ab, and Bb.", "Minor (Aeolian)");
        stringKeys.put("C, D, Eb, F, G, A, and Bb.", "Dorian");
        stringKeys.put("C, D, E, Gb, G, A, and B.", "Lydian");
        stringKeys.put("C, Db, Eb, F, G, Ab, and Bb.", "Phrygian");
        stringKeys.put("C, Db, Eb, F, Gb, Ab, and Bb.", "Locrian");
        stringKeys.put("C, Db, Eb, F, G, A, and Bb.", "Mixolydian");
        
        stringKeys.put("C, D, Eb, F, G, Ab, and B.", "Harmonic Minor"); //the modes of harmonic minor
        stringKeys.put("C, Db, Eb, F, Gb, A, and Bb.", "Locrian ♮7 (Second mode of Harmonic Minor)"); 
        stringKeys.put("C, D, E, F, Ab, A, and B.", "Ionian #5 (Third mode of Harmonic Minor)"); 
        stringKeys.put("C, D, Eb, Gb, G, A, and Bb.", "Dorian #4 (Fourth mode of Harmonic Minor)"); 
        stringKeys.put("C, Db, E, F, G, Ab, and Bb.", "Phrygian Dominant (Fifth mode of Harmonic Minor)"); 
        stringKeys.put("C, Eb, E, Gb, G, A, and B.", "Lydian #2 (Sixth mode of Harmonic Minor)"); 
        stringKeys.put("C, Db, Eb, E, Gb, Ab and A.", "Super Locrian bb7 (Seventh mode of Harmonic Minor)"); 
        
        stringKeys.put("C, D, Eb, F, G, A, and B.", "Melodic Minor"); //the modes of melodic minor
        stringKeys.put("C, Db, Eb, F, G, A, and Bb.", "Dorian b2 (Second mode of Melodic Minor)"); 
        stringKeys.put("C, D, E, Gb, Ab, A, and B.", "Lydian #5 (Third mode of Melodic Minor)"); 
        stringKeys.put("C, D, E, Gb, G, A, and Bb.", "Lydian Dominant (Fourth mode of Melodic Minor)"); 
        stringKeys.put("C, Db, Eb, F, G, Ab, and Bb.", "Mixolydian b6 (Fifth mode of Melodic Minor)"); 
        stringKeys.put("C, D, Eb, F, Gb, Ab, and Bb.", "Locrian #2 (Sixth mode of Melodic Minor)"); 
        stringKeys.put("C, Db, Eb, E, Gb, Ab and Bb.", "Super Locrian (Seventh mode of Melodic Minor)"); 
    }
    
    
    
    // public static String get(int[] key)
    // {
        // var name = commonKeys.get(key);
        // System.out.println(MathHelper.expand(key) + ": " + name);
        // if (name == null)
        // {
            // return "";
        // }
        // else
        // {
            // return name;
        // }
    // }

    public static String get(int[] key)
    {
        var name = stringKeys.get(MathHelper.expand(key));
        //System.out.println(MathHelper.expand(key) + ": " + name);
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
