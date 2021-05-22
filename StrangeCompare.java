import java.util.Comparator;
/**
 * Compares two scale indexes by their distances to a given neutral point.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public class StrangeCompare implements Comparator<Integer>
{
    private final int neutral_point;
    
    /**
     * Constructs a comparator that compares keys based on their distance to Dorian.
     */
    public StrangeCompare()
    {
        neutral_point = 300; // 300 = Dorian
    }
    
    /**
     * Constructs a comparator that compares keys based on their distance to a chosen key index.
     */
    public StrangeCompare(int pt)
    {
        neutral_point = pt;
    }

    /**
     * Compares two key indexes.
     */
    public int compare(Integer o1, Integer o2)
    {
        return getStrangeness(o1) - getStrangeness(o2); 
    }

    private int getStrangeness(Integer i)
    {
        return Math.abs(i - neutral_point);
    }
}
