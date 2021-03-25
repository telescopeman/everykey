
/**
 * Represents a chord.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chord
{
    // instance variables - replace the example below with your own
    private int root;
    private int third;
    private int fifth;
    private int[] extensions;
    
    private int keyOffset;
    /**
     * Constructor for objects of class Chord
     */
    public Chord(int one, int two, int three)
    {
        // initialise instance variables
        root = one;
        third = two;
        fifth = three;
        extensions = new int[]{};
        keyOffset = 0;
    }
    
    /**
     * Constructor for objects of class Chord
     */
    public Chord(int one, int two, int three,int[] others)
    {
        // initialise instance variables
        root = one;
        third = two;
        fifth = three;
        extensions = others;
        keyOffset = 0;
    }

    public String toString()
    {
        
        if (root >= third || third <= fifth)
        {
            return("Invalid Chord!");
        }
        
        String rootname = RomanNumeralsHelper.convert(root);
        String thirdname = "";
        String fifthname  = "";
        
        int thirdinterval = third - root;
        if (thirdinterval == 4)
        {
            thirdname = " Major";
            
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
        
        int fifthint = fifth - root;
        
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
    
        //return "Test";
    }
    
}
