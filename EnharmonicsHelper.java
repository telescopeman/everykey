import java.util.HashMap;

/**
 * Handles enharmonics.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public abstract class EnharmonicsHelper extends BasicTheoryObj
{
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
            
            
    public static String[] doEnharmonics(String[] notes)
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
     * Gets the enharmonic equivalent of a given note.
     */
    private static String getEnharmonicBelow(String note)
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
    private static String getEnharmonicAbove(String note)
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
