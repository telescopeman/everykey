import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 
/**
 * Write a description of class ChordViewer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChordViewer extends EasyFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    public int[] myKey;

    /**
     * Constructor for objects of class ChordViewer
     */
    public ChordViewer(int[] k, String name)
    {
        setSize(new Dimension(900, 150));
        setResizable(false);
        setLayout(new GridLayout(3, 7));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myKey= k;
        

        
    }

    public static Chord getChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        System.out.print(ind + "-->");
        if (index > LOOP)
        {
            return(new Chord(0,0,0));
        }

        //return "Test Chord";
        //System.out.println(String.valueOf(index) + String.valueOf((index + 2) % LOOP) + String.valueOf((index + 4) % LOOP) + "-->" + key[index] + key[(index + 2) % LOOP] + key[(index + 4) % LOOP]);
        return new Chord(key[index], key[((index + 2) % 7)],key[((index + 4) % 7)] );

    }
    
    public static int[] getRawChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        System.out.print(ind + "-->");
        if (index > LOOP)
        {
            return new int[]{};
        }

        //return "Test Chord";
        //System.out.println(String.valueOf(index) + String.valueOf((index + 2) % LOOP) + String.valueOf((index + 4) % LOOP) + "-->" + key[index] + key[(index + 2) % LOOP] + key[(index + 4) % LOOP]);
        return new int[]{key[index], key[(index + 2) % 7],key[(index + 4) % 7]};

    }
    
    public static String formatAscending(int[] sequence)
    {
        int[] seq = sequence;
        int[] ups = new int[]{0,0,0};
        if (seq[0] > seq[1])
        {
            seq[1] += 12;
            ups[1]++;
        }
        if (seq[0] > seq[2])
        {
            seq[2] += 12;
            ups[2]++;
        }
        if (seq[1] > seq[2])
        {
            seq[2] += 12;
            ups[2]++;
        }
        String str = "(";
        for(int i = 0; i < 3; i++)
        {
            str += TheoryObj.getNoteName(seq[i]);
            str += String.valueOf(5 + ups[i]);
            if (i < 2)
            {
                str += "+";
            }
        }
        str += ")w";
        System.out.println("CHORD=" + str);
        return str;
        
        
    }

    // public String format(int[] seq)
    // {
        // return 

    // }
    
    
    
    public void actionPerformed(ActionEvent e) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.print(e);
        clear();
        
        // for(int i = 1; i < 8; i++)
        // {
            // add(new JLabel(String.valueOf(i), JLabel.CENTER));
        // }
        for(int i = 1; i < 8; i++)
        {
            Chord aChord = getChordAt(myKey,i);
            JLabel jLabel1 = new JLabel(RomanHelper.convert(i) + ": " + aChord.toString(), JLabel.CENTER);
            jLabel1.setOpaque(true);
            //String third = aChord.getThirdType();
            jLabel1.setBackground(aChord.toColor());
            add(jLabel1);
            
        }
        
        for(int i = 1; i < 8; i++)
        {
            Chord aChord = getChordAt(myKey,i);
            //int[] noteSequence2 = makeAscending(noteSequence);

            add(new JLabel("(" + aChord.getNotes() + ")", JLabel.CENTER));
        }
        
        for (int i = 1; i < 8; i++)
        
        {
            int[] noteSequence = getRawChordAt(myKey,i);
            String seq = formatAscending(noteSequence);
            
            JButton jb3 = new JButton("Listen");    
            MusicPlayer playr = new MusicPlayer(seq);
            jb3.addActionListener(playr);
            add(jb3);
            
        }
        show();
    }
    
    
}
