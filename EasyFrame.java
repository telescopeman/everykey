import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Inherited class that has a few methods I like to have handy in frames.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class EasyFrame extends JFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    public final Dimension STANDARD = new Dimension(350,100);
    public final Dimension SUPERSTANDARD = new Dimension(550,150);
    public final Dimension ROUNDISH = new Dimension(350,250);
    public final Dimension LONG = new Dimension(900,250);

    /**
     * Constructor for objects of class EasyFrame
     */
    public EasyFrame()
    {
        // initialise instance variables

    }

    public void addHeader(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);

    }

    public EasyFrame(String name)
    {
        // initialise instance variables
        setTitle(name);
    }

    public void addButton(String name, ActionListener trig)
    {
        JButton jb3 = new JButton(name);    
        jb3.addActionListener(trig);
        add(jb3);
    }

    /**
     * Generates a Dimension with the given size.
     */
    public Dimension getDim(int x, int y)
    {
        return new Dimension(x,y);

    }

    // public GridLayout getGrid(int x, int y)
    // {
    // return new GridLayout(x,y);
    // }

    public void setGrid(int x, int y)
    {
        setLayout(new GridLayout(x,y));
    }

    public void appear()
    {
        show();
        requestFocusInWindow();
    }

    public void appear(Dimension dim)
    {
        show();
        requestFocusInWindow();
        setSize(dim);
    }

    
    public int[] getEnclosers(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return new int[]{-1,-1};

        }
        int ind1 = str.indexOf(special.substring(0,1));
        return new int[]{ind1,str.indexOf(special.substring(1,2),ind1)};

    }

    // /**
    // * Sets the size of the frame.
    // */
    // public void setSize(int x, int y)
    // {
    // setSize(new Dimension(x,y));    
    // }

    public void add(String str)
    {
        add(new JLabel(str));

    }

    public void clear()
    {
        // put your code here
        getContentPane().removeAll();
    }

    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    public void act(String id)
    {

    }
}
