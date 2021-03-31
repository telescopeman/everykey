import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.GridLayout; 
/**
 * Provides a view of the chords of a scale.
 *
 * @author Caleb Copeland, User1752197 on StackOverflow [convertToRoman() method only]
 * @version 3/31/21
 */
public class ChordViewer extends EasyFrame
{
    // instance variables - replace the example below with your own
    private int[] myKey;



    /**
     * Creates a ChordViewer of a specified scale with a specified name.
     */
    public ChordViewer(int[] k, String name)
    {

        super("Chords of " + KeyPanel.parse(name));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 150);
        setResizable(false);
        setLayout(new GridLayout(3, 7));
        myKey= k;

    }

    private static MyChord getChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        System.out.print(ind + "-->");
        if (index > LOOP)
        {
            return(new MyChord(0,0,0));
        }

        //return "Test Chord";
        //System.out.println(String.valueOf(index) + String.valueOf((index + 2) % LOOP) + String.valueOf((index + 4) % LOOP) + "-->" + key[index] + key[(index + 2) % LOOP] + key[(index + 4) % LOOP]);
        return new MyChord(key[index], key[((index + 2) % 7)],key[((index + 4) % 7)] );

    }

    private static int[] makeAscending(int[] sequence)
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
        return seq;
    }

    
    
    
    /**
     * Converts a whole number to its equivalent in Roman numerals.
     * 
     * @param input The number to be converted.
     * @since October 19, 2012
     * @returns The Roman numeral in text.
     */
    public static String convertToRoman(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }    
        return s;
    }

    public void actionPerformed(ActionEvent e) {

        //System.out.print(e);
        clear();

        
        for(int i = 1; i < 8; i++) //names of chords
        {
            MyChord aChord = getChordAt(myKey,i);
            JLabel jLabel1 = new JLabel(convertToRoman(i) + ": " + aChord.toString(), JLabel.CENTER);
            jLabel1.setOpaque(true);
            //String third = aChord.getThirdType();
            jLabel1.setBackground(aChord.toColor());
            add(jLabel1);

        }

        for(int i = 1; i < 8; i++) // note names
        {
            MyChord aChord = getChordAt(myKey,i);
            //int[] noteSequence2 = makeAscending(noteSequence);

            add(new JLabel("(" + aChord.getNotes() + ")", JLabel.CENTER));
        }

        for (int i = 1; i < 8; i++) //listen button
        {
            //MyChord aChord = getChordAt(myKey,i);
            int[] noteSequence = MyChord.getRawChordAt(myKey,i);
            int[] seq = makeAscending(noteSequence);
            System.out.println(seq);

            JButton jb3 = new JButton("Play Chord");    
            

            try{
                MusicHelper playr = new MusicHelper(seq);
                jb3.addActionListener(playr);
                
                class ButtonAction extends AbstractAction
                {
                    public ButtonAction(String text, String desc)
                    {
                        super(text);
                        putValue(SHORT_DESCRIPTION, desc);
                    }

                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        System.out.println(ae.getActionCommand());
                        playr.actionPerformed(ae);
                    }
                }
                
                Action buttonAction = new ButtonAction("Play Chord"
                , "Play Chord");

                jb3.getInputMap(jb3.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i).charAt(0)), "Play Chord");
                jb3.getActionMap().put("Play Chord", buttonAction);

                
            }
            catch(Exception ed){
                throw ed;
            }
            add(jb3);
        }
        show();
    }

}
