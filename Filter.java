import java.util.Arrays;
/**
 * Filters keys by certain attributes.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public class Filter extends TheoryObj
{
    private int[] requiredNotes;
    private int requiredPosition;
    private final FilterType type;
    private String[] tags;
    private String description;
    private boolean hasDescription = false;

    /** 
     * If this is turned on, the filter acts opposite to how it usually would.
     */   
    private boolean inverted = false;

    /**
     * Tests for a specific note, at a specific point.
     * @param note The note to check for.
     */
    public Filter(int note, int pos) 
    {
        requiredNotes = new int[]{note};
        requiredPosition = pos;
        type = FilterType.HAS_NOTE_AT_POSITION;
    }

    /**
     * Tests for all of specific notes.
     */
    public Filter(int[] notes) 
    {
        requiredNotes = notes;
        type = FilterType.HAS_ALL_NOTES;

    }

    /**
     * Tests for all of specific notes.
     */
    public Filter(int[] notes, boolean inv) 
    {
        requiredNotes = notes;
        inverted = inv;
        type = FilterType.HAS_ALL_NOTES;

    }

    /**
     * Tests for one of specific notes, at a specific point.
     */
    public Filter(int[] notes, int pos) 
    {
        requiredNotes = notes;
        requiredPosition = pos;
        type = FilterType.HAS_NOTE_AT_POSITION;

    }

    /**
     * Tests for specific notes at a specific point.
     * @param inv If this is turned on, the filter acts opposite to how it usually would. 
     */
    public Filter(int[] notes, int pos,boolean inv) 
    {
        requiredNotes = notes;
        requiredPosition = pos;
        type = FilterType.HAS_NOTE_AT_POSITION;
        inverted = inv;
    }

    /**
     * Tests for a specific note.
     * @param note The note to check for.
     */
    public Filter(int note) 
    {
        requiredNotes = new int[]{note};
        type = FilterType.HAS_NOTE;
    }

    /**
     * Tests for a specific note.
     * @param note The note to check for.
     * @param inv If this is turned on, the filter acts opposite to how it usually would. 
     */
    public Filter(int note, boolean inv) 
    {
        requiredNotes = new int[]{note};
        type = FilterType.HAS_NOTE;
        inverted = inv;
    }

    /**
     * Tests for it being named or other qualities.
     * @param tgs The tag to search for.
     */
    public Filter(String tgs) 
    {
        if (tgs.equals("isNamed"))
        {
            type = FilterType.IS_NAMED;
        }
        else{
            type = FilterType.HAS_TAG;
            tags = new String[]{tgs};
        }
    }

    /**
     * Tests for it being named or other qualities.
     * @param tgs The tags to search for.
     */
    public Filter(String[] tgs) 
    {
        type = FilterType.HAS_TAG;
        tags = tgs;
    }

    /**
     * Tests for it being named or other qualities.
     * @param tgs The quality to filter by.
     * @param inv If this is turned on, the filter acts opposite to how it usually would. 
     */
    public Filter(String tgs, boolean inv) 
    {
        type = FilterType.HAS_TAG;
        tags = new String[]{tgs};
        inverted = inv;
    }

    public Filter(FilterType type)
    {
        this.type = type;
    }

    public Filter(FilterType type, boolean inv)
    {
        this.type = type;
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
    @Override
    public String toString()
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

        switch (type)
        {
            case HAS_NOTE:
            {
                return ("Must " + preceder + "contain the note " + getNoteName(requiredNotes[0]));
            }
            case HAS_NOTE_AT_POSITION:
            {
                if (requiredNotes.length == 1)
                {
                    return ("Must " + preceder + "contain the note " +
                            getNoteName(requiredNotes[0]) + " as a " +
                            getIntervalName(requiredPosition) + ".");
                }
                else
                {
                    return ("Must " + preceder + "contain either " +
                            getNoteName(requiredNotes[0]) + " or " +
                            getNoteName(requiredNotes[1]) + " as a " +
                            getIntervalName(requiredPosition) + ".");
                }
            }
            case IS_NAMED:
            {
                return ("Must " + preceder + "be a named key.");
            }
            case HAS_ALL_NOTES:
            {
                return ("Must " + preceder + "contain notes " + expand(requiredNotes,false));
            }
            case HAS_TAG:
            {
                return ("Only scales " + preceder + "tagged with tag: " + tags[0]);

            }
            default:
            {
                return ("UNHANDLED_TYPE: REQUIRED NOTES:" + requiredNotes);
            }

        }

    }

    /**
     * Analyzes a given scale to see if it matches the Filter.
     * @param key The key to analyze.
     * @return A boolean noting if the scale matched the Filter or not.
     */
    public boolean checkKey(int[] key)
    {
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
            case IS_NAMED:
            {
                return (!KeyNamesHelper.smartGet(key,ind).equals(""));
            }
            case IS_EXOTIC:
            {
                return (KeyNamesHelper.smartGet(key,ind).indexOf("[") > 0);
            }
            case HAS_TAG:
            {
                boolean isValid = true;
                for (String tag : tags)
                {

                    boolean going = false;
                    for (String t : KeyNamesHelper.getTags(key,ind))
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
            case HAS_ALL_NOTES:
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
            if (type == FilterType.HAS_NOTE && Arrays.binarySearch(key,note) < 0)
            {
                return false;
            }
            if (type == FilterType.HAS_NOTE_AT_POSITION && key[requiredPosition] == note)
            {
                return true;
            }
        }

        return type != FilterType.HAS_NOTE_AT_POSITION;

    }
}
