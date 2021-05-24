
public class NameComparator extends BasicComparator<Integer> {

    @Override
    protected int getValue(Integer o1) {
        int[] key = UI.getCurrentList()[o1];
        int ind = SpeedCache.getOffset();
        if (!KeyNamesHelper.smartGet(key,ind).equals(""))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
