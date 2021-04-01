import java.util.HashMap;
/**
 * Class that helps access names of common keys.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public class KeyNamesHelper extends TheoryObj
{

    private int group;

    static HashMap<String,String> stringKeys;
    String lastKey;
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

        group = 0; // major

        addScale("C, D, E, F, G, A, and B.", "Ionian / Major"); // the modes of major
        addScale("C, D, E♭, F, G, A, and B♭.", "Dorian");
        addScale("C, D♭, E♭, F, G, A♭, and B♭.", "Phrygian");
        addScale("C, D, E, G♭, G, A, and B.", "Lydian");
        addScale("C, D, E, F, G, A, and B♭.", "Mixolydian");
        addScale("C, D, E♭, F, G, A♭, and B♭.", "Aeolian / Minor");
        addScale("C, D♭, E♭, F, G♭, A♭, and B♭.", "Locrian");

        group = 1; // harmonic minor

        addScale("C, D, E♭, F, G, A♭, and B.", "Harmonic Minor"); // the modes of harmonic minor
        addScale("C, D♭, E♭, F, G♭, A, and B♭.", "Locrian ♮7 [Second mode of Harmonic Minor]"); 
        addScale("C, D, E, F, A♭, A, and B.", "Ionian Augmented [Third mode of Harmonic Minor]"); 
        addScale("C, D, E♭, G♭, G, A, and B♭.", "Ukrainian Dorian/Dorian ♯4 [Fourth mode of Harmonic Minor]"); 
        addScale("C, D♭, E, F, G, A♭, and B♭.", "Phrygian Dominant [Fifth mode of Harmonic Minor]"); 
        addScale("C, E♭, E, G♭, G, A, and B.", "Lydian ♯2 [Sixth mode of Harmonic Minor]"); 
        addScale("C, D♭, E♭, E, G♭, A♭, and A.", "Ultra Locrian [Seventh mode of Harmonic Minor]"); 

        group = 2; // melodic minor

        addScale("C, D, E♭, F, G, A, and B.", "Melodic Minor"); // the modes of melodic minor
        addScale("C, D♭, E♭, F, G, A, and B♭.", "Dorian ♭2 [Second mode of Melodic Minor]"); 
        addScale("C, D, E, G♭, A♭, A, and B.", "Lydian Augmented [Third mode of Melodic Minor]"); 
        addScale("C, D, E, G♭, G, A, and B♭.", "Lydian Dominant [Fourth mode of Melodic Minor]"); 
        addScale("C, D, E, F, G, A♭, and B♭.", "Mixolydian ♭6 [Fifth mode of Melodic Minor]"); 
        addScale("C, D, E♭, F, G♭, A♭, and B♭.", "Locrian ♯2 [Sixth mode of Melodic Minor]"); 
        addScale("C, D♭, E♭, E, G♭, A♭, and B♭.", "Super Locrian [Seventh mode of Melodic Minor]"); 

        group = 3; // neapolitan scales

        addScale("C, D♭, E♭, F, G, A♭, and B.", "Neapolitan Minor"); // modes of neapolitan minor
        addScale("C, D, E, G♭, G, B♭, and B.", "Lydian ♯6 [Second mode of Neapolitan Minor]");
        addScale("C, D, E, F, A♭, A, and B♭.", "Mixolydian Augmented [Third mode of Neapolitan Minor]");
        addScale("C, D, E♭, G♭, G, A♭, and B♭.", "Romani Minor / Minor ♯4 [Fourth mode of Neapolitan Minor]");
        addScale("C, D♭, E, F, G♭, A♭, and B♭.", "Locrian Dominant [Fifth mode of Neapolitan Minor]");
        addScale("C, E♭, E, F, G, A, and B.", "Ionian ♯2 [Sixth mode of Neapolitan Minor]");
        addScale("C, D♭, D, E, G♭, A♭, and A.", "Ultra Locrian ♭♭3 [Seventh mode of Neapolitan Minor]");

        addScale("C, D♭, E♭, F, G, A, and B.", "Neapolitan Major"); // modes of neapolitan major
        addScale("C, D, E, G♭, A♭, B♭, and B.", "Leading Wholetone [Second mode of Neapolitan Major]");
        addScale("C, D, E, G♭, A♭, A, and B♭.", "Lydian Augmented Dominant [Third mode of Neapolitan Major]");
        addScale("C, D, E, G♭, G, A♭, and B♭.", "Lydian Dominant ♭6 [Fourth mode of Neapolitan Major]");
        addScale("C, D, E, F, G♭, A♭, and B♭.", "Arabian / Major Locrian [Fifth mode of Neapolitan Major]");
        addScale("C, D, E♭, E, G♭, A♭, and B♭.", "Super Locrian ♯2 [Sixth mode of Neapolitan Major]"); 
        addScale("C, D♭, D, E, G♭, A♭, and B♭.", "Super Locrian ♭♭3 [Seventh mode of Neapolitan Major]");

        group = 4; // double harmonic + international

        addScale("C, D♭, E, F, G, A♭, and B.", "Double Harmonic/Byzantine"); // modes of the double harmonic scale
        addScale("C, E♭, E, G♭, G, B♭, and B.", "Lydian ♯2,♯6 [Second mode of the Double Harmonic scale]");
        addScale("C, D♭, E♭, F♭, G, A♭, and A.", "Ultra Phrygian [Third mode of the Double Harmonic scale]");
        addScale("C, D, E♭, G♭, G, A♭, and B.", "Hungarian / \"Gypsy\" Minor [Fourth mode of the Double Harmonic scale]");
        addScale("C, D♭, E, F, G♭, A, and B♭.", "Oriental [Fifth mode of the Double Harmonic scale]"); 
        addScale("C, E♭, E, F, A♭, A, and B.", "Ionian ♯2,♯5 [Sixth mode of the Double Harmonic scale]");
        addScale("C, D♭, D, F, G♭, A♭, and A.", "Locrian ♭♭3,♭♭7 [Seventh mode of the Double Harmonic scale]");

        group = 5; //persian

        addScale("C, D♭, E, F, G, B♭, and B.", "Persian"); // modes of the persian scale
        addScale("C, E♭, E, F, G, B♭, and B.", "Ionian ♯2,♯6 [Second mode of the Persian scale]");
        addScale("C, D♭, D, G♭, G, A♭, and A.", "Ultra Phrygian ♭♭3 [Third mode of the Persian scale]");
        addScale("C, D♭, E♭, G♭, G, A♭, and A.", "Raga Todi Thaat [Fourth mode of the Persian scale]");
        addScale("C, D, F, G♭, G, B♭, and B.", "Lydian ♯3,♯6 [Fifth mode of the Persian scale]");
        addScale("C, E♭, E, F, A♭, A, and B♭.", "Mixolydian Augmented ♯2 [Sixth mode of the Persian scale]");
        addScale("C, D♭, D, F, G♭, G, and A.", "Chromatic Hypophrygian Inverse [Seventh mode of the Persian scale]"); //jesus christ wtf is this lmao

        group = 6; //hungarian + world

        addScale("C, E♭, E, G♭, G, A, and B♭.", "Hungarian / \"Gypsy\" Major"); // modes of the hungarian major scale
        addScale("C, D♭, E♭, E, G♭, G, and A.", "Ultra Locrian ♭♭6 [Second mode of Hungarian Major]"); 
        addScale("C, D, E♭, F, G♭, A♭, and B.", "Harmonic Minor ♭5 [Third mode of Hungarian Major]"); 
        addScale("C, D♭, E♭, E, G♭, A, and B♭.", "Super Locrian ♮6 [Fourth mode of Hungarian Major]"); 
        addScale("C, D, E♭, F, A♭, A, and B.", "Melodic Minor [Fifth mode of Hungarian Major]");
        addScale("C, D♭, E♭, G♭, G, A, and B♭.", "Dorian ♭2, ♯4 [Sixth mode of Hungarian Major]");
        addScale("C, D, F, G♭, A♭, A, and B.", "Nohkan Flute Scale [Seventh mode of Hungarian Major]");

        group = 7; //enigmatic + constructed

        addScale("C, D♭, E, G♭, A♭, B♭, and B.", "Enigmatic"); // modes of the enigmatic scale. names are from https://ianring.com/musictheory/scales/3411
        addScale("C, E♭, F, G, A, B♭, and B.", "Phraptian [Second mode of the Enigmatic scale]");
        addScale("C, D, E, G♭, G, A♭, and A.", "Mela Kantamani [Third mode of the Enigmatic scale]");
        addScale("C, D, E, F, G♭, G, and B♭.", "Katythian [Fourth mode of the Enigmatic scale]");
        addScale("C, D, E♭, E, F, A♭, and B♭.", "Madian [Fifth mode of the Enigmatic scale]");
        addScale("C, D♭, D, E♭, G♭, A♭, and B♭.", "Aerygian [Sixth mode of the Enigmatic scale]");
        addScale("C, D♭, D, F, G, A, and B.", "Mela Manavati [Seventh mode of the Enigmatic scale]");

        group = 8; // harmonic major + jazz

        addScale("C, D, E, F, G, A♭, and B.", "Harmonic Major"); // the modes of harmonic major
        addScale("C, D, E♭, F, G♭, A, and B♭.", "Dorian ♭5 [Second mode of Harmonic Major]"); 
        addScale("C, D♭, E♭, E, G, A♭, and B.", "Phrygian ♭4 [Third mode of Harmonic Major]"); 
        addScale("C, D, E♭, G♭, G, A, and B.", "Lydian ♭3 [Fourth mode of Harmonic Major]"); 
        addScale("C, D♭, E, F, G, A, and B♭.", "Mixolydian ♭2 [Fifth mode of Harmonic Major]"); 
        addScale("C, E♭, E, G♭, A♭, A♭, and B.", "Lydian Augmented ♯6 [Sixth mode of Harmonic Major]"); 
        addScale("C, D♭, E♭, F, G♭, A♭, and A.", "Locrian ♭♭7 [Seventh mode of Harmonic Major]");

        group = 9; // ionian flat 2 + world + jazz
        addScale("C, D♭, E, F, G, A, and B.", "Mela Suryakanta / Ionian ♭2");
        addScale("C, E♭, E, G♭, A♭, B♭, and B.", "Leading Wholetone ♯2 [Second Mode of Ionian ♭2]");
        addScale("C, D♭, E♭, F, G, A♭, and A.", "Mela Senavati / Phrygian Diminished [Third Mode of Ionian ♭2] ");
        addScale("C, D, E, G♭, G, A♭, and B.", "Harmonic Lydian [Fourth Mode of Ionian ♭2]");
        addScale("C, D, E, F, G♭, A, and B♭.", "Mixolydian ♭5 [Fifth Mode of Ionian ♭2]");
        addScale("C, D, E♭, E, G, A♭, and B♭.", "Super Minor [Sixth mode of Ionian ♭2]");
        addScale("C, E♭, E, F, G♭, A♭, and B♭.", "Locrian ♭3 [Seventh mode of Ionian ♭2]");

        group = 10; // modes of Ionian flat 5

        addScale("C, D, E, F, G♭, A, and B.", "Ionian ♭5"); 
        addScale("C, D, E♭, E, G, A, and B♭.", "Super Dorian / Bebop Minor [Second mode of Ionian ♭5]");
        addScale("C, D♭, D, F, G, A♭, and B♭.", "Phrygian ♭♭3 [Third mode of Ionian ♭5]");
        addScale("C, D♭, E, G♭, G, A, and B.", "Lydian ♭2 [Fourth mode of Ionian ♭5]"); 
        //???? insane scale
        addScale("C, D, E♭, F, G, A♭, and A.","Raga Jhankara Bhramavi / Minor Diminished [Sixth mode of Ionian ♭5]");
        addScale("C, D♭, E♭, F, G♭, G, and B♭.", "Locrian ♭♭6 [Seventh mode of Ionian ♭5]");
        group = 11; //more world stuff

        addScale("C, D, E♭, F, G, B♭, and B.", "Raga Viravasantham");

        addScale("C, D, E♭, G♭, G, A♭, and A.","Mela Syamalangi / Raga Shyamalam");
        
        addScale("C, D, E♭, G♭, G, B♭, and B.", "Lydian ♭3, ♯6");
        //addScale("C, D, E♭, G♭, G, A♭, and B.", "Algerian 1"); //could not find enough consistent info

        group = 12; //deep jazz, altered hmajor
        addScale("C, D♭, E♭, E, G♭, G, and B♭.", "Altered Harmonic Major"); // the modes of harmonic major
        addScale("C, D, E♭, F, G♭, A, and B.", "Jeths's Mode / Melodic Minor ♭5 [Second mode of Altered Harmonic Major]"); // the modes of melodic minor
        addScale("C, D♭, E♭, E, G, A, and B♭.", "Super Dorian ♭2 [Third mode of Altered Harmonic Major]");
        addScale("C, D, E♭, G♭, A♭, A, and B.", "Lydian Augmented-Diminished [Fourth mode of Altered Harmonic Major]");
        addScale("C, D♭, E, G♭, G, A, and B♭.", "Lydian Dominant ♭2 [Fifth mode of Altered Harmonic Major]"); 
        addScale("C, E♭, F, G♭, A♭, A, and B.", "Super Lydian [Sixth mode of Altered Harmonic Major]"); 
        addScale("C, D, E♭, E, G♭, A♭, and A.", "Moravian Pistalkova / Hungarian Major Inverse [Seventh mode of Altered Harmonic Major]"); 
        //addScale("C, D♭, E♭, G♭, G, A♭, and B♭.", "Black Keys + Perfect Fifth"); //Fun keys I made up
        //addScale("C, D♭, E, F, G♭, G, and B♭.", "\"The Becoming\" Scale");

        group = 13; //the modes of altered minor
        //altered minor
        //harmonic locrian
        addScale("C, D, E, F, G, B♭, and B.", "Ionian ♯6 [Third mode of Altered Minor]");

        
        group = 14;
        
        addScale("C, D♭, D, E, F, A♭, and B♭.", "The Elephant Scale");
        addScale("C, D♭, E♭, E, G, A, and B.", "Kynian");
        addScale("C, D, E♭, G♭, A♭, B♭, and B.", "Stynian");
        //elephant 4
        addScale("C, E♭, F, G, A♭, A, and B.", "Persichetti Scale [Fifth mode of the Elephant Scale]");  
        //elephant 6
        addScale("C, D, E♭, E, G♭, G, and B♭.", "Lathian [Seventh mode of the Elephant Scale]");        
        
        //the modes of major but with flatted fifths
        // addScale("C, D, E♭, F, G♭, A♭, and B♭.", "Aeolian ♭5");
        // addScale("C, D, E♭, F, G♭, A, and B♭.", "Dorian ♭5");
        // //addScale("C, D, E, G♭, G, A, and B.", "Lydian"); not possible
        // //addScale("C, D♭, E♭, F, G♭, A♭, and B♭.", "Phrygian");
        // //addScale("C, D♭, E♭, F, G♭, A♭, and B♭.", "Locrian");
        // addScale("C, D, E, F, G♭, A, and B♭.", "Mixolydian ♭5");
    }

    private void addScale(String notes, String name)
    {
        lastKey = notes;
        if (!(group > -1))
        {
            return;
        }
        if (!(stringKeys.get(notes) == null))
        {
            System.out.println("Error! Dupes @" + notes + name);
        }
        stringKeys.put(notes,name + getTags(group));
    }
    
    private String getTags(int group)
    {
        return TagsManager.curl(TagsManager.getTagGroup(group));
        
    }

    public int[] getEnclosers(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return new int[]{-1,-1};

        }
        int ind1 = str.indexOf(special.substring(0,1));
        return new int[]{ind1,str.indexOf(special.substring(1,2),ind1)};

    }

    public String[] getTags(int[] scale)
    {
        return getTags(get(scale));

    }

    public String[] getTags(String name)
    {
        String dispName = name;
        String[] result = new String[]{};
        for(boolean i = true; i == true;)
        {
            int[] pt = getEnclosers(dispName,"{}");
            i = false;
            if (pt[0] > -1 && pt[1] > -1)
            {
                result = addX(result,dispName.substring(pt[0]+1,pt[1]));
                dispName = dispName.substring(pt[1]+1);

                i = true;
            }

        }
        return result;
    }

    public static String[] addX(String[] list, String x)
    {
        int i;

        // create a new array of size n+1
        String[] newarr = new String[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }

    private String quickSubstring(String name, int[] pts)
    {
        String newName =  name.substring(0,pts[0]) + name.substring(pts[1]+1);
        return newName;

    }

    public static String get(int[] key)
    {
        var name = stringKeys.get(expand(key,false));
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
