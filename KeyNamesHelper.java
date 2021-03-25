import java.util.HashMap;
/**
 * Class that helps access names of common keys.
 *
 * @author (your name)
 * @version (a version num♭er or a date)
 */
public class KeyNamesHelper 
{
    // instance varia♭les - replace the example ♭elow with your own
    private final int[] majorKey = {1,3,5,6,8,10,12};
    private final int[] minorKey = {1,3,4,6,8,9,11};
    private final int[] lydian = {1,3,5,7,8,10,12};
    
    
    
    
    static HashMap<int[],String> commonKeys;
    
    static HashMap<String,String> stringKeys;
    
    
    /**
     * I don't know why I need to put this in another class ♭ut that's just how it is i guess
     */
    public KeyNamesHelper()
    {
        // commonKeys = new HashMap<int[],String>();
        // commonKeys.put(majorKey, "Major");
        // commonKeys.put(minorKey, "Minor");
        // commonKeys.put(lydian, "Lydian");
        
        stringKeys = new HashMap<String,String>();
        stringKeys.put("C, D, E, F, G, A, and B.", "Ionian (Major)"); // the modes of major
        stringKeys.put("C, D, E♭, F, G, A♭, and B♭.", "Aeolian (Minor)");
        stringKeys.put("C, D, E♭, F, G, A, and B♭.", "Dorian");
        stringKeys.put("C, D, E, G♭, G, A, and B.", "Lydian");
        stringKeys.put("C, D♭, E♭, F, G, A♭, and B♭.", "Phrygian");
        stringKeys.put("C, D♭, E♭, F, G♭, A♭, and B♭.", "Locrian");
        stringKeys.put("C, D, E, F, G, A, and B♭.", "Mixolydian");
        
        stringKeys.put("C, D, E♭, F, G, A♭, and B.", "Harmonic Minor"); // the modes of harmonic minor
        stringKeys.put("C, D♭, E♭, F, G♭, A, and B♭.", "Locrian ♮7 (Second mode of Harmonic Minor)"); 
        stringKeys.put("C, D, E, F, A♭, A, and B.", "Ionian ♯5 (Third mode of Harmonic Minor)"); 
        stringKeys.put("C, D, E♭, G♭, G, A, and B♭.", "Dorian ♯4 (Fourth mode of Harmonic Minor)"); 
        stringKeys.put("C, D♭, E, F, G, A♭, and B♭.", "Phrygian Dominant (Fifth mode of Harmonic Minor)"); 
        stringKeys.put("C, E♭, E, G♭, G, A, and B.", "Lydian ♯2 (Sixth mode of Harmonic Minor)"); 
        stringKeys.put("C, D♭, E♭, E, G♭, A♭, and A.", "Super Locrian ♭♭7 (Seventh mode of Harmonic Minor)"); 
        
        stringKeys.put("C, D, E♭, F, G, A, and B.", "Melodic Minor"); // the modes of melodic minor
        stringKeys.put("C, D♭, E♭, F, G, A, and B♭.", "Dorian ♭2 (Second mode of Melodic Minor)"); 
        stringKeys.put("C, D, E, G♭, A♭, A, and B.", "Lydian ♯5 (Third mode of Melodic Minor)"); 
        stringKeys.put("C, D, E, G♭, G, A, and B♭.", "Lydian Dominant (Fourth mode of Melodic Minor)"); 
        stringKeys.put("C, D♭, E♭, F, G, A♭, and B♭..", "Mixolydian ♭6 (Fifth mode of Melodic Minor)"); 
        stringKeys.put("C, D, E♭, F, G♭, A♭, and B♭.", "Locrian ♯2 (Sixth mode of Melodic Minor)"); 
        stringKeys.put("C, D♭, E♭, E, G♭, A♭, and B♭.", "Super Locrian (Seventh mode of Melodic Minor)"); 
        
        stringKeys.put("C, D♭, E, G♭, A♭, B♭, and B.", "Enigmatic"); // odd historical keys
        stringKeys.put("C, D♭, E, F, G, A♭, and B.", "Gypsy/Byzantine");
        stringKeys.put("C, D, E, F, G♭, A♭, and B♭.", "Arabian (Major Locrian)");
        stringKeys.put("C, D♭, E, F, G, B♭, and B.", "Persian");
        stringKeys.put("C, D, E♭, G♭, G, A♭, and B♭.", "Hungarian");
        
        stringKeys.put("C, D♭, E♭, G♭, G, A♭, and B♭.", "Black Keys + Perfect Fifth"); //Fun keys
        stringKeys.put("C, D♭, E, F, G♭, G, and B♭.", "\"The Becoming\" Scale");
        
        // stringKeys.put("C, D, E, F, G♭, A, and B.", "Ionian ♭5"); //the modes of major but with flatted fifths
        // stringKeys.put("C, D, E♭, F, G♭, A♭, and B♭.", "Aeolian ♭5");
        // stringKeys.put("C, D, E♭, F, G♭, A, and B♭.", "Dorian ♭5");
        // //stringKeys.put("C, D, E, G♭, G, A, and B.", "Lydian"); not possible
        // //stringKeys.put("C, D♭, E♭, F, G♭, A♭, and B♭.", "Phrygian");
        // //stringKeys.put("C, D♭, E♭, F, G♭, A♭, and B♭.", "Locrian");
        // stringKeys.put("C, D, E, F, G♭, A, and B♭.", "Mixolydian ♭5");
    }
    
    
    
    // pu♭lic static String get(int[] key)
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
        var name = stringKeys.get(MathHelper.expand(key,false));
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
