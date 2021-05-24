import javax.swing.JLabel;

/**
 * Displays info about the intervals of a scale.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 */
public class Infobox extends ListeningFrame
{
    private String myName;
    private final String myType;
    private final int[] intervals;

    /**
     * Constructor for objects of class InfoBox
     */
    public Infobox(int[] scale, String name,String type)
    {
        super(SMALL);
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
        ind = StringHelper.getEnclosers(name,"[]")[0];
        if (ind > -1)
        {
            myName = myName.substring(0, ind);
        }
        ind = StringHelper.getEnclosers(name,"/|")[0];
        if (ind > -1)
        {
            myName = myName.substring(0, ind);

        }
        myType = type;
        // instance variables - replace the example below with your own
        intervals = getIntervals(scale);
    }

    private int[] getIntervals(int[] s)
    {
        int[] intervals = new int[]{0,0,0,0,0,0,0};
        for(int i = 0; i < 6; i++)
        {
            int gap = s[i + 1] - s[i] - 1;
            intervals[gap]++;

        }
        int gap = s[0] - s[6] + 12 - 1;
        intervals[gap]++;
        return intervals;
    }

    private String[] getText(int[] intervals)
    {
        String[] s = new String[]{};
        int counter = 1;
        for (int interval : intervals)
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

    protected void act(String s)
    {
        clear();
        appear();
        addHeader(myName + ": " + myType);
        setGrid(getText(intervals).length + 1,1);
        for (String lab : getText(intervals))
        {
            JLabel l = new JLabel(lab);
            l.setHorizontalAlignment(JLabel.CENTER);
            add(l);
        }
    }

    @Override
    protected void onClosed() {
        // do literally nothing
    }
}
