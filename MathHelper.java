import java.util.HashMap;

/**
 * Write a description of class MainThing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public  class MathHelper
{
    // instance variables - replace the example below with your own
    public static int[][] abstractList;
    //private static int[] lastKey;
    private static final int[] initKey = new int[]{1,0,0,0,0,0,0};
    private static int counter = 1;

    private static HashMap<Integer,String> noteNames;

    private static boolean debugMode = false;

    //private static String offset;
    // /**
    // * Constructor for objects of class MainThing
    // */
    // public MainThing()
    // {

    // }
    private static String[] notesArr = new String[]{"Null","C","Db","D","Eb","F","Gb","G","Ab","A","Bb","B","Null"};

        
    public static int[][] getAllKeys()
    {
        if (counter > 1)
        {
            return abstractList;
        }
        abstractList = new int[250][7];
        // initialise instance variables
        counter = 1;

        int n = 0;
        //lastKey = new int[]{0,0,0,0,0,0,0}; //creates prime key

        makeKey(initKey,1);
        return abstractList;

    }

    private static void printlnDebug(String str)
    {
        if (debugMode)
        {
            System.out.println(str);

        }
    }

    

      
    private static void makeKey(int[] curArr, int index)
    {
        if (index == 7)
        {
            int ind2 = getEmpty();
            if (ind2 < 0)
            {
                //System.out.println("ERROR: getEmpty() check failed.");
                return;
            }
            abstractList[ind2] = curArr;

            printlnDebug("Key #" + counter + " successfully created with pitches: " + expand(curArr));
            counter++;
        }
        else
        {
            int lastNote = curArr[index-1];
            

            for(int branch = lastNote + 1; branch < 6 + index;branch++)
            {
                int[] passAlong = curArr;
                passAlong[index] = branch;
                //System.out.println(expand(passAlong));
                makeKey(passAlong,index+1);
            }
        }

    }

    private static int getEmpty()
    {
        int openSlot = -1;
        for(int i = 0; i < abstractList.length; i++) {
            if(abstractList[i][0] != 1)
            {
                openSlot = i;
                break;
            }
        }
        return openSlot;
    }

    public static String expand(int[] key)
    {
        String name = "";
        int count = 0;
        while (count < 7)
        {
            int i = key[count];
            name = name + TheoryHelper.getNoteName(i);
            if (count < 5)
            {
                name = name + ", ";
            }
            else if (count == 5)
            {
                name = name + ", and ";
            }
            else
            {
                name = name + ".";
            }
            count++;

        }
        return name;
    }

    // /**
    // * An example of a method - replace this comment with your own
    // *
    // * @param  y  a sample parameter for a method
    // * @return    the sum of x and y
    // */
    // public static int[] generateKey(int n)
    // {
    // int[] newKey;
    // if (n == 0)
    // {
    // newKey = new int[]{1,2,3,4,5,6,7};
    // }
    // else
    // {
    // newKey = sharpenKey(abstractList[n-1],1);
    // }
    // // put your code here
    // abstractList[n] = newKey;
    // return newKey; 
    // }

    // private static int[] sharpenKey(int[] key, int index)
    // {
    // int[] newKey = key;
    // int count = 1;

    // if (newKey[index] == 12)
    // {
    // return newKey;
    // }
    // else if (index == 6)
    // {
    // newKey[index]++;
    // return newKey;
    // }

    // if (newKey[index] + 1 == newKey[index + 1])
    // {
    // return sharpenKey(newKey, index+1);
    // }
    // else
    // {
    // newKey[index]++;
    // return newKey;
    // }

    // }
}
