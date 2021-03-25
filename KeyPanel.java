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
    private int x;

    /**
     * Constructor for objects of class KeyPanel
     */
    public KeyPanel(int num, int[] key, String name)
    {
        String lbl = "#" + String.valueOf(num) + ": " + name;

        
        
        JButton jb1 = new JButton("Chords");    
        ChordViewer chrds = new ChordViewer(key,name);
        chrds.myKey = key;
        
        JButton jb2 = new JButton("Intervals");
        JButton jb3 = new JButton("Listen");      
        JButton jb4 = new JButton("Button 4");
        JButton jb5 = new JButton("Button 5");
        
        jb1.addActionListener(chrds);
        jb1.addActionListener(chrds);


        JLabel label = new JLabel(name);
        //System.out.println(key.toString());

        add(label);
        add(jb1); add(jb3);

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
