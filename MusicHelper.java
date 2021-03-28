import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.midi.*;
import javax.sound.midi.Sequence;
/**
 * Handles the playing of scales and chords. Replacement for removed class MusicPlayer.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class MusicHelper extends TheoryObj implements ActionListener
{
    // instance variables - replace the example below with your own
    private int tempo;
    private int[] savedNotes;
    private Sequence mySequence;
    /**
     * Constructor for objects of class MusicHelper
     */
    public MusicHelper(int[] keyAsInts) throws javax.sound.midi.InvalidMidiDataException
    {
        savedNotes = keyAsInts;
        mySequence = toMIDI(savedNotes);
        //fafs
    }

    private Sequence toMIDI(int[] notes) throws javax.sound.midi.InvalidMidiDataException
    {
        Sequence seq = new Sequence(Sequence.PPQ,5,1);
        
        
        
        return seq;
    }
    
    
    public void setTempo(int newTempo)
    {
        if (tempo < 1)
        {
            return;
        }
        tempo = newTempo;
        //refreshSequence(savedKey);

    }
    
    public void stop()
    {
        System.out.println("UNDECLARED METHOD STOP");
        
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println("attempt to play");
        return;
    }
    
}
