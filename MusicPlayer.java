import javax.sound.midi.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
     * Handles the playing of scales and chords.
     *
     * @author Caleb Copeland
     * @version 5/24/21
     */
    public class MusicPlayer extends UpperBucketCrab implements ActionListener
    {
        private final int[] MY_NOTES;
        private Sequencer sequencer;
        private final int TIME_CONSTANT = 100;
        private boolean activated = false;

        private final int[] time_signature = new int[]{4,4};

        public void changeTimeSignature(int num, int index)
        {
            time_signature[index] = num;
        }

        public int getTimeSignature(int index)
        {
            return time_signature[index];
        }
    
        /**
         * Constructor for objects of class MusicHelper
         */
        public MusicPlayer(int[] notes, LowerBucketCrab link)
        {
            super(link);
            MY_NOTES = notes;
        }

        public MusicPlayer(int[] notes) {
            super();
            MY_NOTES = notes;
        }


        private Sequence toMIDI(String type) throws javax.sound.midi.InvalidMidiDataException
        {
        Sequence seq = new Sequence(Sequence.PPQ,5,20);
        Track mainTrack = seq.getTracks()[0];

        switch (type)
        {
            case KeyPanel.PLAY_TEXT:
            {
                int counter = 0;
                for (int note : MY_NOTES)
                {
                    addFullNote(mainTrack, note, counter);
                    counter++;
                }
                addFullNote(mainTrack, MY_NOTES[0] + 12, counter);

                break;
            }
            case ChordViewer.PLAY_TEXT:
            {
                addClump(mainTrack, MY_NOTES,0,20);
                break;
            }
            case Sampler.PLAY_TEXT:
            {
                System.out.println("playing in: " + time_signature[0] + "/" + time_signature[1]);

                double chord_length = 16 / (float) time_signature[1];
                for(int i = 1; i < 8; i++)
                {
                    // at the start of each measure...
                    double chord_start_time = (i-1) * chord_length;
                    addClump(mainTrack, TheoryObj.getRawChordAt(MY_NOTES,i), chord_start_time,chord_length);

                    for (double j = 0.0; j < time_signature[0] ; j++)
                    {
                        double this_quarter_note = j * (chord_length / ((double) time_signature[0] )) + chord_start_time;

                        for (double k = 0; k < 2; k++)
                        {
                            //in an eighth note pattern...
                            double this_eighth_note = k * (chord_length / ((double) time_signature[0] * 2)) +
                                    this_quarter_note;

                            // take a random note from the scale
                            addFullNote(mainTrack, MY_NOTES[MathHelper.getRandomNumberInRange(0,6)] + 12, this_eighth_note);
                        }
                        //in a quarter note pattern...
                        // pedal bass
                        addFullNote(mainTrack, MY_NOTES[0] -12, this_quarter_note);
                    }
                }
                double lastTime = 7*chord_length;
                addClump(mainTrack, TheoryObj.getRawChordAt(MY_NOTES,1), lastTime,chord_length);
                addFullNote(mainTrack, MY_NOTES[0] + 12, chord_length);
                addFullNote(mainTrack, MY_NOTES[0] - 12, chord_length);
                //mainTrack.add(new MidiEvent(new MetaMessage(MetaMessage.META), (long) (8*chord_length)))
                break;
            }
            default:
            {
                try
                {
                    Integer.valueOf(type);
                    return toMIDI(ChordViewer.PLAY_TEXT);
                }
                catch (java.lang.NumberFormatException e) // if the string is not a number, its not a chord
                {

                    return toMIDI(KeyPanel.PLAY_TEXT);
                }
            }
        }

        return seq;
    }



    private void addClump(Track myTrack, int[] notes, double offset,double length)
    throws javax.sound.midi.InvalidMidiDataException
    {
        for (int note : notes)
        {
            myTrack.add(toNote(note,offset     )[0]);
            myTrack.add(toNote(note,offset + length)[1]);
        }
    }

    private void addFullNote(Track myTrack, int pitch, double counter)
    throws javax.sound.midi.InvalidMidiDataException
    {
        for (MidiEvent event : toNote(pitch,counter))
        {
            myTrack.add(event);
        }
    }

    private MidiEvent[] toNote(int pitch, double index)
    throws javax.sound.midi.InvalidMidiDataException
    {
        MidiEvent[] events = new MidiEvent[2];

        events[0] = new MidiEvent(makeMessage(pitch+StateWatcher.getOffset(),true), (long) ( index * TIME_CONSTANT));
        events[1] = new MidiEvent(makeMessage(pitch+StateWatcher.getOffset(),false), (long) ((index + 1.0) * TIME_CONSTANT));
        return events;

    }

    private MidiMessage makeMessage(int note,boolean isOn)
    throws javax.sound.midi.InvalidMidiDataException
    {
        int active = ShortMessage.NOTE_ON;
        if (!isOn)
        {
            active = ShortMessage.NOTE_OFF;
        }
        ShortMessage myMsg = new ShortMessage();
        myMsg.setMessage(active, 0, 60 + (note - 1), 93); //middle c plus note value

        return myMsg;

    }

    /**
     * Sets this MusicPlayer's tempo to a given number.
     */
    public void setTempo(float newTempo)
    {
        if (newTempo < 1)
        {
            throw new IllegalArgumentException("Tempo must be greater than zero!");
        }

        sequencer.setTempoInBPM(newTempo*10);

    }

    /**
     * Stops the sequencer.
     */
    public void stop()
    {
        try {
            sequencer.stop();
        }
        catch (NullPointerException e)
        {
            // do nothing
        }
    }


    private void activate(String type) throws javax.sound.midi.InvalidMidiDataException
    {
        Sequence mySequence = toMIDI(type);
        sequencer.setSequence(mySequence);

        activated = true;
    }

    /**
     * Sets up the sequencer.
     */
    public void seqSetup() throws javax.sound.midi.MidiUnavailableException
    {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();

    }

    /**
     * Either stops or starts the player.
     */
    public void actionPerformed(ActionEvent e)
    {
        String id = e.getActionCommand();
        if (id.equals("Stop"))
        {
            stop();
        }
        else
        {
            if (id.equals(Sampler.PLAY_TEXT))
            {
                stop();
            }

            try
            {
                if (!activated)
                {
                    seqSetup();
                    activate(id);
                }
                sequencer.setTickPosition(0);
                StateWatcher.requestControl(this);
            }

            catch (MidiUnavailableException | InvalidMidiDataException mue)
            {
                mue.printStackTrace();
            }

            sequencer.start();
        }
    }

        @Override
        public void onPulledDown() {
            stop();
        }
    }
