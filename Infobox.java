import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * Write a description of class InfoBox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Infobox extends EasyFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    private final Dimension MYDIM = new Dimension(200,300);
    int[] myScale;
    String myName;
    String myType;

    /**
     * Constructor for objects of class InfoBox
     */
    public Infobox(int[] scale, String name,String type)
    {
        // initialise instance variables
        int ind = getEnclosers(name,"()")[0];
        if (ind > -1)
        {
            myName =name.substring(0, ind);

        }
        else
        {
            myName = name;
        }
        ind = getEnclosers(name,"{}")[0];
        if (ind > -1)
        {
            myName =name.substring(0, ind);

        }
        else
        {
            myName = name;
        }
        myType = type;
}

    
    
    public void actionPerformed(ActionEvent e)
    {
        clear();
        appear(MYDIM);
        addHeader(myName + " - " + myType);
        
    }
}
