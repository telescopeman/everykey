import java.util.Arrays;
import javax.swing.*;
import java.awt.*; 
import javax.swing.BoxLayout;

import java.util.HashMap;

/**
 * Controls the UI and most other high-level functions.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public class UIStuff
{
    private int[][] masterList;
    private EasyFrame mainWindow;

    private static boolean debugMode = false;

    private JPanel inner;
    private JScrollPane outer;

    private HashMap<Boolean,String> enableText;
    private JMenu viewfilters, removefilters;
    private final Filter[] defaultFilters = new Filter[]{new Filter("isNamed")};
    private boolean[] filterStatuses = new boolean[]{};
    private Filter[] curFilters = defaultFilters;
    private Filter[] storedFilters;
    private boolean[] storedFilterStatuses;

    private int neutralpoint = 300; // 300 =dorian

    private static KeyNamesHelper namer = new KeyNamesHelper();

    private String sortStyle;
    private final String SORT1 = "Brightness (Ascending)";
    private final String SORT2 = "Brightness (Descending)";
    private final String SORT3 = "Strangeness (Ascending)";
    private final String SORT4 = "Strangeness (Descending)";

    private int[][] curList;
    private int[] absoluteList;

    private String[] nameCache;

    /**
     * Runs the main program.
     */
    public static void main(String[] args)
    {
        UIStuff ui = new UIStuff();
    }

    /**
     * Runs the main program.
     */
    public UIStuff()
    {
        masterList = MathHelper.getAllKeys();
        sortStyle = SORT3;
        setupEnableText();
        oneTimeSetup();
        refresh();

    }

    private void setupEnableText()
    {
        enableText = new HashMap<Boolean,String>();
        enableText.put(true,"on");
        enableText.put(false,"off");

    }
    /**
     * Sets the list of filters.
     */
    public void setCurFilters(Filter[] newFilters)
    {
        curFilters = newFilters;
        refresh();
    }

    /**
     * Returns the index of the scale used for strangeness measurements.
     */
    public int getNeutral()
    {
        return neutralpoint;
    }

    /**
     * Sets the index of the scale used for strangeness measurements.
     */
    public void setNeutral(int i)
    {
        neutralpoint = i;
    }

    /**
     * Stores the current filter list in a variable.
     */
    public void storeFilters()
    {
        storedFilters = Arrays.copyOf(curFilters,curFilters.length);
        storedFilterStatuses = Arrays.copyOf(filterStatuses,filterStatuses.length);
        refresh();
    }

    /**
     * Returns any temporarily stored filters.
     */
    public Filter[] getStoredFilters()
    {
        return storedFilters;
    }

    /**
     * Returns all currently created filters, even the ones that aren't active.
     */
    public Filter[] getCurrentFilters()
    {
        return curFilters;
    }

    /**
     * Returns any temporarily stored filter statuses.
     */
    public boolean[] getStoredStatuses()
    {
        return storedFilterStatuses;
    }

    /**
     * Sets the statuses of the filters.
     */
    public void setFilterStatuses(boolean[] newThings)
    {
        filterStatuses = newThings;
    }

    /**
     * Gets the statuses of the filters.
     */
    public boolean[] getFilterStatuses()
    {
        return filterStatuses;
    }

    /**
     * Refreshes the view with any updated information about filters and sorting.
     */
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
        JPanel temp = new JPanel();
        inner.removeAll();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        int counter = -1;
        int num = 0;
        int[] specificList = styleSort(absoluteList);
        for (int ind : specificList)
        {
            counter++;

            if (keys[ind][0] == 0) //if the first note is nonexistent
            {
                continue;
            }
            else
            {
                num++;
                //System.out.println(ind);
                KeyPanel keyPanel = 
                    new KeyPanel(ind, keys[ind],getKeyName(keys[ind],ind));

                inner.add(keyPanel,BorderLayout.WEST); 

            }
        }

        
        
        JLabel lab = new JLabel("Showing " + num + " out of " + masterList.length 
                + " keys. Hover over a key to see its modal relationships, if applicable.");
        JPanel header = new JPanel();
        header.add(lab);
        inner.add(header,0);
        inner = temp;
        //temp.dispose();
        
        outer = new JScrollPane(inner);
        //outer.getVerticalScrollBar().setUnitIncrement(16);
        outer.setLayout(new ScrollPaneLayout());

        mainWindow.pack();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(new Dimension(750, 1000));
        //outer.setPreferredSize(new Dimension(640,1000));
    }


    private int[] styleSort(int[] list)
    {
        int[] result = new int[list.length];

        switch(sortStyle)
        {
            case SORT1: //ascending brightness
            {
                return list;
            }
            case SORT2: //descending brightness
            {
                return ArrayHelper.reverse(list);
            }
            case SORT3: //ascending "strangeness"
            {
                Integer[] temp = new Integer[]{};
                for(int c : list)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare(neutralpoint));
                int i = 0;
                //result = list
                for(int d : temp)
                {
                    result[i] = d;
                    i++;
                }
                return result;
            }
            case SORT4: //descending "strangeness"
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

    // private String getKeyName(int[] key)
    // {
        // String name = namer.smartGet(key,ind); 
        // //this is inefficient. not sure how to fix this.
        // if (name == "")
        // {
            // return "Unnamed Key (" + MathHelper.expandSmart(key,ind,true) + ")";
        // }
        // else
        // {
            // return name + " (" + MathHelper.expandSmart(key,ind,true) + ")";
        // }

    // }

    private String getKeyName(int[] key, int ind)
    {
        String name = namer.smartGet(key,ind); 
        String dispName = namer.expandSmart(key,ind,true);
        //this is inefficient. not sure how to fix this.
        if (name == "")
        {
            return "Unnamed Key (" + dispName + ")";
        }
        else
        {
            return name + " (" + dispName + ")";
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

        JMenu filtermenu,audio,addfilter,viewops,sortops,ftemplates,other;
        //JMenuItem filterctrls = JMenuItem
        JMenuItem i1, i2, i3, i4, i5, i6,livemaker;
        JMenuItem s1,s2,s3,s4,neu;
        JMenuItem a1, a2;  
        JFrame f= new JFrame("Menu and MenuItem Example");  
        JMenuBar mb=new JMenuBar();  
        viewops=new JMenu("Sorting Options");
        filtermenu=new JMenu("Filter Options");  
        audio=new JMenu("Audio Options");
        other=new JMenu("Viewing Options");

        sortops=new JMenu("Change Sorting Order");  
        neu=new JMenuItem("Change Neutral Point");  

        s1=new JMenuItem(SORT1);  
        s2=new JMenuItem(SORT2);  
        s3=new JMenuItem(SORT3);  
        s4=new JMenuItem(SORT4);  

        s1.addActionListener(new ModActor(this,"setSortStyle",SORT1));
        s2.addActionListener(new ModActor(this,"setSortStyle",SORT2));
        s3.addActionListener(new ModActor(this,"setSortStyle",SORT3));
        s4.addActionListener(new ModActor(this,"setSortStyle",SORT4));
        neu.addActionListener(new StrangeBox(this));

        viewfilters=new JMenu("View/Toggle Active Filters");  
        addfilter=new JMenu("Add New Filter"); 
        removefilters=new JMenu("Remove Filter"); 
        ftemplates=new JMenu("Filter Templates"); 
        livemaker=new JMenuItem("Open Musical Typing");

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
        a1.addActionListener(ModBox.buildTempoBox(this)); //not working?

        livemaker.addActionListener(ModBox.buildVirtualPiano(this)); //not working?

        viewops.add(sortops);
        viewops.add(neu);
        filtermenu.add(viewfilters); filtermenu.add(addfilter);
        filtermenu.add(removefilters); filtermenu.add(ftemplates);
        filtermenu.add(livemaker);
        addfilter.add(i1); addfilter.add(i2); 
        addfilter.add(i3); addfilter.add(i4);  
        addfilter.add(i5); addfilter.add(i6);
        //f.add(livemaker);

        s3.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");
        s4.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");

        sortops.add(s1); sortops.add(s2); sortops.add(s3); sortops.add(s4);

        audio.add(a1); //audio.add(a2);
        mb.add(viewops); mb.add(filtermenu); mb.add(audio); //mb.add(other);
        mainWindow.setJMenuBar(mb);  

        mainWindow.appear(new Dimension(600, 1000));
    }

    /**
     * Changes the setting for sorting of scales.
     */
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
        while (filterStatuses.length < flist.length)
        {
            filterStatuses = ArrayHelper.addX(filterStatuses,true);
        }

        if (flist.length == 0)
        {
            viewfilters.add(new JMenuItem("(None)"));
            removefilters.add(new JMenuItem("(None)"));

        }

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

    /**
     * Toggles a filter's activeness.
     */
    public void toggleFilter(int index)
    {
        boolean newSet = !filterStatuses[index];
        printlnDebug(String.valueOf(index) + newSet);
        filterStatuses[index] = newSet;
        refresh();
    }

    /**
     * Removes a filter.
     */
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

                if (!filterStatuses[num2])
                {
                    valid = true;
                    continue;
                }

                if (!f.checkKey(key,num))
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
