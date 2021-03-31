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
public class SettingsBox extends ModBox
{
    // instance variables - replace the example below with your own
    private float sliderpos;

    static final int TEMPO_MIN = 50;
    static final int TEMPO_MAX = 500;
    static float tempo_init;
    private MusicHelper playr;

    /**
     * Constructor for objects of class SettingsBox
     */
    public SettingsBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tempo_init = PlayerWatcher.getTempo();
        //x = 0;
        sliderpos = tempo_init;
        addHeader("Change Note Speed:");
            //System.out.println("cns");
            try{
                playr = new MusicHelper(new int[]{1,3,5,6,8,10,12});
                playr.seqSetup();
            }
            catch(Exception ed)
            {
                System.out.println(ed + "thiserro");
            }
            JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                    TEMPO_MIN, TEMPO_MAX, (int) tempo_init);

            class SliderListener implements ChangeListener {
                private JSlider slider;
                public SliderListener(JSlider s)
                {
                    slider = s;

                }

                public void stateChanged(ChangeEvent e) {
                    sliderpos = (int)slider.getValue();
                    update(slider.getValue());

                    //System.out.println("upd" + slider.getValue());
                }
            }

            framesPerSecond.addChangeListener(new SliderListener(framesPerSecond));
            //Turn on labels at major tick marks.
            framesPerSecond.setMajorTickSpacing(50);
            framesPerSecond.setMinorTickSpacing(1);
            framesPerSecond.setPaintTicks(true);
            framesPerSecond.setPaintLabels(true);
            framesPerSecond.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));
            Font font = new Font("Serif", Font.ITALIC, 15);
            framesPerSecond.setFont(font);

            add(framesPerSecond);

            addButton("Test",playr);
    }

    private void update()
    {
        //System.out.println(sliderpos);
        PlayerWatcher.setTempo((int)sliderpos);
        //System.out.print(sliderpos);
        //playr.stop();
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
        if (id.equals("Change Note Speed"))
        {

            
            
            appear(new Dimension(350,250));
            
        }
        else
        {
            //System.out.println(id);
            //ui.setTempo(sliderpos);

        }
        //System.out.println(id);

    }
}
