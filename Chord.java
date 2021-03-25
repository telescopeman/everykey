import java.awt.Color;
/**
 * Represents a chord.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chord extends TheoryObj
{
    // instance variables - replace the example below with your own
    private int root;
    private int third;
    private int fifth;
    private int[] extensions;

    private final int LOOP = 7;
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
        sort();
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

    private void invert()
    {
        int temp = root;
        root = third - 12;
        third = fifth - 12;
        fifth = temp;
    }

    public String toString()
    {

        System.out.println(root + ",");
        String rootname = getNoteName(root);
        String thirdname = getThirdType();
        String fifthname  = getFifthType();
        if (fifthname.equals("?")) //invert twice if getting null values
        {
            invert();
            rootname = getNoteName(root);
            thirdname = getThirdType();
            fifthname  = getFifthType();
            if (fifthname.equals("?"))
            {
                invert();
                rootname = getNoteName(root);
                thirdname = getThirdType();
                fifthname  = getFifthType();
            }
        }

        if (fifthname.equals("Augmented") || fifthname.equals("Diminished"))
        {
            thirdname = "";

        }
        return rootname + " " + thirdname + " " + fifthname;

        //return "Test";
    }
    
    

    public String getFifthType()
    {
        String fifthname = "";
        int fifthint = fifth - root;
        int thirdinterval = third - root;
        if (fifthint == 7)
        {
            fifthname = "";
        }
        else if (fifthint == 8 && thirdinterval == 4)
        {
            fifthname = "Augmented";
        }
        else if (fifthint == 6 && thirdinterval == 3)
        {
            fifthname = "Diminished";
        }
        else if (fifthint == 6 && thirdinterval == 4)
        {
            fifthname = "b5";
        }
        else
        {
            fifthname = "?";
        }
        return fifthname;

    }

    public String getThirdType()
    {
        String thirdname = "";
        int thirdinterval = third - root;
        if (thirdinterval == 4)
        {
            thirdname = "Major";

        }
        else if (thirdinterval == 3)
        {
            thirdname = "Minor";

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

        return thirdname;
    }

    public Color toColor()
    {
        if (getFifthType().equals("Augmented"))
        {
            return soften(Color.yellow);
        }
        else if (getFifthType().equals("Diminished"))
        {
            return soften(Color.green);
        }
        else if (getThirdType().equals("Minor"))
        {
            return soften(Color.cyan);
        }
        else if (getThirdType().equals("Major"))
        {
            return soften(Color.orange);
        }
        return Color.gray;
    }

    public Color soften(Color c)
    {
        float r = c.getRed()/255;
        float g = c.getGreen()/255;
        float b = c.getBlue()/255;
        float m = new Double(0.9).floatValue();
        System.out.println(m);
        return new Color(m*r,m*g,m*b);
    }

    public String getNotes()
    {
        return getNoteName(root) + " + " + getNoteName(third) + " + " + getNoteName(fifth);

    }
    
    
}
