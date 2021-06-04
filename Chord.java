import java.awt.Color;
/**
 * Represents a chord.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class Chord extends BasicTheoryObj
{
    private final int root;
    private int third, fifth;

    public static final String
            MAJOR = "Major",
            MINOR = "Minor",
            DIMINISHED = "Diminished",
            AUGMENTED = "Augmented",
            SUSPENDED_2 = "sus2",
            SUSPENDED_4 = "sus4",
            MAJOR_FLAT_5 = "Major ♭5",
            SUSPENDED_2_FLAT_5 = "sus2 ♭5",
            SUSPENDED_4_FLAT_5 = "sus4 ♭5";

    public static final String[] TYPES = {MAJOR,MINOR,DIMINISHED,AUGMENTED,SUSPENDED_2,SUSPENDED_4,MAJOR_FLAT_5,SUSPENDED_2_FLAT_5,SUSPENDED_4_FLAT_5};

    /**
     * Creates a chord with the given notes.
     */
    public Chord(int one, int two, int three)
    {
        // initialise instance variables
        root = one;

        third = two;
        fifth = three;
        sort();
    }

    private void sort()
    {
        while (root >= third)
        {
            third = third + 12;
        }

        while (third >= fifth)
        {
            fifth = fifth + 12;
        }

    }


    /**
     * Returns the name of the chord.
     */
    public String toString()
    {
        String third_name = getThirdType();
        String fifth_name  = getFifthType();

        if (fifth_name.equals("Augmented") || fifth_name.equals("Diminished"))
        {
            third_name = "";

        }
        return getNoteName(root) + " " + third_name + " " + fifth_name;
    }

    private String getFifthType()
    {
        String fifth_name;
        int fifth_interval = fifth - root;
        int third_interval = third - root;
        if (fifth_interval == 7)
        {
            fifth_name = "";
        }
        else if (fifth_interval == 8 && third_interval != 3)
        {
            fifth_name = "Augmented";
        }
        else if (fifth_interval == 6 && third_interval == 3)
        {
            fifth_name = "Diminished";
        }
        else if (fifth_interval == 6)
        {
            fifth_name = "♭5";
        }
        else if (fifth_interval == 5)
        {
            fifth_name = "♭♭5";
        }
        else
        {
            fifth_name = "?";
        }
        return fifth_name;

    }

    private String getThirdType()
    {
        String third_name;
        int third_interval = third - root;
        if (third_interval == 4)
        {
            third_name = "Major";

        }
        else if (third_interval == 3)
        {
            third_name = "Minor";

        }
        else if (third_interval == 2)
        {
            third_name = "sus2";
        }
        else if (third_interval == 5)
        {
            third_name = "sus4";
        }
        else
        {
            third_name = "?";
        }

        return third_name;
    }

    /**
     * Gets a color representing the chord.
     */
    public Color toColor()
    {
        switch (getFifthType())
        {
            case "Augmented":

            return ColorsHelper.soften(Color.yellow);

            case "Diminished":

            case "♭5":

                return ColorsHelper.soften(Color.green);

            case "♭♭5":

            return new Color(144,144,144);

            default:
            switch (getThirdType())
            {
                case "Minor":

                    return new Color(30,100,200);

                case "Major":

                    return ColorsHelper.soften(Color.red);

                case "sus2":

                case "sus4":

                    return new Color(100,200,200);

                default:
                    return Color.gray;
            }
        }
       
    }

    
    

    

    /**
     * Returns the notes of the chord, formatted.
     */
    public String getNotes()
    {
        return getNoteName(root) + " + " + getNoteName(third) + " + " + getNoteName(fifth);

    }
    
    

}
