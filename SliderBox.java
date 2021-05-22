import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import java.awt.Font;

/**
 * Box that allows for change of the tempo.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public abstract class SliderBox extends ModBox
{
    private float slider_position;

    private JSlider slider;
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
            slider_position = (int)sl.getValue();
            softUpdate(sl.getValue());

        }
    }

    /**
     * Constructor for objects of class SettingsBox
     */
    public SliderBox()
    {
        super();// initialise instance variables
    }

    public void setUpSlider(float init, float mn, float mx)
    {
        slider_position = init;
        slider = new JSlider(JSlider.HORIZONTAL,
            (int) mn, (int) mx, (int) slider_position);
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
    
    
    
    public float getPos()
    {
        return slider_position;
    }
    
    public void setPos(float d)
    {
        slider_position = d;
    }
    
    public JSlider getSlider()
    {
        return slider;
    }

    public void update()
    {
        //PlayerWatcher.setTempo((int)slider_position);
    }

    public abstract void update(float d);

    public abstract void softUpdate(float d);

}
