import javax.swing.*;
import java.awt.*; 
import javax.swing.BoxLayout;


/**
 * Write a description of class UIStuff here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UIStuff
{
    // instance variables - replace the example below with your own
    int[][] masterList;
    JFrame mainWindow;

    static boolean debugMode = true;

    JPanel inner;
    JScrollPane outer;
    Filter[] defaultFilters = new Filter[]{new Filter(8,4),new Filter(new int[]{4,5},2)}; //perfect fifth to the root
    JMenu viewfilters;

    Filter[] curFilters = defaultFilters;

    MathHelper myUtility;

    
    int[][] curList;

    /**
     * Constructor for objects of class UIStuff
     */
    public UIStuff()
    {
        myUtility = new MathHelper();
        masterList = myUtility.getAllKeys();
        irrelevantSetup();
        curList = filterKeys(masterList, curFilters);
        updateFilterList();
        updateKeys(curList);

    }
    private static void printlnDebug(String str)
    {
        if (debugMode)
        {
            System.out.println(str);

        }
    }

    private void updateKeys(int[][] keys)
    {
        inner.removeAll();
        int[] lastKey = keys[6];
        int counter = 0;
        for (int[] key : keys)
        {
            counter++;
            if (lastKey ==key)
            {

                //continue;
            }
            if (key[0] == 0)
            {
                //System.out.println("blanked");
                //System.out.println(MathHelper.expand(key));
                continue;
            }
            else
            {
                //System.out.println(String.valueOf(counter) + ": " + MathHelper.expand(key));

                String name = String.valueOf(counter) + ": " + getKeyName(key);

                JButton jb1 = new JButton("Chords");    
                ChordViewer chrds = new ChordViewer();
                chrds.key = key;
                jb1.addActionListener(chrds);
                
                          
                        

                        JButton jb2 = new JButton("Intervals");
                        JButton jb3 = new JButton("Button 3");      
                        JButton jb4 = new JButton("Button 4");
                        JButton jb5 = new JButton("Button 5");

                        JLabel label = new JLabel(name);
                        //System.out.println(key.toString());
                        JPanel keyPanel = new JPanel();

                        keyPanel.add(label);
                        keyPanel.add(jb1); keyPanel.add(jb2); 

                        inner.add(keyPanel); 
                        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
                    }
            }
            //outer.setLayout(new ScrollPaneLayout());

            mainWindow.pack();
            mainWindow.setSize(new Dimension(600, 1000));
            //outer.setPreferredSize(new Dimension(640,1000));
        }

        private String getKeyName(int[] key)
        {
        String name =TheoryHelper.getKeyName(key);
        if (name == "")
        {
            return "Unnamed Key (" + MathHelper.expand(key) + ")";
        }
        else
        {
            return name + " (" + MathHelper.expand(key) + ")";
        }

        
    }

    private void irrelevantSetup()
    {
        mainWindow = new JFrame("SkeletonKey");
        //myWindow.pack();
        mainWindow.setSize(new Dimension(600, 1000));
        //myWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inner = new JPanel();
        outer = new JScrollPane(inner);

        mainWindow.add(outer);

        JMenu filtermenu, addfilter; 
        JMenuItem i1, i2, i3, i4, i5;  
        JFrame f= new JFrame("Menu and MenuItem Example");  
        JMenuBar mb=new JMenuBar();  
        filtermenu=new JMenu("Filter");  
        viewfilters=new JMenu("View Active Filters");  
        addfilter=new JMenu("Add New Filter"); 
        i1=new JMenuItem("Filter by Tonality");  
        i2=new JMenuItem("Filter by Interval");  
        i3=new JMenuItem("Filter by Chord");  
        i4=new JMenuItem("Filter by something else idk");  
        i5=new JMenuItem("deez nuts");  


        filtermenu.add(viewfilters); filtermenu.add(addfilter); 
        addfilter.add(i1); addfilter.add(i2); addfilter.add(i3);  
        mb.add(filtermenu);  
        mainWindow.setJMenuBar(mb);  

        mainWindow.show();
    }

    private void updateFilterList()
    {
        viewfilters.removeAll();
        for (Filter f : curFilters)
        {
            String label = f.translateToReadable();
            viewfilters.add(new JMenuItem(label));  
        }
    }

    private static int[][] filterKeys(int[][] keyList, Filter[] filterList)
    {

        int[][] newList = keyList;
        int num = 0;
        for (int[] key : keyList)
        {
            boolean valid = true;
            for (Filter f : filterList)
            {

                if (!f.checkKey(key))
                {
                    System.out.println("Failed filter test" + num);
                    valid = false;
                    break;

                }

            }
            if (!valid)
            {
                newList[num] = new int[]{0};
            }
            num++;
        }
        return newList;
    }
}
