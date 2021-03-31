/**
 * Samples random notes and all chords from a scale to give a feel for its sound.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public class Sampler extends EasyFrame
{
    
    private int[] myScale;
    private String name;
    MusicHelper mus;
    
    /**
     * Plays random notes.
     */
    public Sampler(int[] scale, String n)
    {
        
        setTitle("Random Sampler");
        int ind = getEnclosers(n,"()")[0];
        
        mus = new MusicHelper(scale);
        if (ind > -1)
        {
            name =n.substring(0, ind);

        }
        else
        {
            name = n;
        }
        ind = getEnclosers(n,"{}")[0];
        if (ind > -1)
        {
            name =n.substring(0, ind);

        }
        else
        {
            name = n;
        }
        
        myScale = scale;
    }

    public void act(String id)
    {
        clear();
        appear(STANDARD);
        addHeader(name + " - Sampler");
        addButton("Start",mus);
    }
}
