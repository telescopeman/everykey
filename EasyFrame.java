import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
/**
 * Inherited class that has a few methods I like to have handy in frames.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class EasyFrame extends JFrame
{
    // instance variables - replace the example below with your own
    private int x;

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
    
    public void add(String str)
    {
        add(new JLabel(str));
        
    }
    
    public void clear()
    {
        // put your code here
        getContentPane().removeAll();
    }
}
