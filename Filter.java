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
    int requiredPosition;
    private String type = "";
    
    KeyNamesHelper namer = new KeyNamesHelper();

    /**
     * Tests for a specific note, at a specific point.
     */
    public Filter(int note, int pos) 
    {
        requiredNotes = new int[]{note};
        requiredPosition = pos;
        type = "NotePos";
    }

    /**
     * Tests for a specific notes, at a specific point.
     */
    public Filter(int[] notes, int pos) 
    {
        requiredNotes = notes;
        requiredPosition = pos;
        type = "NotePos";
    }

    /**
     * Tests for a specific note.
     */
    public Filter(int note) 
    {
        requiredNotes = new int[]{note};
        type = "Note";
    }
    
    /**
     * Tests for it being named.
     */
    public Filter(String spc) 
    {
        type = spc;
    }

    public String translateToReadable()
    {
        if (type == "Note")
        {
            return ("Must contain the note " + TheoryHelper.getNoteName(requiredNotes[0],true));
        }
        else if (type == "NotePos" && requiredNotes.length == 1)
        {
            return ("Must contain the note " + TheoryHelper.getNoteName(requiredNotes[0],true));
        }
        else if (type == "NotePos")
        {
            return ("Must contain the notes " + TheoryHelper.getNoteName(requiredNotes[0],true) + " or " + TheoryHelper.getNoteName(requiredNotes[1],true));
        }
        else if (type == "isNamed")
        {
            return ("Must be a named key.");
        }
        else
        {
            return ("UNHANDLED_TYPE: REQUIRED NOTES:" + requiredNotes);
        }

    }

    public String toString()
    {

        return translateToReadable();
    }

    public boolean checkKey(int[] key)
    {
        //Arrays.sort(key);
        if (type=="isNamed")
        {
            return (!namer.get(key).equals(""));
            
        }

        for (int note : requiredNotes)
        {
            if (type.equals("Note") && Arrays.binarySearch(key,note) < 0)
            {
                return false;
            }
            if (type.equals("NotePos") && key[requiredPosition] == note)
            {
                return true;
            }

        }

        if (type.equals("NotePos"))
        {
            return false;
        }
        return true;
    }

}

