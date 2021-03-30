import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.midi.*;
import javax.sound.midi.Sequence;
import java.util.Random;

/**
 * Handles the playing of scales and chords. Replacement for removed class MusicPlayer.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class MusicHelper extends TheoryObj implements ActionListener
{
    // instance variables - replace the example below with your own

    private int[] savedNotes;
    private Sequence mySequence;
    private Sequencer sequencer;
    private final int timeMult = 5;
    private final int length = 5;
    private float tempo;

    private boolean activated;

    /**
     * Constructor for objects of class MusicHelper
     */
    public MusicHelper(int[] keyAsInts)
    {
        activated = false;
        savedNotes = keyAsInts;

        //fafs
    }

    /**
     * Constructor for objects of class MusicHelper
     */
    public MusicHelper()
    {
        activated = false;

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
                //myTrack
                break;
            }
            case "Play Chord":
            {
                addClump(myTrack,savedNotes,0);
                break;
            }
            case "Start": //sampler player
            {
                for(int i = 0; i < 7; i++)
                {
                    addClump(myTrack,getRawChordAt(savedNotes,i+1),i*timeMult);
                    int counter = 0;
                    for (int j = 0; j < 4; j++)
                    {
                        addFullNote(myTrack, savedNotes[getRandomNumberInRange(0,6)] + 12, counter + i * 4);
                        counter++;
                    }
                }
                break;
            }
            default:
            {
                try 
                {
                    Integer.valueOf(type);
                    return toMIDI(notes,"Play Chord");
                }
                catch (java.lang.NumberFormatException e)
                {

                    return toMIDI(notes,"Listen");
                }
            }
        }

        return seq;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void addClump(Track myTrack, int[] notes, int offset)
    {
        for (int note : notes)
        {
            myTrack.add(toNote(note,offset     )[0]);
            myTrack.add(toNote(note,offset + 20)[1]);
        }
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

    public void setTempo(float newTempo) throws javax.sound.midi.MidiUnavailableException
    {
        tempo = newTempo;
        //System.out.println("set");
        if (newTempo < 1)
        {
            System.out.println("Invalid tempo!");
            return;
        }

        sequencer.setTempoInBPM(newTempo);

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
            sequencer.setSequence(mySequence);
        }
        catch (javax.sound.midi.InvalidMidiDataException imde)
        {
            imde.printStackTrace();
        }
        activated = true;
    }

    public void seqSetup() throws javax.sound.midi.MidiUnavailableException
    {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
    }

    public void actionPerformed(ActionEvent e) {
        String id = e.getActionCommand();
        if (id.equals("Stop"))
        {
            stop();
        }
        //System.out.println(sequencer.getTempoInBPM());

        if (!activated)
        {
            try
            {
                seqSetup();
                //setTempo(tempo);
            }
            catch (javax.sound.midi.MidiUnavailableException mue)
            {
                mue.printStackTrace();
            }

            activate(id);

        }

        sequencer.setTickPosition(0);
        PlayerWatcher.requestControl(this); // DO NOT CHANGE ORDER HERE
        //System.out.println(sequencer.getTempoInBPM());
        sequencer.start();
        //return;

    }

}
