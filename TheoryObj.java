
/**
 * Write a description of class TheoryObj here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class TheoryObj
{
    // instance variables - replace the example below with your own
    private int x;

    private static EnharmonicsHelper enh = new EnharmonicsHelper();
    
    public static String[] noteNames = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","C"};
    
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
    
    public static String expand(int[] key, boolean enharmonicsOn)
    {
        String name = "";
        int count = 0;
        String lastNote = "I";
        while (count < key.length)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            if (enharmonicsOn && count < key.length -1 && newNote.substring(0,1).equals(getNoteName(key[count+1]).substring(0,1)))
            {
                String sameNote = enh.getEnharmonic(newNote);
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
}
