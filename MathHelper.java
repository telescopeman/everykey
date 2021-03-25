import java.util.HashMap;

/**
 * Write a description of class MainThing here.
 *
 * @author (your name)
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

    private static HashMap<Integer,String> noteNames;

    private final static boolean debugMode = false;
    
    private static EnharmonicsHelper enh = new EnharmonicsHelper();

    //private static String offset;
    // /**
    // * Constructor for objects of class MainThing
    // */
    // public MainThing()
    // {

    // }
    public static String[] notesArr = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","Null"};

        
    public static int[][] getAllKeys()
    {
        // if (counter > 3)
        // {
            // return abstractList;
        // }
        abstractList = new int[555][7];
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

    public static void sanityCheck()
    {
        for(int g = 0; g < abstractList.length;g++)
        {
            System.out.println("Key #" + g + " has pitches: " + expand(abstractList[g],false));
            
        }
        
    }
    
    private static void setKey(int[] k, int i)
    {
        abstractList[i] = k;

        printlnDebug("Key #" + i + " set to " + expand(abstractList[i],false));
        
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

    public static String expand(int[] key, boolean enharmonicsOn)
    {
        String name = "";
        int count = 0;
        String lastNote = "I";
        while (count < 7)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            if (enharmonicsOn && count < 6 && newNote.substring(0,1).equals(getNoteName(key[count+1]).substring(0,1)))
            {
                String sameNote = enh.getEnharmonic(newNote);
                if (!newNote.substring(0,1).equals(lastNote.substring(0,1)))
                {
                    newNote = sameNote;
                    
                }
                
            }
            name = name + newNote;
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
            lastNote = newNote;
            count++;

        }
        return name;
    }

    
}
