/**
 * Compares two scale indexes by their distances to a given neutral point.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 */
public class StrangeCompare extends LayeredComparator<Integer>
{
    private final int neutral_point;
    /**
     * Constructs a comparator that compares keys based on their distance to Dorian.
     */
    public StrangeCompare()
    {
        super(new NameComparator());
        neutral_point = 300; // 300 = Dorian
    }
    
    /**
     * Constructs a comparator that compares keys based on their distance to a chosen key index.
     */
    public StrangeCompare(int pt)
    {
        super(new NameComparator());
        neutral_point = pt;
    }

    protected int getValue(Integer i)
    {
        return Math.abs(i - neutral_point);
    }
}
