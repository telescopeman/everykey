import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Samples random notes and all chords from a scale to give a feel for its sound.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public class Sampler extends ListeningFrame
{
    
    private final int[] myScale;
    private String name;
    private final MusicHelper mus;
    
    /**
     * Plays random notes.
     */
    public Sampler(int[] scale, String n)
    {
        super(n,STANDARD);
        setTitle("Random Sampler");
        int ind = n.indexOf('(');

        mus = new MusicHelper(scale);




        if (ind > -1)
        {
            name =n.substring(0, ind);

        }
        else
        {
            name = n;
        }
        ind = n.indexOf('{');
        if (ind > -1)
        {
            name = n.substring(0, ind);

        }
        else
        {
            name = n;
        }
        
        myScale = scale;
        
    }

    /**
     * When the window is closed, stop playing.
     */
    @Override
    protected void onClosed() {
        mus.stop();
    }

    public void act(String s)
    {
        clear();
        addHeader(name + " - Sampler");
        addButton("Start",mus);
        appear();
    }
}
