import javax.swing.*;
import java.awt.*;

/**
 * Samples random notes and all chords from a scale to give a feel for its sound.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 */
public class Sampler extends CrabFrame
{
    private String name;
    public MusicPlayer mus;
    public static final String PLAY_TEXT = "Start";

    class TimeSignatureEditor extends SpinnerEditor
    {
        private final int index;

        public TimeSignatureEditor(int index) {
            super("",
                    new SpinnerNumberModel(
                            mus.getTimeSignature(index),
                            1,null,1),
                    3,true);
            this.index = index;
        }

        @Override
        protected int getCurrentValue() {
            return (int) model.getValue();
        }

        @Override
        public void apply(int curValue) {
            mus.changeTimeSignature(curValue,index);
            System.out.println(curValue);
        }
    }

    /**
     * Plays random notes.
     */
    public Sampler(int[] scale, String n)
    {
        super(n,SUPER_STANDARD);
        setGrid(2,2);
        setTitle("Random Sampler");
        int ind = n.indexOf('(');

        mus = new MusicPlayer(scale,this);


        if (ind > -1)
        {
            name =n.substring(0, ind);

        }
        else
        {
            name = n;
        }
        ind = n.indexOf('{');
        if (ind > -1)
        {
            name = n.substring(0, ind);

        }
        else
        {
            name = n;
        }



    }

    public void act(String s)
    {
        System.out.println(s);
        clear();
        addHeader(name + " - Sampler");
        //JPanel p = new JPanel(new GridLayout());
        //p.add(new JLabel("Time:"));
        //p.add(new TimeSignatureEditor(0));
        //EasyLabel label = new EasyLabel("/");
        //label.setVerticalAlignment(SwingConstants.CENTER);
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setFontSize(20);
        //p.add(label);
        //p.add(new TimeSignatureEditor(1));
        //add(p);

        addButton(PLAY_TEXT,mus);


        appear();
    }
}
