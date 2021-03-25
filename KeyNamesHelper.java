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
     * Most of these are from Wikipedia.
     */
    public KeyNamesHelper()
    {
        // commonKeys = new HashMap<int[],String>();
        // commonKeys.put(majorKey, "Major");
        // commonKeys.put(minorKey, "Minor");
        // commonKeys.put(lydian, "Lydian");
        
        stringKeys = new HashMap<String,String>();
        stringKeys.put("C, D, E, F, G, A, and B.", "Ionian/Major"); // the modes of major
        stringKeys.put("C, D, E♭, F, G, A, and B♭.", "Dorian");
        stringKeys.put("C, D♭, E♭, F, G, A♭, and B♭.", "Phrygian");
        stringKeys.put("C, D, E, G♭, G, A, and B.", "Lydian");
        stringKeys.put("C, D, E, F, G, A, and B♭.", "Mixolydian");
        stringKeys.put("C, D, E♭, F, G, A♭, and B♭.", "Aeolian/Minor");
        stringKeys.put("C, D♭, E♭, F, G♭, A♭, and B♭.", "Locrian");
        
        
        
        stringKeys.put("C, D, E♭, F, G, A♭, and B.", "Harmonic Minor"); // the modes of harmonic minor
        stringKeys.put("C, D♭, E♭, F, G♭, A, and B♭.", "Locrian ♮7 [Second mode of Harmonic Minor]"); 
        stringKeys.put("C, D, E, F, A♭, A, and B.", "Ionian Augmented [Third mode of Harmonic Minor]"); 
        stringKeys.put("C, D, E♭, G♭, G, A, and B♭.", "Ukrainian Dorian/Dorian ♯4 [Fourth mode of Harmonic Minor]"); 
        stringKeys.put("C, D♭, E, F, G, A♭, and B♭.", "Phrygian Dominant [Fifth mode of Harmonic Minor]"); 
        stringKeys.put("C, E♭, E, G♭, G, A, and B.", "Lydian ♯2 [Sixth mode of Harmonic Minor]"); 
        stringKeys.put("C, D♭, E♭, E, G♭, A♭, and A.", "Ultra Locrian [Seventh mode of Harmonic Minor]"); 
        
        
        stringKeys.put("C, D, E♭, F, G, A, and B.", "Melodic Minor"); // the modes of melodic minor
        stringKeys.put("C, D♭, E♭, F, G, A, and B♭.", "Dorian ♭2 [Second mode of Melodic Minor]"); 
        stringKeys.put("C, D, E, G♭, A♭, A, and B.", "Lydian Augmented [Third mode of Melodic Minor]"); 
        stringKeys.put("C, D, E, G♭, G, A, and B♭.", "Lydian Dominant [Fourth mode of Melodic Minor]"); 
        stringKeys.put("C, D♭, E♭, F, G, A♭, and B♭..", "Mixolydian ♭6 [Fifth mode of Melodic Minor]"); 
        stringKeys.put("C, D, E♭, F, G♭, A♭, and B♭.", "Locrian ♯2 [Sixth mode of Melodic Minor]"); 
        stringKeys.put("C, D♭, E♭, E, G♭, A♭, and B♭.", "Super Locrian [Seventh mode of Melodic Minor]"); 
        
        
        stringKeys.put("C, D♭, E♭, F, G, A♭, and B.", "Neapolitan Minor"); // modes of neapolitan minor
        stringKeys.put("C, D, E, G♭, G, B♭, and B.", "Lydian ♯6 [Second mode of Neapolitan Minor]");
        stringKeys.put("C, D, E, F, A♭, A, and B♭.", "Mixolydian Augmented [Third mode of Neapolitan Minor]");
        stringKeys.put("C, D, E♭, G♭, G, A♭, and B♭.", "Romani Minor [Fourth mode of Neapolitan Minor]");
        stringKeys.put("C, D♭, E, F, G♭, A♭, and B♭.", "Locrian Dominant [Fifth mode of Neapolitan Minor]");
        stringKeys.put("C, E♭, E, F, G, A, and B.", "Ionian ♯2 [Sixth mode of Neapolitan Minor]");
        stringKeys.put("C, D♭, D, E, G♭, A♭, and A.", "Ultra Locrian ♭♭3 [Seventh mode of Neapolitan Minor]");
        

        stringKeys.put("C, D♭, E♭, F, G, A, and B.", "Neapolitan Major"); // modes of neapolitan major
        stringKeys.put("C, D, E, G♭, A♭, B♭, and B.", "Leading Wholetone/Lydian Augmented ♯6 [Second mode of Neapolitan Major]");
        stringKeys.put("C, D, E, G♭, A♭, A, and B♭.", "Lydian Augmented Dominant [Third mode of Neapolitan Major]");
        stringKeys.put("C, D, E, G♭, G, A♭, and B♭.", "Lydian Dominant ♭6 [Fourth mode of Neapolitan Major]");
        stringKeys.put("C, D, E, F, G♭, A♭, and B♭.", "Arabian/Major Locrian [Fifth mode of Neapolitan Major]");
        stringKeys.put("C, D, E♭, E, G♭, A♭, and B♭.", "Super Locrian ♯2 [Sixth mode of Neapolitan Major]"); 
        stringKeys.put("C, D♭, D, E, G♭, A♭, and B♭.", "Super Locrian ♭♭3 [Seventh mode of Neapolitan Major]");
        
        
        stringKeys.put("C, D♭, E, F, G, A♭, and B.", "Double Harmonic/Byzantine"); // modes of the double harmonic scale
        stringKeys.put("C, E♭, E, G♭, G, B♭, and B.", "Lydian ♯2,♯6 [Second mode of the Double Harmonic scale]");
        stringKeys.put("C, D♭, E♭, F♭, G, A♭, and A.", "Ultra Phrygian [Third mode of the Double Harmonic scale]");
        stringKeys.put("C, D, E♭, G♭, G, A♭, and B♭.", "Hungarian/\"Gypsy\" Minor [Fourth mode of the Double Harmonic scale]");
        
        stringKeys.put("C, D♭, E, F, G♭, A, and B♭.", "Oriental [Fifth mode of the Double Harmonic scale]");
        stringKeys.put("C, E♭, E, F, A♭, A, and B.", "Ionian ♯2,♯5 [Sixth mode of the Double Harmonic scale]");
        stringKeys.put("C, D♭, D, F, G♭, A♭, and A.", "Locrian ♭♭3,♭♭7 [Seventh mode of the Double Harmonic scale]");
        
        
        stringKeys.put("C, D♭, E, F, G, B♭, and B.", "Persian"); // modes of the persian scale
        stringKeys.put("C, E♭, E, F, G, B♭, and B.", "Ionian ♯2,♯6 [Second mode of the Persian scale]");
        stringKeys.put("C, D♭, D, G♭, G, A♭, and A.", "Ultra Phrygian ♭♭3 [Third mode of the Persian scale]");
        stringKeys.put("C, D♭, D, G♭, G, A♭, and A.", "Todi Raga [Fourth mode of the Persian scale]");
        stringKeys.put("C, D, F, G♭, G, B♭, and B.", "Lydian ♯3,♯6 [Fifth mode of the Persian scale]");
        stringKeys.put("C, E♭, E, F, A♭, A, and B♭.", "Mixolydian Augmented ♯2 [Sixth mode of the Persian scale]");
        stringKeys.put("C, D♭, D, F, G♭, G, and A.", "Chromatic Hypophrygian Inverse [Seventh mode of the Persian scale]"); //jesus christ wtf is this lmao
        
        
        stringKeys.put("C, E♭, E, G♭, G, A, and B♭.", "Hungarian/\"Gypsy\" Major"); // modes of the hungarian major scale
        stringKeys.put("C, D♭, E♭, E, G♭, G, and A.", "Ultra Locrian ♭♭6 [Second mode of Hungarian Major]"); 
        stringKeys.put("C, D, E♭, F, G♭, A♭, and B.", "Harmonic Minor ♭5 [Third mode of Hungarian Major]"); 
        stringKeys.put("C, D♭, E♭, E, G♭, A, and B♭.", "Super Locrian ♮6 [Fourth mode of Hungarian Major]"); 
        stringKeys.put("C, D, E♭, F, A♭, A, and B.", "Melodic Minor ♯5 [Fifth mode of Hungarian Major]");
        stringKeys.put("C, D♭, E♭, G♭, G, A, and B♭.", "Dorian ♭2,♯4 [Sixth mode of Hungarian Major]");
        stringKeys.put("C, D, F, G♭, A♭, A, and B.", "Nohkan Flute Scale [Seventh mode of Hungarian Major]");
        
        
        stringKeys.put("C, D♭, E, G♭, A♭, B♭, and B.", "Enigmatic"); // Other keys
        
        
        //stringKeys.put("C, D, E♭, G♭, G, A♭, and B.", "Algerian 1"); //could not find enough consistent info
        
        
        
        
        
        
        
        //stringKeys.put("C, D♭, E♭, G♭, G, A♭, and B♭.", "Black Keys + Perfect Fifth"); //Fun keys I made up
        //stringKeys.put("C, D♭, E, F, G♭, G, and B♭.", "\"The Becoming\" Scale");
        
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
