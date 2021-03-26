import java.util.List;
import java.util.Arrays;
/**
 * Filters keys by certain attributes.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Filter extends TheoryObj
{
    // instance variables - replace the example below with your own
    private int[] requiredNotes;
    private int requiredPosition;
    private String type = "";
    private String[] tags;
    
    
    /** 
    * If this is turned on, the filter acts opposite to how it usually would.
    */   
    private boolean inverted = false;
    
    private KeyNamesHelper namer = new KeyNamesHelper();

    /**
     * Tests for a specific note, at a specific point.
     * @param note The note to check for.
     */
    public Filter(int note, int pos) 
    {
        requiredNotes = new int[]{note};
        requiredPosition = pos;
        type = "NotePos";
    }

    /**
     * Tests for one of specific notes, at a specific point.
     */
    public Filter(int[] notes, int pos) 
    {
        requiredNotes = notes;
        requiredPosition = pos;
        type = "NotePos";
        
    }
    
    /**
     * Tests for a specific notes, at a specific point.
     * @param inv If this is turned on, the filter acts opposite to how it usually would. 
     */
    public Filter(int[] notes, int pos,boolean inv) 
    {
        requiredNotes = notes;
        requiredPosition = pos;
        type = "NotePos";
        inverted = inv;
    }

    
    /**
     * Tests for a specific note.
     * @param note The note to check for.
     */
    public Filter(int note) 
    {
        requiredNotes = new int[]{note};
        type = "Note";
    }
    
    /**
     * Tests for a specific note.
     * @param note The note to check for.
     * @param inv If this is turned on, the filter acts opposite to how it usually would. 
     */
    public Filter(int note, boolean inv) 
    {
        requiredNotes = new int[]{note};
        type = "Note";
        inverted = inv;
    }
    
    /**
     * Tests for it being named or other qualities.
     * @param spc The tag to search for.
     */
    public Filter(String tgs) 
    {
        if (tgs.equals("isNamed"))
        {
            type = tgs;
        }
            else{
        type = "Tag";
        tags = new String[]{tgs};
    }
    }
    
    /**
     * Tests for it being named or other qualities.
     * @param spc The tags to search for.
     */
    public Filter(String[] tgs) 
    {
        type = "Tag";
        tags = tgs;
    }
    
    /**
     * Tests for it being named or other qualities.
     * @param spc The quality to filter by.
     * @param inv If this is turned on, the filter acts opposite to how it usually would. 
     */
    public Filter(String tgs, boolean inv) 
    {
        if (tgs.equals("isNamed"))
        {
            type = tgs;
        }
            else{
        type = "Tag";
        tags = new String[]{tgs};
    }
        inverted = inv;
    }

    
    /**
     * Generates a description of the Filter.
     */
    public String translateToReadable()
    {
        String preceder = "";
        if (inverted)
        {
            preceder = "not ";
        }
        
        
        if (type == "Note")
        {
            return ("Must " + preceder + "contain the note " + getNoteName(requiredNotes[0]));
        }
        else if (type == "NotePos" && requiredNotes.length == 1)
        {
            return ("Must " + preceder + "contain the note " + getNoteName(requiredNotes[0]));
        }
        else if (type == "NotePos")
        {
            return ("Must " + preceder + "contain the notes " + getNoteName(requiredNotes[0]) + " or " + getNoteName(requiredNotes[1]));
        }
        else if (type == "isNamed")
        {
            return ("Must " + preceder + "be a named key.");
        }
        else if (type == "Exotic")
        {
            return ("Must " + preceder + "be an exotic key.");
        }
        else if (type == "Tags")
        {
            return ("Only scales tagged with tags " + tags);
            
        }
        else
        {
            return ("UNHANDLED_TYPE: REQUIRED NOTES:" + requiredNotes);
        }

    }
    

    /**
     * Generates a description of the Filter.
     */
    public String toString()
    {

        return translateToReadable();
    }

    
    /**
     * Analyzes a given scale to see if it matches the Filter.
     * @param key The key to analyze.
     * @return A boolean noting if the scale matched the Filter or not.
     */
    public boolean checkKey(int[] key)
    {
        //Arrays.sort(key);
        return !(inverted == checkKeyHelper(key));
    }
    
    private boolean checkKeyHelper(int[] key)
    {
        switch(type)
        {
        case "isNamed":
        {
            return (!namer.get(key).equals(""));
        }
        case "Exotic":
        {
            return (namer.get(key).indexOf("[") > 0);
        }
        case "Tag":
        {
            List<String> list = Arrays.asList(namer.getTags(key));
            boolean isValid = true;
            for (String tag : tags)
            {
                if (!list.contains(tag))
                {
                    return false;
                }
                
            }
            return isValid;
        }
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

