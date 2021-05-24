import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.AbstractAction;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.JOptionPane;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * A virtual piano that can be played with the keyboard or mouse, and can convert notes
 * played into a custom filter.
 *
 * @version 5/23/21
 *
 * @author smitha.r from dreamincode.net, Caleb Copeland
 * 
 */
public class VirtualPiano extends ModBox {
    private final String[]
            WHITE_KEY_BUTTONS = new String[]{"A","S","D","F","G","H","J","K","L","SEMICOLON"},
            BLACK_KEY_BUTTONS = new String[]{"W","E","T","Y","U","O","P"};

    private final String
            START_RECORDING = "Start Recording Filter",
            STOP_RECORDING = "Stop Recording Filter",
            TO_FILTER = "Save as New Filter",
            EXIT = "Discard",
            SHOW_INFO = "About Musical Typing";

    private int[] pressedKeys;

    private JButton[] list;
    private JButton b1,b2,b3;

    private MidiChannel channel;
    private JLayeredPane panel;
    private boolean isRecording = false,
            lastSet = false;

    private int lastPitch,
        index;
    private final int width = 60,
            height = width * 240 / 40,
            maxKeys = 12;

    public VirtualPiano() {
        super(1,1);
    }

    public void open() throws javax.sound.midi.MidiUnavailableException
    {
        clear();
        pressedKeys = new int[7];
        index = 0;
        setResizable(false);
        setSize(getDim(14*width,3 * height / 2));
        setRecState(false);
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[1];

        panel = new JLayeredPane();
        panel.setPreferredSize(getDim(14*width,height));
        add(panel);

        addWindowListener(new java.awt.event.WindowAdapter()
            {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    discard();
                }
            });

        int width2 = width * 16 / 20;
        int height2 = height * 80 / 120;

        
        class PlayAction extends AbstractAction {
            private final int pitch;
            private final JButton bu;
            private final boolean type;

            public PlayAction(int i, JButton b,boolean kind)
            {
                pitch = i;
                bu = b;
                type = kind;
            }

            public void actionPerformed(ActionEvent e) {
                go(type);
            }

            public void go(boolean s)
            {
                playNote(pitch,bu,s);

            }
        }

        int numWhite = 0;
        int numBlack = 0;
        list = new JButton[maxKeys];
        for ( int i = 0; i < maxKeys; i++) {

            final int ja =i+50;

            list[i] = new JButton();
            JButton b = list[i];

            b.setOpaque(true);
            int j = i % 12;
            boolean isWhite = (j == 0 || j == 2|| j == 4|| j == 5|| j == 7|| j ==9|| j == 11);

            final String pr = "typed";
            final String rl = "released";
            b.addMouseListener( new MouseListener() 
                {
                    public void mouseExited(MouseEvent e)
                    {
                    }

                    public void mouseEntered(MouseEvent e)
                    {
                    }

                    public void mouseReleased(MouseEvent e)
                    {
                        JButton b = (JButton) e.getSource();
                        PlayAction th = (PlayAction) b.getActionMap().get(pr);
                        th.go(false);
                    }

                    public void mousePressed(MouseEvent e)
                    {
                        JButton b = (JButton) e.getSource();
                        PlayAction th = (PlayAction) b.getActionMap().get(pr);
                        th.go(true);
                    }

                    public void mouseClicked(MouseEvent e)
                    {

                    }
                }
            );
            String mnem;
            if (isWhite)
            {
                mnem = WHITE_KEY_BUTTONS[numWhite];
            }
            else
            {
                mnem = BLACK_KEY_BUTTONS[numBlack];
            }
            b.setVerticalAlignment( SwingConstants.BOTTOM );
            if (mnem.equals("SEMICOLON"))
            {
                b.setText(";");
            }
            else
            {
                b.setText(mnem);
            }

            b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed " + mnem), pr);
            b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + mnem),rl);
            Action ac = new PlayAction(ja,b,true);
            Action ac2 = new PlayAction(ja,b,false);
            b.getActionMap().put(pr, ac);
            b.getActionMap().put(rl, ac2);
            if (isWhite)
            {
                b.setBackground(Color.WHITE);
                b.setLocation(numWhite * width, 0);
                b.setSize(width, height);
                panel.add(b, 0, -1);
                numWhite++;
            }
            else
            {
                b.setLocation((numWhite-1)*(width) + (width2*3/4), 0);
                b.setSize(width2, height2);
                b.setBackground(Color.WHITE);
                panel.add(b, 1, -1);
                numBlack++;
            }

            list[i] = b;
        }

        addButtons();
        pack();

        setVisible(true);
        FilterBank.storeFilters();
        requestFocusInWindow();

    }

    private void addButtons()
    {
        Dimension myDim = getDim(9*height/16, width);

        b1 = new JButton(START_RECORDING);
        b1.addActionListener(this);
        b1.setLocation(10 * width + 6, 20);
        b1.setSize(myDim);
        panel.add(b1, 2);

        b2 = new JButton(TO_FILTER);
        b2.addActionListener(this);
        b2.setEnabled(false);
        b2.setLocation(10 * width + 6, 20 + width);
        b2.setSize(myDim);
        panel.add(b2, 2);

        b3 = new JButton(EXIT);
        b3.addActionListener(this);
        b3.setEnabled(false);
        b3.setLocation(10 * width + 6, 20 + 2* width);
        b3.setSize(myDim);
        panel.add(b3, 2);

        JButton b4 = new JButton(SHOW_INFO);
        b4.addActionListener(this);
        b4.setLocation(10 * width + 6, 20 + 3* width);
        b4.setSize(myDim);
        panel.add(b4, 2);

    }

    private void playNote(int pitch, JButton b,boolean pl)
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
            list[pressedKeys[index]-1].setBackground(Color.WHITE);
        }
        pressedKeys[index] = converted;
        advance();
        return true;
    }

    private void startRecording()
    {
        setRecState(true);
        FilterBank.storeFilters();
        b2.setEnabled(true);
        b3.setEnabled(true);
        for(JButton key : list)
        {
            key.setBackground(Color.WHITE);
        }
        pressedKeys = new int[7];
        clearKeys();
        b1.setText(STOP_RECORDING);

    }

    protected void apply()
    {
        FilterBank.setFilterStatuses(ArrayHelper.addX(FilterBank.getStoredStatuses(),true));
        FilterBank.setCurFilters(ArrayHelper.addX(FilterBank.getStoredFilters(),toFilter()));
    }

    private void save()
    {
        apply();
        setVisible(false);
        FilterBank.storeFilters();
    }

    private void discard()
    {
        FilterBank.setCurFilters(FilterBank.getStoredFilters());
        b2.setEnabled(false);
        b3.setEnabled(false);
        for(JButton key : list)
        {
            key.setBackground(Color.WHITE);
        }
    }

    private void clearKeys()
    {
        int counter = 0;
        for (int i : pressedKeys)
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
        b1.setText(START_RECORDING);
    }
    
    private void setRecState(boolean d)
    {
        StateWatcher.isPianoOpen = d;
        isRecording = d;
        StateWatcher.togglePiano();
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
}