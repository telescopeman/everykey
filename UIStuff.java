import java.util.Arrays;
import java.util.ArrayList;
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
    boolean[] filterStatuses = new boolean[]{true,true};

    private HashMap<Boolean,String> enableText;

    JMenu viewfilters, removefilters;

    Filter[] curFilters = defaultFilters;

    MathHelper myUtility;

    private int globalTempo = 140;

    private static KeyNamesHelper namer = new KeyNamesHelper();

    private String sortStyle;

    final String SORT1 = "Brightness (Ascending)";
    final String SORT2 = "Brightness (Descending)";
    final String SORT3 = "Strangeness (Ascending)";
    final String SORT4 = "Strangeness (Descending)";
    int[][] curList;
    int[] absoluteList;
    public UIStuff()
    {
        myUtility = new MathHelper();
        setTempo(globalTempo);
        masterList = myUtility.getAllKeys();
        sortStyle = SORT3;
        setupEnableText();
        oneTimeSetup();
        refresh();

    }

    public int getTempo()
    {
        return globalTempo;
    }

    public void setTempo(int n)
    {
        globalTempo = n;
        //PlayerWatcher.setTempo(n);
        
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
        refresh();
    }

    public void setFilterStatuses(boolean[] newThings)
    {
        filterStatuses = newThings;
    }

    public void refresh()
    {
        //printlnDebug("Refreshing...");
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
        int counter = -1;
        int num = 0;
        int[] list = new int[keys.length];
        // for (int[] key : keys)
        // {
        // counter++;

        // if (key[0] == 0)
        // {
        // //System.out.println("blanked");
        // //System.out.println(MathHelper.expand(key));
        // continue;
        // }
        // else
        // {
        // list[counter] = counter;
        // }
        // }
        counter = 0;
        int[] specificList = styleSort(absoluteList);
        for (int ind : specificList)
        {
            counter++;

            if (keys[ind][0] == 0)
            {
                //System.out.println("blanked");
                //System.out.println(MathHelper.expand(key));
                continue;
            }
            else
            {
                num++;

                KeyPanel keyPanel = new KeyPanel(ind, keys[ind], getKeyName(keys[ind]));

                inner.add(keyPanel,BorderLayout.WEST); 
                inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
            }
        }
        //outer.setLayout(new ScrollPaneLayout());
        JLabel lab = new JLabel("Showing " + num + " out of " + masterList.length + " keys. Hover over a key to see its modal relationships, if applicable.");
        JPanel header = new JPanel();
        header.add(lab);
        inner.add(header,0);

        mainWindow.pack();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(new Dimension(800, 1000));
        //outer.setPreferredSize(new Dimension(640,1000));
    }
    
    

    private int[] styleSort(int[] list)
    {
        int[] result = new int[list.length];

        switch(sortStyle)
        {
            case SORT1:
            {
                return list;
            }
            case SORT2:
            {
                return ArrayHelper.reverse(list);
            }
            case SORT3:
            {
                Integer[] temp = new Integer[]{};
                for(int c : list)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare());
                int i = 0;
                //result = list
                for(int d : temp)
                {
                    result[i] = d;
                    i++;
                }
                return result;
            }
            case SORT4:
            {
                Integer[] temp = new Integer[]{};
                for(int c : list)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare());
                int i = 0;
                //result = list
                for(int d : temp)
                {
                    result[i] = d;
                    i++;
                }
                return ArrayHelper.reverse(result);
            }
            default:
            {
                System.out.println("Invalid sort style!");
            }
        }
        return result;
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

    private void oneTimeSetup()
    {
        mainWindow = new EasyFrame("Skeleton Key");
        //myWindow.pack();
        //mainWindow.setSize();
        //myWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inner = new JPanel();
        outer = new JScrollPane(inner);
        outer.getVerticalScrollBar().setUnitIncrement(16);

        mainWindow.add(outer);

        JMenu filtermenu,audio,addfilter,viewops,sortops,ftemplates;
        //JMenuItem filterctrls = JMenuItem
        JMenuItem i1, i2, i3, i4, i5, i6;
        JMenuItem s1,s2,s3,s4;
        JMenuItem a1, a2;  
        JFrame f= new JFrame("Menu and MenuItem Example");  
        JMenuBar mb=new JMenuBar();  
        viewops=new JMenu("View Options");
        filtermenu=new JMenu("Filter Options");  
        audio=new JMenu("Audio Options");

        sortops=new JMenu("Change Sorting Order");  

        
        s1=new JMenuItem(SORT1);  
        s2=new JMenuItem(SORT2);  
        s3=new JMenuItem(SORT3);  
        s4=new JMenuItem(SORT4);  
        
        s1.addActionListener(new ModActor(this,"setSortStyle",SORT1));
        s2.addActionListener(new ModActor(this,"setSortStyle",SORT2));
        s3.addActionListener(new ModActor(this,"setSortStyle",SORT3));
        s4.addActionListener(new ModActor(this,"setSortStyle",SORT4));

        viewfilters=new JMenu("View/Toggle Active Filters");  
        addfilter=new JMenu("Add New Filter"); 
        removefilters=new JMenu("Remove Filter"); 
        ftemplates=new JMenu("Filter Templates"); 

        i1=new JMenuItem("Filter by Tonality");  
        i2=new JMenuItem("Filter by Note");  
        i3=new JMenuItem("Filter by Chord");  
        i4=new JMenuItem("Filter by Tags");  
        i5=new JMenuItem("Filter by Mode");  
        i6=new JMenuItem("Filter by Special");  

        for (JMenuItem item : new JMenuItem[]{i1,i2,i3,i4,i5,i6})
        {
            item.addActionListener(ModBox.buildFilterCreator(this));
        }
    
        for (FilterTemplate t : TemplatesHelper.getAll())
        {
            JMenuItem t1 = new JMenuItem(t.getName());
            t1.addActionListener(new ModActor(this, t.getFilters()));
            ftemplates.add(t1);
        }
        
        a1=new JMenuItem("Change Note Speed");  
        a1.addActionListener(ModBox.buildSettingsBox(this)); //not working?

        viewops.add(sortops);
        filtermenu.add(viewfilters); filtermenu.add(addfilter);
        filtermenu.add(removefilters); filtermenu.add(ftemplates);
        addfilter.add(i1); addfilter.add(i2); 
        addfilter.add(i3); addfilter.add(i4);  
        addfilter.add(i5); addfilter.add(i6);

        s3.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");
        s4.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");

        sortops.add(s1); sortops.add(s2); sortops.add(s3); sortops.add(s4);

        audio.add(a1); //audio.add(a2);
        mb.add(viewops); mb.add(filtermenu); mb.add(audio);
        mainWindow.setJMenuBar(mb);  

        mainWindow.appear(new Dimension(600, 1000));
    }

    public void setSortStyle(String newStyle)
    {
        sortStyle = newStyle;
        refresh();

    }

    private void updateFilterList(Filter[] flist)
    {
        viewfilters.removeAll();
        removefilters.removeAll();
        int counter = 0;

        for (Filter f : flist)
        {

            String status = enableText.get(filterStatuses[counter]);
            //System.out.println(filterStatuses[0]);
            JMenuItem button = new JMenuItem(f.translateToReadable() + " [" + status + "]" ); //the 

            JMenuItem button2 = new JMenuItem(f.translateToReadable() ); //the remove button

            button.addActionListener(new ModActor(this,"toggle",counter)); 
            button2.addActionListener(new ModActor(this,"remove",counter));
            viewfilters.add(button); removefilters.add(button2);
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

    public void removeFilter(int index)
    {
        curFilters = ArrayHelper.removeOne(curFilters,index);
        filterStatuses = ArrayHelper.removeOne(filterStatuses,index);
        refresh();

    }

    private int[][] filterKeys(int[][] keyList, Filter[] filterList)
    {

        int[][] newList = Arrays.copyOf(keyList,keyList.length);
        int num = 0;

        absoluteList = new int[]{};
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
            if (valid)
            {
                absoluteList = ArrayHelper.addX(absoluteList,num);
            }
            else
            {
                newList[num] = new int[]{0};
            }

            num++;
        }
        return newList;
    }
}
