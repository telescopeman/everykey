import java.util.HashMap;

/**
 * An object capable of using music theory.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public abstract class TheoryObj
{

    private static String[] noteNames = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","C"},
            coolNames = new String[]{"root","second","third","fourth","fifth","sixth","seventh"};

    public static final String[] CHROMATICSCALE = new String[]{"C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B"};

    private static final HashMap<String,String> ENHARMONICSBELOW = new HashMap<String,String>()
        {{
                put("C♯", "B♯♯");
                put("D♭", "C♯");
                put("D", "C♯♯");
                put("E♭♭", "D");
                put("E♭", "D♯");
                put("E", "D♯♯");
                put("F♭♭", "E♭");
                put("F♭", "E");
                put("F", "E♯");
                put("F♯", "F♯");
                put("G♭♭", "F");
                put("G♭", "F♯");
                put("G", "F♯♯");
                put("A♭♭", "G");
                put("A♭", "G♯");
                put("A", "G♯♯");
                put("B♭", "A♯");
                put("B", "A♯♯");
            }};

    private static final HashMap<String,String> ENHARMONICSABOVE = new HashMap<String,String>()
        {{
                put("C", "D♭♭");
                put("C♯", "D♭");
                put("C♯♯", "D");
                put("C♯♯", "D");
                put("D♭", "E♭♭♭");
                put("D", "E♭♭");
                put("D♯", "E♭");
                put("D♯♯", "E");
                put("E♭", "F♭♭");
                put("E", "F♭");
                put("E♯", "F");
                put("E♯♯", "F♯");
                put("F", "G♭♭");
                put("F♯", "G♭");
                put("F♯♯", "G");
                put("G♭", "A♭♭♭");
                put("G", "A♭♭");
                put("G♯", "A♭");
                put("G♯♯", "A");
                put("A♭", "B♭♭♭");
                put("A", "B♭♭");
                put("A♯", "B♭");
                put("A♯♯", "B");
                put("B♭", "C♭♭");
                put("B", "C♭");
                put("B♯♯", "C♯");
                put("C♭", "D♭♭♭");
            }};

    public static String getNoteName(int ind) //Assumes root note is C.
    {
        int index = ind + StateWatcher.getOffset();
        if (index > 13)
        {
            return getNoteNameHelper(index-12);

        }
        else if (index <= 0)
        {
            return getNoteNameHelper(index+12);
        }
        else
        {
            return noteNames[index];
        }
    }

    private static String getNoteNameHelper(int ind) //Assumes root note is C.
    {

        if (ind > 13)
        {
            return getNoteNameHelper(ind-12);

        }
        else if (ind <= 0)
        {
            return getNoteNameHelper(ind+12);
        }
        else
        {
            return noteNames[ind];
        }
    }

    public static String getIntervalName(int index)
    {
        return coolNames[index];

    }

    /**
     * Takes the notes of a scale and expands them into a list of the notes in words.
     * @param enharmonicsOn Whether the program should prioritize proper forms of notes so that each letter is used only once.
     */
    public static String expand(int[] key, boolean enharmonicsOn)
    {
        //System.out.println("Exp");
        int[] key2 = new int[key.length];
        int c = 0;
        for(int i : key)
        {
            key2[c] = ((i-1)%12)+1 ;
            c++;
        }
        String name = "";
        String[] rawNotes = expandRaw(key2,enharmonicsOn);

        int count3 = 0;
        for (String newNote : rawNotes)
        {
            name = name + newNote;
            if (count3 < key.length - 2) // most
            {
                name = name + ", ";
            }
            else if (count3 == key.length - 2) //second to last
            {
                name = name + ", and ";
            }
            else // last
            {
                name = name + ".";
            }

            count3++;
        }
        return name;
    }

    public static String expandSmart(int[] key, int ind, boolean enh)
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

    public static String expandSmart(int[] key, boolean enh, int ind)
    {
        return expandSmart(key,ind,enh);
    }

    private static String[] expandRaw(int[] key, boolean enharmonicsOn)
    {
        String[] rawNotes = new String[7];
        for(int count = 0; count < key.length; count++)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            rawNotes[count] = newNote;
        }

        if (enharmonicsOn)
        {
            return doEnharmonics(rawNotes);
        }
        else {
            return rawNotes;
        }
    }

    private static String[] doEnharmonics(String[] notes)
    {
        /*
          if this method is called,
          there should now be an array of all the note names.
          next comes the formatting into an actual string.
         */
        String[] rawNotes = notes;
        final int maxAttempts = 20;
        int attempts = 0;
        int dupes = 1;
        String[] cur = rawNotes;
        cur[0] = getNoteName(1);
        // this is akin to a sorting algorithm. it will iterate thru until no more dupes, or gives up.
        while (dupes > 0 && attempts < maxAttempts)
        {
            /*
              the first note is always gonna be C.
              unless i implement a way to change root note, we can just skip it.
              also gonna skip the last note for now. that's a special case.
             */
            //System.out.println(attempts);
            dupes = 0;
            for(int count2 = 1; count2 < rawNotes.length; count2++)
            {
                String curNote = cur[count2];
                String prevNote = cur[count2-1];
                String nextNote;
                if (count2 < rawNotes.length -1 )
                {
                    nextNote = cur[count2+1];
                }
                else
                {
                    nextNote = "dummy";
                }
                cur[count2] = curNote;

                if (testIfDupe(curNote,prevNote))
                {

                    cur[count2] = getEnharmonicAbove(curNote);
                    dupes++;
                    continue;
                }
                if (count2 < rawNotes.length -1  && testIfDupe(cur[count2],nextNote))
                {
                    String rpl = getEnharmonicBelow(cur[count2]);

                    cur[count2] = rpl;

                    dupes++;
                }
                // cursory check to make sure obvious style errors are removed.
            }
            attempts++;
        }
        if (attempts < maxAttempts)
        {
            return cur;
        }
        else // filtering has (somehow) failed
        {
            throw new IllegalStateException("enharmonics failure!");
        }
    }


    private static boolean testIfDupe(String str1, String str2)
    {
        if (str2.equals(""))
        {
            return false;
        }
        return getLetter(str1).equals(getLetter(str2));

    }

    private static String getLetter(String str)
    {
        if (str.equals(""))
        {
            return "";
        }
        return str.substring(0,1);
    }

    /**
     * Gets the notes of the triad at the specific index of a scale.
     */
    public static int[] getRawChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        if (index > LOOP)
        {
            return new int[]{};
        }
        return new int[]{key[index], key[(index + 2) % 7],key[(index + 4) % 7]};

    }

    /**
     * Gets the enharmonic equivalent of a given note.
     */
    public static String getEnharmonicBelow(String note)
    {
        String name = ENHARMONICSBELOW.get(note);

        if (name == null)
        {
            return note;
        }
        else
        {
            return name;
        }
    }

    /**
     * Gets the enharmonic equivalent of a given note.
     */
    public static String getEnharmonicAbove(String note)
    {
        String name = ENHARMONICSABOVE.get(note);
        if (name == null)
        {
            System.out.println("Unhandled above note: " + note);
            throw new IllegalArgumentException("Unhandled above note: " + note);
        }
        else
        {
            return name;
        }
    }
}
