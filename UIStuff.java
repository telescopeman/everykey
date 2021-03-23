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
    Filter[] defaultFilters = new Filter[]{new Filter(1,7)}; //perfect fifth to the root
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
        //printlnDebug(masterList);
        //masterList = myUtility.getAllKeys();
        //myUtility.sanityCheck();
        irrelevantSetup();
        // Scrollbar s=new Scrollbar();  
        // s.setBounds(100,100, 50,100);   
        // inner.add(s);  
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
                
                String name = getKeyName(key);
                
                
                JButton jb1 = new JButton("Chords");      
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
                keyPanel.show();
            }
        }
        
        inner.show();
        mainWindow.pack();
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
        inner.setLayout(new BoxLayout(inner, BoxLayout.PAGE_AXIS));
        mainWindow.add(inner);

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
                    //System.out.println("Failed filter test" + num);
                    valid = false;
                    
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
