import javax.swing.JLabel;

/**
 * Displays info about the intervals of a scale.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 */
public class Infobox extends ListeningFrame
{
    // instance variables - replace the example below with your own
    private int[] myScale;
    private String myName;
    private String myType;
    private int[] intervals;

    /**
     * Constructor for objects of class InfoBox
     */
    public Infobox(int[] scale, String name,String type)
    {
        super();
        int ind = StringHelper.getEnclosers(name,"()")[0];
        if (ind > -1)
        {
            myName = name.substring(0, ind);

        }
        else
        {
            myName = name;
        }
        ind = StringHelper.getEnclosers(name,"{}")[0];
        if (ind > -1)
        {
            myName = myName.substring(0, ind);

        }
        else
        {
            myName = myName;
        }
        ind = StringHelper.getEnclosers(name,"[]")[0];
        if (ind > -1)
        {
            myName = myName.substring(0, ind);

        }
        else
        {
            myName = myName;
        }
        ind = StringHelper.getEnclosers(name,"/|")[0];
        if (ind > -1)
        {
            myName = myName.substring(0, ind);

        }
        else
        {
            myName = myName;
        }
        myType = type;
        myScale = scale;
        intervals = getIntervals(myScale);
    }

    private int[] getIntervals(int[] s)
    {
        int[] ints = new int[]{0,0,0,0,0,0,0};
        for(int i = 0; i < 6; i++)
        {
            int gap = s[i + 1] - s[i] - 1;
            ints[gap]++;

        }
        int gap = s[0] - s[6] + 12 - 1;
        ints[gap]++;
        return ints;
    }

    private String[] getText(int[] ints)
    {
        String[] s = new String[]{};
        int counter = 1;
        for (int interval : ints)
        {
            if (interval > 0)
            {
                s = ArrayHelper.addX(s,getIntervalName(counter) + ": " + interval);
            }
            counter++;
        }
        return s;
    }

    private String getIntervalName(int i)
    {
        switch (i)
        {
            case 1:
            {
                return "Semitones";
            }
            case 2:
            {
                return "Whole tones";
            }
            case 3:
            {
                return "Minor thirds";
            }
            case 4:
            {
                return "Major thirds";
            }
            case 5:
            {
                return "Perfect fourths";
            }
            case 6:
            {
                return "Diminished fifths";
            }
            case 7:
            {
                return "Perfect fifths";
            }
            case 8:
            {
                return "Augmented fifths";
            }
            default:
            {
                return "Unhandled intervals";
            }
        }

    }

    public void act(String s)
    {
        clear();
        appear(SMALL);
        addHeader(myName + ": " + myType);
        setGrid(getText(intervals).length + 1,1);
        for (String lab : getText(intervals))
        {
            JLabel l = new JLabel(lab);
            l.setHorizontalAlignment(JLabel.CENTER);
            add(l);
        }

    }
}
