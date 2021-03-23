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
    
    JPanel inner;
    Filter[] defaultFilters = new Filter[]{new Filter(1,7)}; //perfect fifth to the root
    JMenu viewfilters;
    
    Filter[] curFilters = defaultFilters;

    /**
     * Constructor for objects of class UIStuff
     */
    public UIStuff()
    {
        masterList = MathHelper.getAllKeys();
        irrelevantSetup();
        int[][] curList = filterKeys(masterList, defaultFilters);
        
        updateFilterList();
        updateKeys(curList);
        
    }

    private void updateKeys(int[][] curList)
    {
        inner.removeAll();
        for (int[] key : curList)
        {
            if (key[0] == 0)
            {
                //System.out.println("blanked");
                continue;
            }
            else
            {
                //System.out.println("unblanked");
                String name = MathHelper.expand(key);
                
                JButton jb1 = new JButton("Chords");      
                JButton jb2 = new JButton("Intervals");
                JButton jb3 = new JButton("Button 3");      
                JButton jb4 = new JButton("Button 4");
                JButton jb5 = new JButton("Button 5");
                
                JLabel label = new JLabel(name);
                JPanel keyPanel = new JPanel();
                //keyPanel.setLayout(new GridLayout(3, 1));
                keyPanel.add(label);
                keyPanel.add(jb1); keyPanel.add(jb2); 
                
                inner.add(keyPanel); 
                keyPanel.show();
            }
        }
        
        inner.show();
        mainWindow.pack();
    }
    
    
    private void irrelevantSetup()
    {
        mainWindow = new JFrame("SkeletonKey");
        //myWindow.pack();
        mainWindow.setSize(new Dimension(600, 1000));
        //myWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
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
        
        Scrollbar s=new Scrollbar();  
        //s.setBounds();  
        inner.add(s);  

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

    public static int[][] filterKeys(int[][] keyList, Filter[] filterList)
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
