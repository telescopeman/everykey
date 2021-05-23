
/**
 * Box that allows for change of the global tempo.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public class TempoBox extends SliderBox
{
    private final int TEMPO_MIN = 50;
    private final int TEMPO_MAX = 500;
    
    private MusicHelper playr;

    /**
     * Creates the TempoBox.
     */
    public TempoBox()
    {
        addHeader("Change Note Speed:");
        try{
            playr = new MusicHelper(new int[]{1,3,5,6,8,10,12});
            playr.seqSetup();
        }
        catch(Exception ed)
        {
            System.out.println(ed);
        }
        setUpSlider(StateWatcher.getTempo(),TEMPO_MIN,TEMPO_MAX);

        getSlider().setMajorTickSpacing(50);
        add(getSlider());

        addButton("Test",playr);
    }

    

    
    public void update(float d)
    {
        StateWatcher.setTempo(d);
    }



    public void act(String id)
    {
        if (id.equals("Change Note Speed"))
        {
            appear(ROUNDISH);
        }
        else
        {
            update(getPos());
        }
        
    }
}
