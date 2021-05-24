import javax.sound.midi.MidiUnavailableException;

/**
 * Box that allows for change of the global tempo.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class TempoBox extends SliderBox
{
    private MusicHelper musicPlayer;

    /**
     * Creates the TempoBox.
     */
    public TempoBox()
    {
        super("Change Note Speed", ROUNDISH,
                StateWatcher.getTempo(),50,500);
        addHeader("Change Note Speed:");
        try{
            musicPlayer = new MusicHelper(new int[]{1,3,5,6,8,10,12});
            musicPlayer.seqSetup();
        }
        catch(MidiUnavailableException ed)
        {
            ed.printStackTrace();
        }

        getSlider().setMajorTickSpacing(50);
        add(getSlider());
        addButton("Test", musicPlayer);
    }

    protected void apply()
    {
        StateWatcher.setTempo(getPos());
    }

}
