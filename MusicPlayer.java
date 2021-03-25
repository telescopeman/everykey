import org.jfugue.player.ManagedPlayer;
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
    private int tempo = 140;
    
    private int[] savedKey;
    /**
     * Constructor for objects of class MusicPlayer
     */
    public MusicPlayer(int[] k)
    {
        plyr = new Player();
        savedKey = k;
        refreshSequence(k);

    }
    
    public void refreshSequence(int[] k)
    {
        
        String tempSequence = "";

        for (int note : k)
        {
            tempSequence += getNoteName(note) + "5q ";

        }
        tempSequence += getNoteName(1) + "6w";
        //System.out.println(tempSequence);
        sequence = "T" + String.valueOf(tempo) + " ";
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

    /**
     * Constructor for objects of class MusicPlayer
     */
    public MusicPlayer(String seq)
    {
        plyr = new Player();

        sequence = "T140 ";
        for (int i = 0; i < seq.length(); i++) {
            Character c = seq.charAt(i);
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

    public void setTempo(int newTempo)
    {
        if (tempo < 1)
        {
            return;
        }
        tempo = newTempo;
        refreshSequence(savedKey);

    }

    public void stop()
    {
        plyr.getManagedPlayer().finish();
    }
    
    public void actionPerformed(ActionEvent e) {

        //System.out.println(sequence);
        class MyThread extends Thread { //this is here to prevent app freezing during note play

            public void run(){
                plyr.play(sequence);
            }
        }

        if (plyr.getManagedPlayer().isPlaying())
        {
            stop();
        }

        MyThread thr = new MyThread();
        thr.start();


        //System.out.print();
        //System.out.println(e);
    }

}
