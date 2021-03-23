import java.util.List;
import java.util.Arrays;
/**
 * Filters keys by certain attributes.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Filter
{
    // instance variables - replace the example below with your own
    int[] requiredNotes;
    private String type = "";

    /**
     * Tests for a specific interval.
     */
    public Filter(int noteIndex, int interval) 
    {
        requiredNotes = new int[]{noteIndex,noteIndex+interval};
        type = "Interval";
    }
    
    /**
     * Tests for a specific note.
     */
    public Filter(int noteIndex) 
    {
        requiredNotes = new int[]{noteIndex};
        type = "Note";
    }
    
    public String translateToReadable()
    {
        if (type == "Note")
        {
            return ("Must contain the note " + TheoryHelper.getNoteName(requiredNotes[0],true));
        }
        else if (type == "Interval")
        {
            return ("Must contain a " + TheoryHelper.getIntervalName(requiredNotes[1]-requiredNotes[0]) + " over " + TheoryHelper.getTargetNoteName(requiredNotes[0]));
        }
        else
        {
            return ("UNHANDLED_TYPE: REQUIRED NOTES:" + requiredNotes);
        }
        
    }
    
    public boolean checkKey(int[] key)
    {
        Arrays.sort(requiredNotes);
        
        for (int note : requiredNotes)
        {
            if (Arrays.binarySearch(key,note)<0)
            {
                return false;
            }
        }
        return true;
    }
    
    // /**
     // * Filters for a certain chord.
     // */
    // public Filter(Chord chordType)
    // {
        
        
    // }
    
}
