import javax.swing.*;
import java.awt.*; 
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
    
    
    public EasyFrame(String name)
    {
        // initialise instance variables
        setTitle(name);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void clear()
    {
        // put your code here
        getContentPane().removeAll();
    }
}
