
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
        if (index > 13 || index < 0)
        {
            return "Null";
        }
        else
        {
            return noteNames[index];
        }
    }
}
