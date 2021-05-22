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
    private MusicHelper mus;
    
    /**
     * Plays random notes.
     */
    public Sampler(int[] scale, String n)
    {
        
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

    public void act(String s)
    {
        clear();
        appear(STANDARD);
        addHeader(name + " - Sampler");
        addButton("Start",mus);
    }
}
