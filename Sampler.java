/**
 * Samples random notes and all chords from a scale to give a feel for its sound.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 */
public class Sampler extends CrabFrame
{
    private String name;
    private final MusicPlayer mus;
    
    /**
     * Plays random notes.
     */
    public Sampler(int[] scale, String n)
    {
        super(n,STANDARD);
        setTitle("Random Sampler");
        int ind = n.indexOf('(');

        mus = new MusicPlayer(scale,this);




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

    }

    public void act(String s)
    {
        clear();
        addHeader(name + " - Sampler");
        addButton("Start",mus);
        appear();
    }
}
