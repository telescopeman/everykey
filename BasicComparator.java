import java.util.Comparator;

public abstract class BasicComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2)
    {
        return getValue(o1) - getValue(o2);
    }

    protected abstract int getValue(T o1);
}
