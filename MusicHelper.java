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
    private Sequencer sequencer;
    private final int timeMult = 5;
    private final int length = 5;

    private boolean activated;
    /**
     * Constructor for objects of class MusicHelper
     */
    public MusicHelper(int[] keyAsInts) throws javax.sound.midi.InvalidMidiDataException
    {
        activated = false;
        savedNotes = keyAsInts;

        //fafs
    }

    private Sequence toMIDI(int[] notes, String type) throws javax.sound.midi.InvalidMidiDataException
    {
        Sequence seq = new Sequence(Sequence.PPQ,5,20);
        Track myTrack = seq.getTracks()[0];
        switch (type)
        {
            case "Listen":
            {
                int counter = 0;
                for (int note : savedNotes)
                {
                    addFullNote(myTrack, note, counter);
                    counter++;
                }
                addFullNote(myTrack,savedNotes[0] + 12, counter);
                break;
            }
            case "Play Chord":
            {
                for (int note : savedNotes)
                {
                    myTrack.add(toNote(note,1)[0]);
                    myTrack.add(toNote(note,20)[1]);
                }
                break;
            }
        }

        return seq;
    }

    private void addFullNote(Track myTrack, int pitch, int counter)
    {
        for (MidiEvent event : toNote(pitch,counter))
        {
            myTrack.add(event);
        }
    }

    private MidiEvent[] toNote(int pitch, int index)
    {
        MidiEvent[] events = new MidiEvent[2];
        events[0] = new MidiEvent(makeMessage(pitch,true),index*timeMult);
        events[1] = new MidiEvent(makeMessage(pitch,false),(index+1)*timeMult);
        return events;

    }

    private MidiMessage makeMessage(int note,boolean isOn)
    {
        int active = ShortMessage.NOTE_ON;
        if (!isOn)
        {
            active = ShortMessage.NOTE_OFF;
        }
        ShortMessage myMsg = new ShortMessage();
        try
        {
            // Start playing the note Middle C (60), 
            // moderately loud (velocity = 93).
            myMsg.setMessage(active, 0, 60 + (note - 1), 93); //middle c plus note value
        }
        catch (javax.sound.midi.InvalidMidiDataException imde)
        {
            imde.printStackTrace();
        }
        long timeStamp = -1;
        return myMsg;

    }

    public void setTempo(int newTempo) throws javax.sound.midi.MidiUnavailableException
    {
        if (tempo < 1)
        {
            return;
        }
        MidiSystem.getSequencer().setTempoInBPM(newTempo);
        //refreshSequence(savedKey);

    }

    public void stop()
    {
        //System.out.println("UNDECLARED METHOD STOP");
        sequencer.stop();

    }

    private void activate(String type)
    {
        try
        {
            mySequence = toMIDI(savedNotes,type);
        }
        catch (javax.sound.midi.InvalidMidiDataException imde)
        {
            imde.printStackTrace();
        }
        try
        {
            sequencer = MidiSystem.getSequencer();
        }
        catch (javax.sound.midi.MidiUnavailableException mue)
        {
            mue.printStackTrace();

        }
        try
        {
            sequencer.setSequence(mySequence);

        }
        catch (javax.sound.midi.InvalidMidiDataException imde)
        {
            imde.printStackTrace();
        }

        try
        {
            sequencer.open();
        }
        catch (javax.sound.midi.MidiUnavailableException mue2)
        {
            mue2.printStackTrace();
        }
        activated = true;
    }

    public void actionPerformed(ActionEvent e) {
        String id = e.getActionCommand();
        System.out.println("attempt to play" + id);
        
        if (!activated)
        {
            activate(id);

        }
        sequencer.stop();
        sequencer.start();
        //return;

    }

}
