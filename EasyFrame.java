import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * Inherited class that has a few methods I like to have handy in frames.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 */
public class EasyFrame extends JFrame
{
    // size presets
    public static final Dimension STANDARD = new Dimension(350,100);
    public static final Dimension SUPER_STANDARD = new Dimension(550,150);
    public static final Dimension SMALL = new Dimension(200,200);
    public static final Dimension ROUNDISH = new Dimension(350,250);
    public static final Dimension LONG = new Dimension(900,250);
    public static final Dimension LONG_AND_THIN = new Dimension(900,150);
    public static final Dimension MAIN_WINDOW = new Dimension(850,1000);


    /**
     * Constructor for objects of class EasyFrame
     */
    public EasyFrame() {}

    public EasyFrame(String name, Dimension dimension)
    {
        setTitle(name);
        setSize(dimension);

    }

    public EasyFrame(Dimension dimension) {
        setSize(dimension);
    }

    public void addHeader(String text)
    {
        EasyLabel title = new EasyLabel(text,JLabel.CENTER);
        title.embolden();
        add(title);

    }
    
    public void addCenteredText(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        add(title);
    }

    public EasyFrame(String name)
    {
        setTitle(name);
    }

    public void addButton(String name, ActionListener trig)
    {
        EasyButton jb3 = new EasyButton(name,CENTER_ALIGNMENT);
        jb3.addActionListener(trig);
        add(jb3);
    }


    public void setGrid(int x, int y)
    {
        setLayout(new GridLayout(x,y));
    }

    public void appear()
    {
        setVisible(true);
        requestFocusInWindow();
        UI.adjustColors(this);
    }


    public void clear()
    {
        getContentPane().removeAll();
    }


}
