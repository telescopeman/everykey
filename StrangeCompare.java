import java.util.Comparator;
/**
 * Compares two scale indexes by their distances to Dorian.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class StrangeCompare implements Comparator<Integer>
{
    // instance variables - replace the example below with your own
    private int x;

    final int DORIAN = 300;
    
    /**
     * Constructor for objects of class StrangeCompare
     */
    public StrangeCompare()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int compare(Integer o1, Integer o2)
    {
        return getStrangeness(o1) - getStrangeness(o2);
        
        
    }

    private int getStrangeness(Integer i)
    {
        return Math.abs(i-DORIAN);
        
    }
}
