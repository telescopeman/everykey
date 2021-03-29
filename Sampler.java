import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

/**
 * Write a description of class Sampler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sampler extends EasyFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    private int[] myScale;
    private String name;
    private final Dimension STANDARD = new Dimension(350,100);
    MusicHelper mus;
    
    /**
     * Plays random notes.
     */
    public Sampler(int[] scale, String n)
    {
        // initialise instance variables
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

    public void actionPerformed(ActionEvent e)
    {
        clear();
        appear(STANDARD);
        addHeader(name + " - Sampler");
        addButton("Start",mus);
    }
}
