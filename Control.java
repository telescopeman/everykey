import java.util.Arrays;

/**
 * Controls high-level functions.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 * @since 5/23/21
 */
public class Control
{
    private static int neutral_point = 300;

    private static SortOption currentSortStyle = SortOption.Strangeness_Ascending;

    // this is basically a list of the
    // scales indices in order
    private static int[] sortedIndices;

    /**
     * Runs the main program.
     */
    public static void main(String[] args)
    {
        KeyNamesHelper.initialize();
        UI.initialize();
        FilterBank.initialize();
        UI.oneTimeSetup();
        UI.refresh();
    }

    /**
     * Returns the index of the scale used for strangeness measurements.
     */
    public static int getNeutralPoint()
    {
        return neutral_point;
    }

    /**
     * Sets the index of the scale used for strangeness measurements.
     */
    public static void setNeutral(int i)
    {
        neutral_point = i;
    }

    /**
     * Sorts the scales by the chosen sorting style.
     */
    public static int[] styleSort()
    {
        int[] result = new int[sortedIndices.length];

        switch(currentSortStyle)
        {
            case Brightness_Ascending:
            {
                return sortedIndices;
            }
            case Brightness_Descending:
            {
                return ArrayHelper.reverse(sortedIndices);
            }
            case Strangeness_Ascending:
            {
                Integer[] temp = new Integer[]{};
                for(int c : sortedIndices)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare(neutral_point));
                int i = 0;
                //result = list
                for(int d : temp)
                {
                    result[i] = d;
                    i++;
                }
                return result;
            }
            case Strangeness_Descending: //descending "strangeness"
            {
                Integer[] temp = new Integer[]{};
                for(int c : sortedIndices)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare());
                int i = 0;

                for(int d : temp)
                {
                    result[i] = d;
                    i++;
                }
                return ArrayHelper.reverse(result);
            }
            default:
            {
                System.out.println("Invalid sort style!");
            }
        }
        return result;
    }


    /**
     * Changes the setting for sorting of scales.
     */
    public static void setSortStyle(SortOption newStyle)
    {
        currentSortStyle = newStyle;
        UI.refresh();
    }

    /**
     * Filters all the scales, and returns what's left.
     * @return The remaining scales that fit the filters.
     */
    public static int[][] filterKeys()
    {
        Filter[] filterList = FilterBank.getCurrentFilters();

        int[][] newList = Arrays.copyOf(UI.getMasterList(),UI.getMasterList().length);
        int num = 0;

        Control.sortedIndices = new int[]{};
        for (int[] key : UI.getMasterList())
        {
            boolean valid = true;
            int num2 = -1;
            for (Filter f : filterList)
            {
                num2++;

                if (!FilterBank.getFilterStatuses()[num2])
                {
                    // if the filter isn't turned on, skip it.
                    continue;
                }

                if (!f.checkKey(key,num))
                {
                    valid = false;
                    break;

                }

            }
            if (valid)
            {
                Control.sortedIndices = ArrayHelper.addX(Control.sortedIndices,num);
            }
            else
            {
                newList[num] = new int[]{0};
            }

            num++;
        }
        return newList;
    }


}