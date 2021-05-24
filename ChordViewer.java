import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Provides a view of the chords of a scale.
 *
 * @author Caleb Copeland, User1752197 on StackOverflow [convertToRoman() method only]
 * @version 5/24/21
 */
public class ChordViewer extends ListeningFrame implements LowerBucketCrab
{
    private final int[] myKey;

    /**
     * Creates a ChordViewer of a specified scale with a specified name.
     */
    public ChordViewer(int[] k, String name)
    {

        super("Chords of " + StringHelper.filterOutTags(name), LONG_AND_THIN);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setGrid(3, 7);
        myKey= k;

    }

    private static Chord getChordAt(int[] key, int ind)
    {
        int index = ind - 1;
        final int LOOP = 7;
        if (index > LOOP)
        {
            return(new Chord(0,0,0));
        }
        return new Chord(key[index], key[((index + 2) % 7)],key[((index + 4) % 7)] );

    }

    private static int[] makeAscending(int[] sequence)
    {
        if (sequence[0] > sequence[1])
        {
            sequence[1] += 12;
        }
        if (sequence[0] > sequence[2])
        {
            sequence[2] += 12;
        }
        if (sequence[1] > sequence[2])
        {
            sequence[2] += 12;
        }
        return sequence;
    }

    /**
     * Converts a whole number to its equivalent in Roman numerals.
     * 
     * @param input The number to be converted.
     * @return The Roman numeral in text.
     */
    public static String convertToRoman(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        StringBuilder s = new StringBuilder();
        while (input >= 1000) {
            s.append("M");
            input -= 1000;        }
        while (input >= 900) {
            s.append("CM");
            input -= 900;
        }
        while (input >= 500) {
            s.append("D");
            input -= 500;
        }
        while (input >= 400) {
            s.append("CD");
            input -= 400;
        }
        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input >= 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input >= 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }    
        return s.toString();
    }

    protected void act(String id) {
        clear();

        for(int i = 1; i < 8; i++) //names of chords
        {
            Chord aChord = getChordAt(myKey,i);
            JLabel jLabel1 = new JLabel(convertToRoman(i) + ": " + aChord, JLabel.CENTER);
            jLabel1.setOpaque(true);
            jLabel1.setBackground(aChord.toColor());
            add(jLabel1);

        }

        for(int i = 1; i < 8; i++) // note names
        {
            Chord aChord = getChordAt(myKey,i);
            addCenteredText("(" + aChord.getNotes() + ")");
        }

        for (int i = 1; i < 8; i++) //listen button
        {
            int[] noteSequence = TheoryObj.getRawChordAt(myKey,i);
            int[] seq = makeAscending(noteSequence);

            String PLAY_TEXT = "Play Chord";
            EasyButton jb3 = new EasyButton(PLAY_TEXT, CENTER_ALIGNMENT);
            MusicPlayer musicPlayer = new MusicPlayer(seq,this);
            jb3.addActionListener(musicPlayer);

            class ButtonAction extends AbstractAction
            {
                public ButtonAction(String text)
                {
                    super(text);
                    putValue(SHORT_DESCRIPTION, text);
                }

                public void actionPerformed(ActionEvent ae)
                {
                    musicPlayer.actionPerformed(ae);
                }
            }

            ButtonAction buttonAction = new ButtonAction(PLAY_TEXT);

            // when the user types the number of the chord, it plays
            jb3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i).charAt(0)), PLAY_TEXT);
            jb3.getActionMap().put(PLAY_TEXT, buttonAction);

            add(jb3);
        }
        setVisible(true);
    }

    @Override
    protected void onClosed() {
        BucketCrabManager.pullDown(this);
    }
}
