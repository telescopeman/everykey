import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.*; 

/**
 * Write a description of class SettingsBox here.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class SettingsBox extends ModBox
{
    // instance variables - replace the example below with your own
    private int sliderpos;

    static final int TEMPO_MIN = 50;
    static final int TEMPO_MAX = 500;
    static int tempo_init;
    private MusicHelper playr;

    /**
     * Constructor for objects of class SettingsBox
     */
    public SettingsBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        tempo_init = ui.getTempo();
        //x = 0;
        sliderpos= 180;
    }

    private void update()
    {
        ui.setTempo(sliderpos);
        System.out.print(sliderpos);
        try
        {
            playr.setTempo(sliderpos);
        }
        catch (javax.sound.midi.MidiUnavailableException mue)
        {
            mue.printStackTrace();
        }
        playr.stop();
    }

    /**
     * Various things.
     *
     * @param e The button pressed to activate this method.
     */
    public void actionPerformed(ActionEvent e)
    {
        String id = e.getActionCommand();
        if (id.equals("Audio Speed"))
        {

            clear();
            appear(new Dimension(350,250));
            addHeader("Change Audio Speed:");
            JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                    TEMPO_MIN, TEMPO_MAX, tempo_init);

            class SliderListener implements ChangeListener {
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider)e.getSource();
                    if (!source.getValueIsAdjusting()) {
                        sliderpos = (int)source.getValue();
                        update();
                    }    
                }
            }

            framesPerSecond.addChangeListener(new SliderListener());

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
            try{
                playr = new MusicHelper(new int[]{1,2,3,4,5});
            }
            catch(Exception ed)
            {
                System.out.println(ed);
            }
            //playr.setTempo(sliderpos);
            addButton("Test",playr);

        }
        else
        {
            //System.out.println(id);
            ui.setTempo(sliderpos);

        }
        //System.out.println(id);

    }
}
