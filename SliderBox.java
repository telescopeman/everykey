import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*; 

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

    JSlider slider;

    class SliderListener implements ChangeListener {
        private JSlider sl;
        public SliderListener(JSlider s)
        {
            sl = s;

        }

        public void stateChanged(ChangeEvent e) {
            sliderpos = (int)sl.getValue();
            //update(sl.getValue());

            //System.out.println("upd" + slider.getValue());
        }
    }

    /**
     * Constructor for objects of class SettingsBox
     */
    public SliderBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //x = 0;
        
        //addHeader("Change Note Speed:");
        //System.out.println("cns");
        
        //setUpSlider(PlayerWatcher.getTempo(),50f,500f);
        //add(slider);

        //addButton("Test",playr);
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

    /**
     * Appear.
     */
    public void actionPerformed(ActionEvent e)
    {
        String id = e.getActionCommand();
        if (id.equals("Change Note Speed"))
        {
            appear(new Dimension(350,250));
        }

    }
}