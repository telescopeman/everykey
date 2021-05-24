/**
 * An object capable of using deeper music theory.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public abstract class TheoryObj extends BasicTheoryObj
{
    private static String[] coolNames = new String[]{"root","second","third","fourth","fifth","sixth","seventh"};

    public static final String[] CHROMATICSCALE = 
        new String[]{"C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B"};
    

    /**
     * Gets the name of an interval.
     */
    protected static String getIntervalName(int index)
    {
        return coolNames[index];
    }

    /**
     * Takes the notes of a scale and expands them into a list of the notes in words.
     * @param enharmonicsOn Whether the program should prioritize proper forms of notes so that each letter is used only once.
     */
    protected static String expand(int[] key, boolean enharmonicsOn)
    {
        int[] key2 = clamp(key);
        String[] rawNotes = expandRaw(key2,enharmonicsOn);

        return StringHelper.arrayToString(rawNotes, "and",true);

    }
    
    
    /**
     * Makes sure all the notes are within the octave.
     */
    protected static int[] clamp(int[] key)
    {
        int[] key2 = new int[key.length];
        int c = 0;
        for(int i : key)
        {
            key2[c] = ((i-1)%12)+1 ;
            c++;
        }
        return key2;
    }

    protected static String expandSmart(int[] key, int ind, boolean enh)
    {
        if (SpeedCache.needsCache(ind,enh))
        {
            String str = expand(key, enh);
            SpeedCache.smartCache(str,ind,enh);
            return str;
        }
        else
        {
            return SpeedCache.smartCheck(ind,enh);
        }

    }

    protected static String[] expandRaw(int[] key, boolean enharmonicsOn)
    {
        String[] rawNotes = new String[key.length];
        for(int count = 0; count < key.length; count++)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            //System.out.println(newNote + " do");
            if (newNote == null)
            {
                //System.out.println("tripped");
                String[] temp = rawNotes;
                String[] rawNotes2 = new String[count];
                for(int count2 = 0; count2 < count; count2++)
                {
                    rawNotes2[count2] = temp[count2];
                    //System.out.println(temp[count2]);
                }
                return rawNotes2;
            }
            rawNotes[count] = newNote;
        }

        if (enharmonicsOn)
        {
            return EnharmonicsHelper.doEnharmonics(rawNotes);
        }
        else {
            return rawNotes;
        }
    }

    /**
     * Gets the notes of the triad at the specific index of a scale.
     */
    protected static int[] getRawChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        if (index > LOOP)
        {
            return new int[]{};
        }
        return new int[]{key[index], key[(index + 2) % 7],key[(index + 4) % 7]};

    }
}
