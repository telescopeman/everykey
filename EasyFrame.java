import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
/**
 * Inherited class that has a few methods I like to have handy in frames.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class EasyFrame extends JFrame implements EasyVisual
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
    
    
    public void clear()
    {
        // put your code here
        getContentPane().removeAll();
    }
}
