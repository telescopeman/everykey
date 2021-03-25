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
public class ChordViewer extends EasyFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    public int[] myKey;

    /**
     * Constructor for objects of class ChordViewer
     */
    public ChordViewer(int[] k, String name)
    {
        setSize(new Dimension(700, 100));
        setResizable(false);
        setLayout(new GridLayout(2, 7));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myKey= k;
        

        
    }

    public static Chord getChordAt(int[] key, int ind)
    {
        int index = ind -1;
        final int LOOP = 7;
        System.out.print(ind + "-->");
        if (index > LOOP)
        {
            return(new Chord(0,0,0));
        }

        //return "Test Chord";
        //System.out.println(String.valueOf(index) + String.valueOf((index + 2) % LOOP) + String.valueOf((index + 4) % LOOP) + "-->" + key[index] + key[(index + 2) % LOOP] + key[(index + 4) % LOOP]);
        return new Chord(key[index], key[(index + 2) % LOOP],key[(index + 4) % LOOP] );

    }

    public void actionPerformed(ActionEvent e) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.print(e);
        clear();
        
        for(int i = 1; i < 8; i++)
        {
            add(new JLabel(String.valueOf(i), JLabel.CENTER));
        }
        for(int i = 1; i < 8; i++)
        {
            Chord aChord = getChordAt(myKey,i);

            add(new JLabel(aChord.toString(), JLabel.CENTER));
        }
        show();
    }
}
