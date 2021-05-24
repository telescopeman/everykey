
/**
 * Box that allows for change of the global tempo.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class TempoBox extends SliderBox
{
    private final int TEMPO_MIN = 50;
    private final int TEMPO_MAX = 500;
    
    private MusicHelper musicPlayer;

    /**
     * Creates the TempoBox.
     */
    public TempoBox()
    {
        super("Change Note Speed", ROUNDISH);
        addHeader("Change Note Speed:");
        try{
            musicPlayer = new MusicHelper(new int[]{1,3,5,6,8,10,12});
            musicPlayer.seqSetup();
        }
        catch(Exception ed)
        {
            System.out.println(ed);
        }
        setUpSlider(StateWatcher.getTempo(),TEMPO_MIN,TEMPO_MAX);

        getSlider().setMajorTickSpacing(50);
        add(getSlider());

        addButton("Test", musicPlayer);
    }

    protected void apply()
    {
        StateWatcher.setTempo(getPos());
    }

}
