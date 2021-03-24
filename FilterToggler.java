import javax.swing.*;
import java.awt.*; 
/**
 * Write a description of class FilterToggler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FilterToggler extends JMenuItem
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class FilterToggler
     */
    public FilterToggler(int index)
    {
        x = index;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getIndex()
    {
        // put your code here
        return x;
    }
}
