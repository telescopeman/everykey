
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

    private static String[] noteNames = new String[]{"Null","C","Db","D","Eb","F","Gb","G","Ab","A","Bb","B","Null"};
    private static String[] altNames =  new String[]{"",    "", "C#","", "D#","", "F#", "","G#","", "A#","",""};
    

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
