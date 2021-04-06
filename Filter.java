import java.util.Arrays;
/**
 * Filters keys by certain attributes.
 *
 * @author Caleb Copeland
 * @version 4/5/21
 */
public class Filter extends TheoryObj
{
    private int[] requiredNotes;
    private int requiredPosition;
    private String type = "";
    private String[] tags;
    private String description;
    private boolean hasDescription = false;

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
     * Tests for all of specific notes.
     */
    public Filter(int[] notes) 
    {
        requiredNotes = notes;
        type = "AllNotes";

    }

    /**
     * Tests for all of specific notes.
     */
    public Filter(int[] notes, boolean inv) 
    {
        requiredNotes = notes;
        inverted = inv;
        type = "AllNotes";

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
     * Tests for specific notes at a specific point.
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

    public void setDescription(String desc)
    {
        description = desc;
        hasDescription = true;

    }

    /**
     * Generates a description of the Filter.
     */
    public String translateToReadable()
    {
        if (hasDescription)
        {
            return description;
        }

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
            return ("Must " + preceder + "contain the note " + 
                getNoteName(requiredNotes[0]) + " as a " + 
                getIntervalName(requiredPosition) + ".");
        }
        else if (type == "NotePos")
        {
            return ("Must " + preceder + "contain either " + 
                getNoteName(requiredNotes[0]) + " or " + 
                getNoteName(requiredNotes[1]) + " as a " + 
                getIntervalName(requiredPosition) + ".");
        }
        else if (type == "isNamed")
        {
            return ("Must " + preceder + "be a named key.");
        }
        else if (type == "AllNotes")
        {
            return ("Must " + preceder + "contain notes " + expand(requiredNotes,false));
        }
        else if (type == "Tag")
        {
            return ("Only scales " + preceder + "tagged with tag: " + tags[0]);

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
        return !(inverted == checkKeyHelper(key,-1));
    }
    
    /**
     * Analyzes a given scale to see if it matches the Filter.
     * @param key The key to analyze.
     * @return A boolean noting if the scale matched the Filter or not.
     */
    public boolean checkKey(int[] key, int ind)
    {
        return !(inverted == checkKeyHelper(key,ind));
    }

    private boolean checkKeyHelper(int[] key,int ind)
    {
        switch(type)
        {
            case "isNamed":
            {
                return (!namer.smartGet(key,ind).equals(""));
            }
            case "Exotic":
            {
                return (namer.smartGet(key,ind).indexOf("[") > 0);
            }
            case "Tag":
            {
                boolean isValid = true;
                for (String tag : tags)
                {

                    boolean going = false;
                    for (String t : namer.getTags(key,ind))
                    {
                        
                        if (t.equals(tag))
                        {
                            going = true;
                        }

                    }
                    return going;

                }
                return true;
            }
            //everything below this is the same for indexed and nonindexed check
            case "AllNotes":
            {
                return massCheck(key);
            }
            default: //note or notepos
            {
                return defCheck(key);
            }
        }

    }
    
    private boolean massCheck(int[] key)
    {
        for (int note : requiredNotes)
                {
                    if (Arrays.binarySearch(key,note) < 0)
                    {
                        return false;
                    }
                }
                return true;
    }

    private boolean defCheck(int[] key)
    {
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
