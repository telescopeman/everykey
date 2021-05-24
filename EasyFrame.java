import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Inherited class that has a few methods I like to have handy in frames.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class EasyFrame extends JFrame
{
    // size presets
    public static final Dimension STANDARD = new Dimension(350,100);
    public static final Dimension SUPER_STANDARD = new Dimension(550,150);
    public static final Dimension SMALL = new Dimension(200,200);
    public static final Dimension ROUNDISH = new Dimension(350,250);
    public static final Dimension LONG = new Dimension(900,250);
    public static final Dimension LONG2 = new Dimension(900,150);
    public static final Dimension MAIN = new Dimension(850,1000);


    /**
     * Constructor for objects of class EasyFrame
     */
    public EasyFrame() {}

    public void addHeader(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
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
        ActionItem jb3 = new ActionItem(trig,name);
        add(jb3);
    }

    public void repaint()
    {
        super.repaint();

        //System.out.println("Fbrr");

    }


    /**
     * Generates a Dimension with the given size.
     */
    public Dimension getDim(int x, int y)
    {
        return new Dimension(x,y);
    }
    
    public void setWidth(int x)
    {
         setSize(new Dimension(x,getSize().height));
    }

    public void setHeight(int x)
    {
        //noinspection SuspiciousNameCombination
        setSize(new Dimension(getSize().width,x));
    }

    public void setGrid(int x, int y)
    {
        setLayout(new GridLayout(x,y));
    }

    public void appear()
    {
        setVisible(true);
        requestFocusInWindow();
        UIStuff.adjustColors(this);

    }



    public void appear(Dimension dim)
    {
        appear();
        setSize(dim);
    }


    /**
     * Adds a label with the specified String.
     */
    public void add(String str)
    {
        add(new JLabel(str));

    }

    public void clear()
    {
        getContentPane().removeAll();
    }


}
