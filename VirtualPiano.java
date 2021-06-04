import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * A virtual piano that can be played with the keyboard or mouse, and can convert notes
 * played into a custom filter.
 *
 * @version 6/4/21
 *
 * @author smitha.r from dreamincode.net, Caleb Copeland
 * 
 */
public class VirtualPiano extends ModBox implements MouseListener {

    private static final String
            START_RECORDING = "Start Recording Filter",
            STOP_RECORDING = "Stop Recording Filter",
            TO_FILTER = "Save as New Filter",
            EXIT = "Discard",
            SHOW_INFO = "About Musical Typing";

    public static final String TYPED = "typed",
            RELEASED = "released";

    private PianoKey[] pianoKeys;

    private final JButton
            toggle_recording_button = new JButton(START_RECORDING),
            make_filter_button = new JButton(TO_FILTER),
            exit_button = new JButton(EXIT);

    private MidiChannel channel;
    private JLayeredPane panel;

    private boolean isRecording = false,
            lastSet = false;

    private int[] pressedKeys;

    private int numWhite = 0,
            numBlack = 0,
            lastPitch,
            index = 0;

    public static final int
            WHITE_WIDTH = 60,
            WHITE_HEIGHT = WHITE_WIDTH * 240 / 40,
            BLACK_WIDTH = WHITE_WIDTH * 16 / 20,
            BLACK_HEIGHT = WHITE_HEIGHT * 80 / 120;


    public VirtualPiano() {
        super(1,1);
    }

    public void open() throws javax.sound.midi.MidiUnavailableException
    {
        clear();
        final int FRAME_WIDTH = 14 * WHITE_WIDTH;
        pressedKeys = new int[7];
        setResizable(false);
        setSize(FRAME_WIDTH,3 * WHITE_HEIGHT / 2);
        setRecState(false);
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[1];

        panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(FRAME_WIDTH, WHITE_HEIGHT));
        add(panel);


        int maxKeys = 12;
        pianoKeys = new PianoKey[maxKeys];
        for (int i = 0; i < maxKeys; i++) {

            pianoKeys[i] = new PianoKey(i,this);

            if (pianoKeys[i].isWhite)
            {
                panel.add(pianoKeys[i], 0, -1);
                numWhite++;
            }
            else
            {
                panel.add(pianoKeys[i], 1, -1);
                numBlack++;
            }
        }

        addButtons();
        pack();

        setVisible(true);
        FilterBank.storeFilters();
        requestFocusInWindow();

    }

    @SuppressWarnings("SuspiciousNameCombination")
    private void addButtons()
    {

        final int BUTTON_WIDTH = 9* WHITE_HEIGHT /16;
        final int BUTTON_POSITION_X = 10 * WHITE_WIDTH + 6;
        final int INIT_OFFSET = 20;

        toggle_recording_button.addActionListener(this);
        toggle_recording_button.setLocation(BUTTON_POSITION_X, INIT_OFFSET);
        toggle_recording_button.setSize(BUTTON_WIDTH,WHITE_WIDTH);
        panel.add(toggle_recording_button, 2);


        make_filter_button.addActionListener(this);
        make_filter_button.setEnabled(false);
        make_filter_button.setLocation(BUTTON_POSITION_X, INIT_OFFSET + WHITE_WIDTH);
        make_filter_button.setSize(BUTTON_WIDTH,WHITE_WIDTH);
        panel.add(make_filter_button, 2);


        exit_button.addActionListener(this);
        exit_button.setEnabled(false);
        exit_button.setLocation(BUTTON_POSITION_X, INIT_OFFSET + 2 * WHITE_WIDTH);
        exit_button.setSize(BUTTON_WIDTH,WHITE_WIDTH);
        panel.add(exit_button, 2);

        JButton b4 = new JButton(SHOW_INFO);
        b4.addActionListener(this);
        b4.setLocation(BUTTON_POSITION_X, INIT_OFFSET + 3 * WHITE_WIDTH);
        b4.setSize(BUTTON_WIDTH,WHITE_WIDTH);
        panel.add(b4, 2);

    }

    public void playNote(int pitch, JButton b,boolean pl)
    {
        if (pl == lastSet && pitch == lastPitch)
        {
            return;
        }
        lastPitch = pitch;
        lastSet = pl;
        if (pl)
        {
            channel.noteOn(pitch, 64);
            b.setBackground(Color.RED);
            if (isRecording)
            {
                if (addKey(pitch))
                {
                    apply();
                }
                
            }

        }
        else
        {
            channel.noteOff(pitch, 64);
            if (isRecording)
            {
                b.setBackground(Color.GREEN);
            }
            else
            {
                b.setBackground(Color.WHITE);
            }

        }
    }

    private boolean addKey(int pitch)
    {
        int converted = (pitch - 49) % 12;
        if (ArrayHelper.contains(pressedKeys,converted))
        {
            return false;
        }
        if (pressedKeys[index] > 0)
        {
            pianoKeys[pressedKeys[index]-1].setBackground(Color.WHITE);
        }
        pressedKeys[index] = converted;
        advance();
        return true;
    }

    private void startRecording()
    {
        setRecState(true);
        FilterBank.storeFilters();
        make_filter_button.setEnabled(true);
        exit_button.setEnabled(true);
        for(JButton key : pianoKeys)
        {
            key.setBackground(Color.WHITE);
        }
        pressedKeys = new int[7];
        clearKeys();
        toggle_recording_button.setText(STOP_RECORDING);
    }

    protected void apply()
    {
        FilterBank.setFilterStatuses(ArrayHelper.addX(FilterBank.getStoredStatuses(),true));
        FilterBank.setCurFilters(ArrayHelper.addX(FilterBank.getStoredFilters(),toFilter()));
    }

    public int getNumWhite()
    {
        return numWhite;
    }

    public int getNumBlack()
    {
        return numBlack;
    }

    private void save()
    {
        apply();
        setVisible(false);
        FilterBank.storeFilters();
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e)
    {
        PianoKey b = (PianoKey) e.getSource();
        playNote(b.getIndex()+50,b,false);
    }

    public void mousePressed(MouseEvent e)
    {
        PianoKey b = (PianoKey) e.getSource();
        playNote(b.getIndex()+50,b,true);
    }

    public void mouseClicked(MouseEvent e) {

    }

    private void discard()
    {
        FilterBank.setCurFilters(FilterBank.getStoredFilters());
        make_filter_button.setEnabled(false);
        exit_button.setEnabled(false);
        for(JButton key : pianoKeys)
        {
            key.setBackground(Color.WHITE);
        }
    }

    private void clearKeys()
    {
        int counter = 0;
        for (int ignored : pressedKeys)
        {
            pressedKeys[counter] = -1;
            counter++;
        }
    }

    private void advance()
    {
        index++;
        if (index > 6)
        {
            index = index % 7;
        }
    }

    private void stopRecording()
    {
        setRecState(false);
        toggle_recording_button.setText(START_RECORDING);
    }
    
    private void setRecState(boolean d)
    {
        isRecording = d;
    }

    private Filter toFilter()
    {
        int[] keys = new int[]{};
        for (int i : pressedKeys)
        {
            if (i > 0)
            {
                keys = ArrayHelper.addX(keys,i);
            }

        }
        return new Filter(keys);
    }

    protected void act(String id) {
        switch (id)
        {
            case START_RECORDING:
            {
                startRecording();
                break;
            }
            case STOP_RECORDING:
            {
                stopRecording();
                break;
            }

            case TO_FILTER:
            {
                stopRecording();
                save();
                break;
            }
            case EXIT:
            {
                stopRecording();
                discard();
                break;
            }

            case SHOW_INFO:
            {
                JOptionPane.showMessageDialog(null, 
                    "Musical Typing mode allows for creation of filters" +
                        "\nby way of inputting notes. Simply start recording, " +
                        "\ninput the desired notes, then click Save as New Filter \nto create a new filter.");
                break;
            }

            default:
            {
                try
                {
                    open();
                }
                catch (javax.sound.midi.MidiUnavailableException mue)
                {
                    mue.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onClosed() {
        discard();
        channel.allNotesOff();
    }
}