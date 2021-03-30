import javax.swing.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
//import javax.swing.*;
//import javax.media.*;
//import javax.media.Controller;
//import javax.media.Player;
import java.io.*;
import javax.swing.JPanel;
import javax.sound.midi.Synthesizer;
//import javax.swing.JComboBox;

/**
 * @author smitha.r from dreamincode.net, Caleb Copeland
 * @since March 15, 2012
 */
public class VirtualPiano extends ModBox {
    private final String[] whitemnems = new String[]{"A","S","D","F","G","H","J","K","L","SEMICOLON"};
    private final String[] blackmnems = new String[]{"W","E","T","Y","U","O","P"};

    private final String startrec = "Start Recording";
    private final String stoprec = "Stop Recording";
    private final String toFilter = "Apply New Filter Set";

    
    MidiChannel channel;
    JLayeredPane panel;
    private boolean isRecording = false;
    private boolean lastSet = false;
    private int lastPitch;
    final int width = 40;
    final int height = 240;
    public VirtualPiano(UIStuff uir) {
        super(uir);

    }

    public void open() throws javax.sound.midi.MidiUnavailableException
    {
        appear();
        isRecording = false;
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[1];
        JFrame frame = new JFrame("Live Player"); //makes the JFrame

        final int velocity = 64; 

        panel = new JLayeredPane();
        frame.add(panel);

        int maxKeys = 17;

        int width2 = width * 16 / 20;
        int height2 = height * 80 / 120;

        class PlayAction extends AbstractAction {
            private int pitch;
            private JButton bu;
            private boolean type = false;
            public PlayAction(int i, JButton b,boolean kind)
            {
                pitch = i;
                bu = b;
                type = kind;
            }

            public void actionPerformed(ActionEvent e) {
                go(type);
                //System.out.println("Action for first button/menu item: " + e);
            }

            public void go(boolean s)
            {
                playNote(pitch,bu,s);

            }
        }

        int numWhite = 0;
        int numBlack = 0;
        for ( int i = 0; i < maxKeys; i++) {

            final int ja =i+50;

            JButton b = new JButton();

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
                        // JButton b = (JButton) e.getSource();
                        // PlayAction th = (PlayAction) b.getActionMap().get(pr);
                        // th.go(true);
                    }
                }
            );
            String mnem;
            if (isWhite)
            {
                mnem = whitemnems[numWhite];
            }
            else
            {
                mnem = blackmnems[numBlack];
            }

            b.getInputMap(b.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed " + mnem), pr);
            b.getInputMap(b.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + mnem),rl);
            //OscillationPreventor osc = new OscillationP
            Action ac = new PlayAction(ja,b,true);
            Action ac2 = new PlayAction(ja,b,false);
            b.getActionMap().put(pr, ac);
            b.getActionMap().put(rl, ac2);
            if (isWhite)
            {

                b.setBackground(Color.WHITE);
                //b.setMnemonic(whitemnems[numWhite].charAt(0));
                //System.out.println(KeyStroke.getKeyStroke("pressed " + mnem));

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
        }

        addButtons();

        //frame.pack();
        frame.setSize(13*width,height);
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }

    private void addButtons()
    {
        JButton b1 = new JButton("Start Recording");
        b1.addActionListener(this);
        b1.setLocation(10 * width, 20);
        b1.setSize(height/2, width);
        panel.add(b1, 2);
        
    }

    public void playNote(int pitch, JButton b,boolean pl)
    {
        //System.out.println(pl);
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

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand())
        {
            case startrec:
            {
                isRecording = true;
                break;
            }

            case stoprec:
            {
                isRecording = false;
                break;
            }
            case toFilter:
            {
                isRecording = false;
                break;
                }
                
            default:
            {
                isRecording = false;
                break;
            }
        }
        
    }