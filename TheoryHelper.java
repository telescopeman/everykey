 
/**
 * Write a description of class TheoryHelper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TheoryHelper
{
    // instance variables - replace the example below with your own
    private int x;

    private static String[] noteNames = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","Null"};
    private static String[] altNames =  new String[]{"",    "", "C♯","", "D♯","", "", "F♯", "","G♯","", "A♯","",""};
    
    
    private static String[] targetNoteNames =  new String[]{"", "the root","D♭","D","E♭","F","G♭","G","A♭","A","B♭","B","Null"};
    
    private static String[] intervalNames =  new String[]{"same note", "minor second","second","minor third","major third","perfect fourth","augmented fourth / diminished fifth","perfect fifth","minor sixth","major sixth","seventh","major seventh","Null"};
    
    //private static KeyNamesHelper namer = new KeyNamesHelper();
    
    
    
    // public static String getKeyName(int[] key) //Assumes root note is C.
    // {
        // //System.out.print(key);
        // return namer.get(key);
    // }
    
    
    public static String getNoteName(int index) //Assumes root note is C.
    {
        if (index > 12 || index < 0)
        {
            return "Null";
        }
        else
        {
            return noteNames[index];
        }
    }
    
    public static String getNoteName(int index, boolean withAlt) //Assumes root note is C.
    {
        if (index > 12 || index < 0)
        {
            return "Null";
        }
        else
        {
            if (withAlt && !altNames[index].equals(""))
            {
                return noteNames[index] + "/" + altNames[index];
            }
            else
            {
                return noteNames[index];
            }
        }
    }
    
    public static String getIntervalName(int index)
    {
        return intervalNames[index];
        
    }
    
    public static String getTargetNoteName(int index)
    {
        return targetNoteNames[index];
        
        
    }
    
    
    public static String getChordAt(int[] key, int index)
    {
        final int LOOP = 6;
        if (index > 7)
        {
            return("Invalid Chord!");
        }
        
        //return "Test Chord";
        
        return parseChord(key[index],key[(index + 2) % LOOP],key[(index + 4) % LOOP]);
        
        
        
    }
    
    public static String parseChord(int note1, int note2, int note3)
    {
        if (!(note1 < note2 && note2 < note3))
        {
            return("Invalid Chord!");
        }
        
        String rootname = getNoteName(note1);
        String thirdname = "";
        String fifthname  = "";
        
        int thirdinterval = note2 - note1;
        if (thirdinterval == 4)
        {
            thirdname = "";
            
        }
        else if (thirdinterval == 3)
        {
            thirdname = " Minor";
            
        }
        else if (thirdinterval == 2)
        {
            thirdname = "sus2";
        }
        else if (thirdinterval == 5)
        {
            thirdname = "sus4";
        }
        else
        {
            thirdname = "?";
        }
        
        int fifthint = note3 - note1;
        if (fifthint == 7)
        {
            fifthname = "";
        }
        else if (fifthint == 8 && thirdinterval == 4)
        {
            fifthname = " Augmented";
        }
        else if (fifthint == 6 && thirdinterval == 3)
        {
            thirdname = "";
            fifthname = " Diminished";
        }
        else if (fifthint == 6 && thirdinterval == 4)
        {
            fifthname = "b5";
        }
        else
        {
            fifthname = "?";
        }
        
        return rootname + thirdname + fifthname;
    }
    
    
    // /**
     // * An example of a method - replace this comment with your own
     // *
     // * @param  y  a sample parameter for a method
     // * @return    the sum of x and y
     // */
    // public Chord getChordAt(int[] key, int index)
    // {
        // i
    // }
}
