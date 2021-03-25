 
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
