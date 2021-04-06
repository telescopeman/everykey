import java.util.Arrays;
/**
 * A collection of useful Array methods.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 */
public abstract class ArrayHelper
{
    

    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static String[] addX(String[] list, String x)
    {
        int i;

        // create a new array of size n+1
        String[] newarr = new String[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }

    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static int[] addX(int[] list, int x)
    {
        int i;

        // create a new array of size n+1
        int[] newarr = new int[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }
    
    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static Integer[] addX(Integer[] list, Integer x)
    {
        int i;

        // create a new array of size n+1
        Integer[] newarr = new Integer[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = new Integer(list[i]);

        newarr[list.length] = x;

        return newarr;
    }
    
    /**
     * Checks to see if an array contains a certain item.
     */
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
    /**
     * Filters an array of Strings for the items containing certain terms.
     * @param mod If set to False, the method only filters for items that don't contain the term.
     */
    public static String[] getFiltered(String[] list, String term, boolean mod)
    {
        String[] result = new String[]{};
        for(String item : list)
        {
            if ((item.indexOf(term) > -1) == mod)
            {
                result = addX(result,item);
                
            }
            
        }
        return result;
        
    }

    
    /**
     * Returns an array equal to the reverse of the passed one.
     */
    public static int[] reverse(int[] list)
    {
        int[] result = new int[]{};
        for(int i = list.length-1; i >= 0; i--)
        {
            result = addX(result,list[i]);

        }

        return result;
    }

    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static boolean[] addX(boolean[] list, boolean x)
    {
        int i;

        // create a new array of size n+1
        boolean[] newarr = new boolean[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }

    /**
     * Creates an Array equal to the array passed as parameter minus the item at a specified index.
     */
    public static boolean[] removeOne(boolean[] list, int index)
    {
        if (list.length == 1)
        {
            return new boolean[]{};
        }

        // create a new array of size n-1
        boolean[] newarr = new boolean[list.length-1];

        for (int i = 0; i < index; i++)
            newarr[i] = list[i];

        for (int i = index + 1; i < index; i++) //add all old items to new array other than [index]
            newarr[i-1] = list[i];

        return newarr;
    }

    /**
     * Creates an Array with a certain number of a certain type of item.
     * To be honest, not sure what I used this for.
     * @param tog What kinds of booleans should be in the array.
     * @param num How many items the array should contain.
     */
    public static boolean[] getGroupOf(boolean tog, int num)
    {
        boolean[] result = new boolean[num];
        for(int i = 0; i < num; i++)
        {
            result[i] = tog;
        }
        return result;
    }
    
    /**
     * Creates an Array equal to the array passed as parameter minus the item at a specified index.
     */
    public static Filter[] removeOne(Filter[] list, int index)
    {
        if (list.length == 1)
        {
            return new Filter[]{};
        }

        // create a new array of size n+1
        Filter[] newarr = new Filter[list.length-1];

        for (int i = 0; i < index; i++)
            newarr[i] = list[i];

        for (int i = index + 1; i < list.length; i++) //add all old items to new array other than [index]
            newarr[i-1] = list[i];

        return newarr;
    }

    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static Filter[] addX(Filter[] list, Filter x)
    {
        int i;

        // create a new array of size n+1
        Filter[] newarr = new Filter[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }
}
