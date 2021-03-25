import javax.swing.*;
import java.awt.*; 
/**
 * Write a description of class KeyPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KeyPanel extends JPanel
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class KeyPanel
     */
    public KeyPanel(int num, int[] key, String name)
    {
        int pt = name.indexOf("[");
        int pt2 = name.indexOf("]");
        String dispName;
        if (pt > -1)
        {
            dispName =  name.substring(0,pt-1) + name.substring(pt2+1);
        }
        else
        {
            dispName =  name;
        }
        String lbl = "#" + String.valueOf(num) + ": " + dispName;

        
        JButton jb1 = new JButton("Chords");    
        ChordViewer chrds = new ChordViewer(key,name);

        
        chrds.myKey = key;

        JButton jb2 = new JButton("Intervals");
        JButton jb3 = new JButton("Listen");    
        MusicPlayer playr = new MusicPlayer(key);
        jb3.addActionListener(playr);
        add(jb3);
        JButton jb4 = new JButton("Button 4");
        JButton jb5 = new JButton("Button 5");

        jb1.addActionListener(chrds);
        

        JLabel label = new JLabel(lbl);
        if (pt > -1)
        {
            label.setToolTipText(name.substring(pt+1,pt2));
        }
        //System.out.println(key.toString());

        add(label);
        add(jb1); 

    }

}
