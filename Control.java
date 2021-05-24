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


    private static final boolean debugMode = false;

    private static int neutral_point = 300;

    private static SortOption currentSortStyle = SortOption.Strangeness_Ascending;

    public static int[] absoluteList;


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
    public static int getNeutral()
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

    public static int[] styleSort()
    {
        int[] result = new int[absoluteList.length];

        switch(currentSortStyle)
        {
            case Brightness_Ascending:
            {
                return absoluteList;
            }
            case Brightness_Descending:
            {
                return ArrayHelper.reverse(absoluteList);
            }
            case Strangeness_Ascending:
            {
                Integer[] temp = new Integer[]{};
                for(int c : absoluteList)
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
                for(int c : absoluteList)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare());
                int i = 0;
                //result = list
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


    public static int[][] filterKeys(int[][] keyList, Filter[] filterList)
    {

        int[][] newList = Arrays.copyOf(keyList,keyList.length);
        int num = 0;

        Control.absoluteList = new int[]{};
        for (int[] key : keyList)
        {
            boolean valid = true;
            int num2 = -1;
            for (Filter f : filterList)
            {
                num2++;

                if (!FilterBank.getFilterStatuses()[num2])
                {
                    valid = true;
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
                Control.absoluteList = ArrayHelper.addX(Control.absoluteList,num);
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