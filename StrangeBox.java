import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Hashtable;

/**
 * Allows for changing the neutral point for strangeness measurements.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class StrangeBox extends ModBox
{
    // instance variables - replace the example below with your own
    private int sliderpos;

    static final int MIN = 20;
    static final int MAX = 440;
    static int strange_init;

    /**
     * Constructor for objects of class StrangeBox
     */
    public StrangeBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        strange_init = ui.getNeutral();
        //x = 0;
        sliderpos = strange_init;
        addHeader("Change Neutral Point for Strangeness Sorting:");
            //System.out.println("cns");
            
            JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                    MIN, MAX, strange_init);

            class SliderListener implements ChangeListener {
                private JSlider slider;
                public SliderListener(JSlider s)
                {
                    slider = s;

                }

                public void stateChanged(ChangeEvent e) {
                    sliderpos = (int)slider.getValue();
                    //update(slider.getValue());

                    //System.out.println("upd" + slider.getValue());
                }
            }

            framesPerSecond.addChangeListener(new SliderListener(framesPerSecond));
            //Turn on labels at major tick marks.
            framesPerSecond.setMajorTickSpacing(50);
            framesPerSecond.setMinorTickSpacing(1);
            
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
            framesPerSecond.setLabelTable( labelTable );
            
            framesPerSecond.setPaintTicks(true);
            framesPerSecond.setPaintLabels(true);
            framesPerSecond.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));
            Font font = new Font("Serif", Font.ITALIC, 15);
            framesPerSecond.setFont(font);

            add(framesPerSecond);

            addButton("Apply",this);
    }

    private void update(float d)
    {
        //System.out.println(d);
        PlayerWatcher.setTempo(d);
        //System.out.print(sliderpos);
        //playr.stop();
    }

    /**
     * Various things.
     *
     * @param e The button pressed to activate this method.
     */
    public void actionPerformed(ActionEvent e)
    {
        String id = e.getActionCommand();
        if (id.equals("Change Neutral Point"))
        {

            
            
            appear(new Dimension(900,250));
            
        }
        else
        {
            //System.out.println(id);
            ui.setNeutral(sliderpos);
            ui.refresh();
            hide();

        }
        //System.out.println(id);

    }
}
