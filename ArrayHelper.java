import java.util.Arrays;
/**
 * Write a description of class ArrayHelper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayHelper
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class ArrayHelper
     */
    public ArrayHelper()
    {
        // initialise instance variables
        x = 0;
    }

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
    
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
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

    public static int[] reverse(int[] list)
    {
        int[] result = new int[]{};

        
        for(int i = list.length-1; i >= 0; i--)
        {
            result = ArrayHelper.addX(result,list[i]);

        }

        return result;
    }

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

    public static boolean[] removeOne(boolean[] list, int index)
    {
        if (list.length == 1)
        {
            return new boolean[]{};
        }

        // create a new array of size n+1
        boolean[] newarr = new boolean[list.length-1];

        for (int i = 0; i < index; i++)
            newarr[i] = list[i];

        for (int i = index + 1; i < index; i++) //add all old items to new array other than [index]
            newarr[i] = list[i];

        return newarr;
    }

    public static boolean[] getGroupOf(boolean tog, int num)
    {
        boolean[] result = new boolean[num];
        for(int i = 0; i < num; i++)
        {
            result[i] = tog;
        }
        return result;
    }
    
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
