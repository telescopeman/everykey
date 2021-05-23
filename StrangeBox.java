import java.util.Hashtable;
import javax.swing.JLabel;

/**
 * Allows for changing the neutral point for strangeness measurements.
 *
 * @author Caleb Copeland
 * @version 5/22/21
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
        addHeader("Change Neutral Point for Strangeness Sorting:");
        setUpSlider(UIStuff.getNeutral(),MIN,MAX);
            
        addLabels();
        
        add(getSlider());

        addButton("Apply",this);
    }
    
    private void addLabels()
    {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(041, new JLabel("Aerygian") );
        labelTable.put(111, new JLabel("Ultra Phrygian ♭♭3") );
        labelTable.put(166, new JLabel("Locrian") );
        labelTable.put(224, new JLabel("Enigmatic Scale") );
        labelTable.put(300, new JLabel("Dorian") );
        labelTable.put(336, new JLabel("Major") );
        labelTable.put(364, new JLabel("Nohkan") );
        labelTable.put(420, new JLabel("Super Lydian") );
        getSlider().setLabelTable( labelTable );
    }

    protected void apply()
    {
        UIStuff.setNeutral((int)getPos());
    }
    
    public void act(String id)
    {
        if (id.equals("Change Neutral Point"))
        {
            appear(LONG);
        }
        else
        {
            apply();
            UIStuff.refresh();
            setVisible(false);

        }
    }
}
