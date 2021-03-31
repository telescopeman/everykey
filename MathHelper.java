import java.util.HashMap;

/**
 * Handles creation of the huge list of scales,
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class MathHelper extends TheoryObj
{
    // instance variables - replace the example below with your own
    private static int[][] abstractList;
    
    private static int[][] trysomething;
    
    //private static int[] lastKey;
    private static final int[] initKey = new int[]{1,1,1,1,1,1,1};
    private static int counter;

    //private static HashMap<Integer,String> noteNames;

    private final static boolean debugMode = false;
    
    

    
    public static String[] notesArr = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","Null"};

    /**
     * Generates the list of all possible seven-note scales.
     */
    public static int[][] getAllKeys()
    {
        // if (counter > 3)
        // {
            // return abstractList;
        // }
        abstractList = new int[462][7];
        //trysomething = new int[210][7];
        // initialise instance variables
        counter = 0;

        int n = 0;
        //lastKey = new int[]{0,0,0,0,0,0,0}; //creates prime key

        //sanityCheck();
        
        makeKey(initKey,1);
        
        //sanityCheck();
        
        return abstractList;

    }

    private static void printlnDebug(String str)
    {
        if (debugMode)
        {
            System.out.println(str);

        }
    }

    
    
    private static void setKey(int[] k, int i)
    {
        abstractList[i] = k;

        //printlnDebug("Key #" + i + " set to " + expand(abstractList[i],false));
        
    }

      
    private static void makeKey(int[] curArr, int index)
    {
        if (index == 7)
        {
            setKey(curArr,counter);
            counter++;
        }
        else
        {
            
            int lastNote = curArr[index-1];
            

            for(int branch = lastNote + 1; branch < 7 + index;branch++)
            {
                 int[] passAlong = new int[curArr.length];
 
        // Copy elements of a[] to b[]
                for (int i = 0; i < passAlong.length; i++)
                    passAlong[i] = curArr[i];
                //int[] passAlong = curArr;
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

    

    
}
