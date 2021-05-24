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
        String[] new_array = new String[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            new_array[i] = list[i];

        new_array[list.length] = x;

        return new_array;
    }

    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static int[] addX(int[] list, int x)
    {
        int i;

        // create a new array of size n+1
        int[] new_array = new int[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            new_array[i] = list[i];

        new_array[list.length] = x;

        return new_array;
    }
    
    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static Integer[] addX(Integer[] list, Integer x)
    {
        int i;

        // create a new array of size n+1
        Integer[] new_array = new Integer[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            new_array[i] = list[i];

        new_array[list.length] = x;

        return new_array;
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
            if ((item.contains(term)) == mod)
            {
                result = addX(result,item);
                
            }
            
        }
        return result;
        
    }

    public static int search(String[] list, String term)
    {
        int counter = 0;
        for(String item : list)
        {
            if ((item.equals(term)))
            {
                return counter;
                
            }
            counter++;
        }
        return -1;
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
        boolean[] new_array = new boolean[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            new_array[i] = list[i];

        new_array[list.length] = x;

        return new_array;
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
        boolean[] new_array = new boolean[list.length-1];

        if (index >= 0) System.arraycopy(list, 0, new_array, 0, index);

        //add all old items to new array other than [index]
        if (index - (index + 1) >= 0) System.arraycopy(list, index + 1, new_array, index + 1 - 1, index - (index + 1));

        return new_array;
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
        Filter[] new_array = new Filter[list.length-1];

        if (index >= 0) System.arraycopy(list, 0, new_array, 0, index);

        //add all old items to new array other than [index]
        if (list.length - (index + 1) >= 0)
            System.arraycopy(list, index + 1, new_array, index + 1 - 1, list.length - (index + 1));

        return new_array;
    }

    /**
     * Creates an Array equal to the array passed as parameter plus a new item.
     */
    public static Filter[] addX(Filter[] list, Filter x)
    {
        int i;

        // create a new array of size n+1
        Filter[] new_array = new Filter[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            new_array[i] = list[i];

        new_array[list.length] = x;

        return new_array;
    }
}
