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
    private static int[] scale_indices;

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

    private static int[] sortByStrangeness()
    {
        int[] result = new int[scale_indices.length];
        Integer[] temp = new Integer[]{};
        for(int c : scale_indices)
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

    /**
     * Unfinished
     * @return
     */
    private static int[] sortByIntervals()
    {
        int[] result = new int[scale_indices.length];
        Integer[] temp = new Integer[]{};
        for(int c : scale_indices)
        {
            temp = ArrayHelper.addX(temp,c);
        }
        Arrays.sort(temp, new IntervalCompare());
        int i = 0;

        for(int d : temp)
        {
            result[i] = d;
            i++;
        }
        return result;
    }

    /**
     * Sorts the scales by the chosen sorting style.
     */
    public static int[] styleSort()
    {
        int[] result = new int[scale_indices.length];

        switch(currentSortStyle)
        {
            case Brightness_Ascending:
            {
                return scale_indices;
            }
            case Brightness_Descending:
            {
                return ArrayHelper.reverse(scale_indices);
            }
            case Strangeness_Ascending:
            {
                return sortByStrangeness();
            }
            case Strangeness_Descending: //descending "strangeness"
            {
                return ArrayHelper.reverse(sortByStrangeness());
            }
            case Intervalic_Oddities_Ascending:
            {
                return sortByIntervals();
            }
            case Intervalic_Oddities_Descending:
            {
                return ArrayHelper.reverse(sortByIntervals());
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

        Control.scale_indices = new int[]{};
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
                scale_indices = ArrayHelper.addX(scale_indices,num);
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