import java.util.Arrays;
import javax.swing.*;
import java.awt.*; 
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.awt.event.ActionEvent;

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
    EasyFrame mainWindow;

    static boolean debugMode = true;

    JPanel inner;
    JScrollPane outer;
    //Filter[] defaultFilters = new Filter[]{new Filter(8,4),new Filter(new int[]{4,5},2)}; //perfect fifth to the root
    Filter[] defaultFilters = new Filter[]{new Filter("isNamed")};
    boolean[] filterStatuses = new boolean[]{true};
    
    private HashMap<Boolean,String> enableText;
    
    JMenu viewfilters;

    Filter[] curFilters = defaultFilters;

    MathHelper myUtility;
    
    private static KeyNamesHelper namer = new KeyNamesHelper();

    
    int[][] curList;
    
    public UIStuff()
    {
        myUtility = new MathHelper();
        masterList = myUtility.getAllKeys();
        setupEnableText();
        irrelevantSetup();
        refresh();
        
    }
    
       
    
    
    
    
    public void setupEnableText()
    {
        enableText = new HashMap<Boolean,String>();
        enableText.put(true,"on");
        enableText.put(false,"off");
        //System.out.print(enableText);
        
    }
    
    public void setCurFilters(Filter[] newFilters)
    {
        curFilters = newFilters;
        
    }
    
    public void setFilterStatuses(boolean[] newThings)
    {
        filterStatuses = newThings;
    }
    
    public void refresh()
    {
        printlnDebug("Refreshing...");
        updateFilterList(curFilters);
        curList = filterKeys(masterList, curFilters);
        
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
        int num = 0;
        
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
                num++;
                //System.out.println(String.valueOf(counter) + ": " + MathHelper.expand(key));

                String name = "#" + String.valueOf(counter) + ": " + getKeyName(key);

                JButton jb1 = new JButton("Chords");    
                ChordViewer chrds = new ChordViewer(key,getKeyName(key));
                chrds.myKey = key;
                jb1.addActionListener(chrds);
                
                          
                        

                        JButton jb2 = new JButton("Intervals");
                        JButton jb3 = new JButton("Button 3");      
                        JButton jb4 = new JButton("Button 4");
                        JButton jb5 = new JButton("Button 5");

                        JLabel label = new JLabel(name);
                        //System.out.println(key.toString());
                        JPanel keyPanel = new JPanel();

                        keyPanel.add(label);
                        keyPanel.add(jb1); //keyPanel.add(jb2); 

                        inner.add(keyPanel); 
                        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
                    }
            }
            //outer.setLayout(new ScrollPaneLayout());
            JLabel lab = new JLabel("Showing " + num + " out of 462 keys.");
            JPanel header = new JPanel();
            header.add(lab);
            inner.add(header,0);

            mainWindow.pack();
            mainWindow.setSize(new Dimension(800, 1000));
            //outer.setPreferredSize(new Dimension(640,1000));
        }

        private String getKeyName(int[] key)
        {
        String name =namer.get(key);
        if (name == "")
        {
            return "Unnamed Key (" + MathHelper.expand(key,true) + ")";
        }
        else
        {
            return name + " (" + MathHelper.expand(key,true) + ")";
        }

        
    }

    private void irrelevantSetup()
    {
        mainWindow = new EasyFrame("Skeleton Key");
        //myWindow.pack();
        mainWindow.setSize(new Dimension(600, 1000));
        //myWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inner = new JPanel();
        outer = new JScrollPane(inner);

        mainWindow.add(outer);

        JMenu filtermenu, viewops, addfilter; 
        JMenuItem i1, i2, i3, i4, i5;  
        JFrame f= new JFrame("Menu and MenuItem Example");  
        JMenuBar mb=new JMenuBar();  
        
        filtermenu=new JMenu("Filter Options");  
        viewops=new JMenu("View Options");
        
        viewfilters=new JMenu("View Active Filters");  
        addfilter=new JMenu("Add New Filter"); 
        i1=new JMenuItem("Filter by Tonality");  
        i2=new JMenuItem("Filter by Note");  
        i3=new JMenuItem("Filter by Chord");  
        i4=new JMenuItem("Filter by something else idk");  
        i5=new JMenuItem("deez nuts");  
        
        
        i1.addActionListener(makeFC());
        i2.addActionListener(makeFC());
        i3.addActionListener(makeFC());

        filtermenu.add(viewfilters); filtermenu.add(addfilter); 
        addfilter.add(i1); addfilter.add(i2); addfilter.add(i3);  
        mb.add(filtermenu); mb.add(viewops);
        mainWindow.setJMenuBar(mb);  

        mainWindow.show();
    }
    
     /**
     * Quick way to make FilterCreator class
     */
    private FilterCreator makeFC()
    {
        return new FilterCreator(this);
        
    }
    
    

    private void updateFilterList(Filter[] flist)
    {
        viewfilters.removeAll();
        int counter = 0;
        
        for (Filter f : flist)
        {
            

            
            String thing = enableText.get(filterStatuses[counter]);
            
            System.out.println(filterStatuses[0]);
            
            String label = f.translateToReadable() + " [" + thing + "]";
            
            FilterToggler button = new FilterToggler(counter);
            button.setText(label);
            ActionListener menuListener = new ActionListener()
            {
                      @Override
                      public void actionPerformed(ActionEvent event)
                      {
                        String invAction = event.getActionCommand();
        
        
                        
                        FilterToggler actItem = button;
                        
                        int ind = button.getIndex();
                        //System.out.print(String.getValue(ind));
                        
                        toggleFilter(ind);
    
                        //System.out.println("Popup menu item [" + invAction + "] [ " + actItem + " ] was pressed.");
                }
            }
            ;
            
            
            button.addActionListener(menuListener);
            viewfilters.add(button);  
            counter++;
        }
    }
    
    public void toggleFilter(int index)
    {
        
        boolean newSet = !filterStatuses[index];
        printlnDebug(String.valueOf(index) + newSet);
        filterStatuses[index] = newSet;
        refresh();
    }

    private int[][] filterKeys(int[][] keyList, Filter[] filterList)
    {

        int[][] newList = Arrays.copyOf(keyList,keyList.length);
        int num = 0;
        
        for (int[] key : keyList)
        {
            boolean valid = true;
            int num2 = -1;
            for (Filter f : filterList)
            {
                num2++;
                //printlnDebug(String.valueOf(num2) + f);
                if (!filterStatuses[num2])
                {
                    valid = true;
                    continue;
                }
                
                

                if (!f.checkKey(key))
                {
                    //System.out.println("Failed filter test" + num);
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
