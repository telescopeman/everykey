import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 
/**
 * Write a description of class ChordViewer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChordViewer extends JFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    public int[] key;

    /**
     * Constructor for objects of class ChordViewer
     */
    public ChordViewer()
    {
        setSize(new Dimension(700, 100));
        setLayout(new GridLayout(2, 7));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(int i = 1; i < 8; i++)
        {
            add(new JLabel(String.valueOf(i), JLabel.CENTER));
        }
        for(int i = 1; i < 8; i++)
        {
            add(new JLabel(TheoryHelper.getChordAt(key,i), JLabel.CENTER));
        }
        
        
    }

 
     
    public void actionPerformed(ActionEvent e) {
        show();
        System.out.println(e);
}
}
