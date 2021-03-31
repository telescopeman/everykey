import java.util.Hashtable;
import javax.swing.JLabel;

/**
 * Allows for changing the neutral point for strangeness measurements.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class StrangeBox extends SliderBox
{


    static final int MIN = 20;
    static final int MAX = 440;

    /**
     * Constructor for objects of class StrangeBox
     */
    public StrangeBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        
        addHeader("Change Neutral Point for Strangeness Sorting:");
        setUpSlider(ui.getNeutral(),MIN,MAX);
            
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer(041), new JLabel("Aerygian") );
        labelTable.put( new Integer(111), new JLabel("Ultra Phrygian ♭♭3") );
        labelTable.put( new Integer(166), new JLabel("Locrian") );
        labelTable.put( new Integer(224), new JLabel("Enigmatic Scale") );
        labelTable.put( new Integer(300), new JLabel("Dorian") );
        labelTable.put( new Integer(336), new JLabel("Major") );

        labelTable.put( new Integer(364), new JLabel("Nohkan") );
        labelTable.put( new Integer(420), new JLabel("Super Lydian") );
        //labelTable.put( new Integer( FPS_MAX ), new JLabel("Fast") );
        slider.setLabelTable( labelTable );

    
        

        add(slider);

        addButton("Apply",this);
    }

    public void update(float d)
    {
        ui.setNeutral((int)d);
        //System.out.println("GO" + d);
    }

    
    
    public void act(String id)
    {
        if (id.equals("Change Neutral Point"))
        {

            
            appear(getDim(900,250));

        }
        else
        {
            update(sliderpos);
            ui.refresh();
            hide();

        }
    }
}
