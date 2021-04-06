import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import java.awt.Font;

/**
 * Box that allows for change of the tempo.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class SliderBox extends ModBox
{
    // instance variables - replace the example below with your own
    public float sliderpos;

    
    //static float tempo_init;

    public JSlider slider;

    /**
     * Listens for changes in a JSlider, and updates variables accordingly, in addition to calling a method for passive updates.
     */
    class SliderListener implements ChangeListener {
        private JSlider sl;
        /**
         * Creates a new SliderListener assigned to a JSlider.
         * @param s The slider that will be watched for change.
         */
        public SliderListener(JSlider s)
        {
            sl = s;
        }

        /**
         * Updates a variable and calls a method indicating a change has taken place.
         */
        public void stateChanged(ChangeEvent e) {
            sliderpos = (int)sl.getValue();
            softUpdate(sl.getValue());

        }
    }

    /**
     * Constructor for objects of class SettingsBox
     */
    public SliderBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        
    }

    public void setUpSlider(float init, float mn, float mx)
    {
        sliderpos = init;
        slider = new JSlider(JSlider.HORIZONTAL,
            (int) mn, (int) mx, (int) sliderpos);
        slider.addChangeListener(new SliderListener(slider));
        //Turn on labels at major tick marks.
        
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(
            BorderFactory.createEmptyBorder(0,0,10,0));
        Font font = new Font("Serif", Font.ITALIC, 15);
        slider.setFont(font);
        
    }
    
    public void update()
    {
        //PlayerWatcher.setTempo((int)sliderpos);
    }

    public void update(float d)
    {
        //PlayerWatcher.setTempo(d);
    }
    
    public void softUpdate(float d)
    {
        //PlayerWatcher.setTempo(d);
    }
    
    public void act(String id)
    {
        
    }
}
