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
import javax.swing.JMenuBar;
//import javax.swing.*;
//import javax.media.*;
//import javax.media.Controller;
//import javax.media.Player;
import java.io.*;
import javax.swing.JPanel;
import javax.sound.midi.Synthesizer;
import javax.swing.JComboBox;

/**
 * @author smitha.r from dreamincode.net
 * @since March 15, 2012
 */
public class VirtualPiano implements KeyListener {
    private final String[] whitemnems = new String[]{"A","S","D","F","G","H","J","K","L","SEMICOLON"};
    private final String[] blackmnems = new String[]{"W","E","T","Y","U","O","P"};
    private Synthesizer synthesizer = MidiSystem.getSynthesizer();
    private final MidiChannel channel = synthesizer.getChannels()[1];
    public VirtualPiano() throws MidiUnavailableException{

        synthesizer.open();
        JFrame frame = new JFrame("Live Player"); //makes the JFrame

        final int velocity = 64; 

        JLayeredPane panel = new JLayeredPane();
        frame.add(panel);

        int maxKeys = 17;

        int width = 40;
        int height = 240;
        int width2 = width * 16 / 20;
        int height2 = height * 80 / 120;

        class PlayAction extends AbstractAction {
            private int pitch;
            private JButton bu;
            private boolean on;
            private boolean last;

            public PlayAction(int i, JButton b, boolean tog) {
                //super(text);
                pitch = i;
                bu = b;
                on = tog;
                //putValue(SHORT_DESCRIPTION, desc);
                //putValue(MNEMONIC_KEY, mnemonic);
            }

            public void actionPerformed(ActionEvent e) {
                if (!bu.isSelected())
                {
                    playNote(pitch,bu,on);
                }
                System.out.println("Action for first button/menu item: " + e);
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

            b.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    { 
                        System.out.println(e);

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
            final String pr = "go";
            final String rl = "stop";
            b.getInputMap(b.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed " + mnem), pr);
            b.getInputMap(b.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + mnem),rl);
            b.getActionMap().put(pr, new PlayAction(ja,b,true));
            b.getActionMap().put(rl,new PlayAction(ja,b,false));
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

                b.setForeground(Color.BLACK);
                panel.add(b, 1, -1);
                numBlack++;
            }
        }

        //frame.pack();
        frame.setSize(14*width,height);
        frame.setVisible(true);
    }

    public void playNote(int pitch, JButton b,boolean pl)
    {
        if (pl)
        {
            channel.noteOn(pitch, 64);
            b.setBackground(Color.RED);
        }
        else
        {
            channel.noteOff(pitch, 64);
            b.setBackground(Color.WHITE);

        }
    }


    public void actionPerformed(ActionEvent e) {
        System.out.println("Action for first button/menu item" + e);
    }

    public void keyPressed(KeyEvent e)
    {
        System.out.println("got " + e);
    }

    public void keyReleased(KeyEvent e)
    {
        System.out.println("got " + e);
    }

    public void keyTyped(KeyEvent e)
    {
        System.out.println("got " + e);
    }
}