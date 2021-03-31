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
public class TempoBox extends SliderBox
{
    // instance variables - replace the example below with your own
    public float sliderpos;

    static final int TEMPO_MIN = 50;
    static final int TEMPO_MAX = 500;
    //static float tempo_init;
    private MusicHelper playr;

    //JSlider slider;

    class SliderListener implements ChangeListener {
        private JSlider sl;
        public SliderListener(JSlider s)
        {
            sl = s;

        }

        public void stateChanged(ChangeEvent e) {
            sliderpos = (int)sl.getValue();
            update(sl.getValue());

            //System.out.println("upd" + slider.getValue());
        }
    }

    /**
     * Constructor for objects of class SettingsBox
     */
    public TempoBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //x = 0;
        //sliderpos = PlayerWatcher.getTempo();
        addHeader("Change Note Speed:");
        //System.out.println("cns");
        try{
            playr = new MusicHelper(new int[]{1,3,5,6,8,10,12});
            playr.seqSetup();
        }
        catch(Exception ed)
        {
            System.out.println(ed);
        }
        setUpSlider(PlayerWatcher.getTempo(),TEMPO_MIN,TEMPO_MAX);

        slider.setMajorTickSpacing(50);

        add(slider);

        addButton("Test",playr);
    }

    //@Override
    public void update()
    {
        PlayerWatcher.setTempo((int)sliderpos);
    }

    //@Override
    public void update(float d)
    {
        PlayerWatcher.setTempo(d);
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
        else
        {
            update(sliderpos);
        }
        
    }
}
