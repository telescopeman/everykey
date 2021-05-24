import java.util.Comparator;

/**
 * @since 5/24/21
 */
public abstract class LayeredComparator<T> extends BasicComparator<T> implements Comparator<T> {

    final Comparator<T> comparator;

    public LayeredComparator(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2)
    {
        int result = super.compare(o1,o2);

        if (result == 0)
        {
            return comparator.compare(o1,o2);
        }
        return result;
    }




}
