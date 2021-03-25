import org.jfugue.player.Player;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Write a description of class MusicPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MusicPlayer extends TheoryObj implements ActionListener
{
    // instance variables - replace the example below with your own
    private Player plyr;
    private String sequence;
    /**
     * Constructor for objects of class MusicPlayer
     */
    public MusicPlayer(int[] k)
    {
        plyr = new Player();
        String tempSequence = "";
        
        for (int note : k)
        {
            tempSequence += getNoteName(note) + "5q ";

        }
        tempSequence += getNoteName(1) + "6h";
        //System.out.println(tempSequence);
        sequence = "T140 ";
        for (int i = 0; i < tempSequence.length(); i++) {
            Character c = tempSequence.charAt(i);
            if (c.equals('♭'))
            {
                sequence += "b";
            }
            else
            {
               sequence += c; 
            }
            
        }
        //System.out.println(sequence);

    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(sequence);
        plyr.play(sequence);

        //System.out.print();
        //System.out.println(e);
    }

    
   
}
