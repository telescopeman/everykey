
/**
 * Box that allows for change of the global tempo.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class TempoBox extends SliderBox
{
    private final int TEMPO_MIN = 50;
    private final int TEMPO_MAX = 500;
    
    private MusicHelper playr;

    /**
     * Creates a box bound to a certain UI.
     */
    public TempoBox(UIStuff uiref)
    {
        super(uiref);// initialise instance variables
        
        addHeader("Change Note Speed:");
        
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

    
    public void softUpdate(float d)
    {
        PlayerWatcher.setTempo(d);
    }


    public void act(String id)
    {
        if (id.equals("Change Note Speed"))
        {
            appear(getDim(350,250));
        }
        else
        {
            update(sliderpos);
        }
        
    }
}
