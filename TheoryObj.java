
/**
 * Write a description of class TheoryObj here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TheoryObj
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
        while (count < 7)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            if (enharmonicsOn && count < 6 && newNote.substring(0,1).equals(getNoteName(key[count+1]).substring(0,1)))
            {
                String sameNote = enh.getEnharmonic(newNote);
                if (!newNote.substring(0,1).equals(lastNote.substring(0,1)))
                {
                    newNote = sameNote;
                    
                }
                
            }
            name = name + newNote;
            if (count < 5)
            {
                name = name + ", ";
            }
            else if (count == 5)
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
}
