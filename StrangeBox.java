import java.util.Hashtable;
import javax.swing.JLabel;

/**
 * Allows for changing the neutral point for strangeness measurements.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public class StrangeBox extends SliderBox
{
    static final int MIN = 20;
    static final int MAX = 440;

    /**
     * Constructor for objects of class StrangeBox
     */
    public StrangeBox()
    {
        super();// initialise instance variables
        
        addHeader("Change Neutral Point for Strangeness Sorting:");
        setUpSlider(UIStuff.getNeutral(),MIN,MAX);
            
        addLabels();
        
        add(getSlider());

        addButton("Apply",this);
    }
    
    private void addLabels()
    {
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer(041), new JLabel("Aerygian") );
        labelTable.put( new Integer(111), new JLabel("Ultra Phrygian ♭♭3") );
        labelTable.put( new Integer(166), new JLabel("Locrian") );
        labelTable.put( new Integer(224), new JLabel("Enigmatic Scale") );
        labelTable.put( new Integer(300), new JLabel("Dorian") );
        labelTable.put( new Integer(336), new JLabel("Major") );
        labelTable.put( new Integer(364), new JLabel("Nohkan") );
        labelTable.put( new Integer(420), new JLabel("Super Lydian") );
        getSlider().setLabelTable( labelTable );
    }

    public void update(float d)
    {
        UIStuff.setNeutral( (int) d );
    }
    
    public void softUpdate(float d)
    {
        UIStuff.setNeutral( (int) d );
    }
    
    public void act(String id)
    {
        if (id.equals("Change Neutral Point"))
        {
            appear(LONG);
        }
        else
        {
            update(getPos());
            UIStuff.refresh();
            hide();

        }
    }
}
