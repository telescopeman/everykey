import java.util.HashMap;

/**
 * An object capable of using music theory.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public abstract class TheoryObj
{
    // instance variables - replace the example below with your own
    private int x;

    //private static EnharmonicsHelper enh = new EnharmonicsHelper();

    public static String[] noteNames = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","C"};

    public static String[] coolNames = new String[]{"root","second","third","fourth","fifth","sixth","seventh"};

    private static final HashMap<String,String> ENHARMONICS = new HashMap<String,String>()
        {{
                put("D♭", "C♯");
                
                put("E♭", "D♯");
                put("E", "D♯♯");
                put("F", "E♯");
                put("G♭", "F♯");
                put("G", "F♯♯");
                put("A♭", "G♯");
                put("B♭", "A♯");
            }};

    /**
     * Constructor for objects of class TheoryObj
     */
    public TheoryObj()
    {
        // initialise instance variables
        x = 0;
    }

    public static String getNoteName(int index) //Assumes root note is C.
    {
        if (index > 13)
        {
            return getNoteName(index-12);

        }
        else if (index <= 0)
        {
            return getNoteName(index+12);
        }
        else
        {
            return noteNames[index];
        }
    }

    /**
     * Takes the notes of a scale and expands them into a list of the notes in words.
     */
    public static String expand(int[] key, boolean enharmonicsOn)
    {
        String name = "";
        int count = 0;
        String lastNote = "III";
        while (count < key.length)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            if (enharmonicsOn && count < key.length -1 && newNote.substring(0,1).equals(getNoteName(key[count+1]).substring(0,1)))
            {
                String sameNote = getEnharmonic(newNote);
                if (!newNote.substring(0,1).equals(lastNote.substring(0,1)))
                {
                    newNote = sameNote;

                }

            }
            name = name + newNote;
            if (count < key.length - 2)
            {
                name = name + ", ";
            }
            else if (count == key.length - 2)
            {
                name = name + ", and ";
            }
            else
            {
                name = name + ".";
            }
            lastNote = newNote;
            count++;

        }
        return name;
    }

    /**
     * Gets the notes of the triad at the specific index of a scale.
     */
    public static int[] getRawChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        System.out.print(ind + "-->");
        if (index > LOOP)
        {
            return new int[]{};
        }

        //return "Test Chord";
        //System.out.println(String.valueOf(index) + String.valueOf((index + 2) % LOOP) + String.valueOf((index + 4) % LOOP) + "-->" + key[index] + key[(index + 2) % LOOP] + key[(index + 4) % LOOP]);
        return new int[]{key[index], key[(index + 2) % 7],key[(index + 4) % 7]};

    }

    /**
     * Gets the enharmonic equivalent of a given note.
     */
    public static String getEnharmonic(String note)
    {
        String name = ENHARMONICS.get(note);

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
