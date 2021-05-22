import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;


import java.awt.BorderLayout; 
import java.util.Arrays;

/**
 * Controls the UI and most other high-level functions.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public class UIStuff
{
    private static int[][] masterList, curList;

    private static EasyFrame mainWindow;
    private static EasyPanel innerPanel;

    private static final boolean debugMode = false;



    private static JMenu view_filters,
            remove_filters,
            filter_menu;


    private final Filter[] defaultFilters = new Filter[]{new Filter("isNamed")};

    private static boolean[] filterStatuses, storedFilterStatuses;
    private static Filter[] curFilters, storedFilters;

    private static int neutral_point = 300; // 300 =dorian

    private static KeyNamesHelper namer = new KeyNamesHelper();

    private static SortOption sortStyle;
    private static final String SORT1 = "Brightness (Ascending)";
    private static final String SORT2 = "Brightness (Descending)";
    private static final String SORT3 = "Strangeness (Ascending)";
    private static final String SORT4 = "Strangeness (Descending)";

    private static int[] absoluteList;
    private int keyOffset;

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
        KeyNamesHelper.initialize();
        curFilters = defaultFilters;
        filterStatuses = new boolean[]{};
        masterList = MathHelper.getAllKeys();
        sortStyle = SortOption.Strangeness_Ascending;
        
        oneTimeSetup();
        refresh();

    }
    
    
    public static void setFilterControlsDisabled(boolean d)
    {
        filter_menu.setEnabled(!d);
        //refresh();
    }
    
    /**
     * Sets the list of filters.
     */
    public static void setCurFilters(Filter[] newFilters)
    {
        curFilters = newFilters;
        refresh();
    }

    /**
     * Returns the index of the scale used for strangeness measurements.
     */
    public static int getNeutral()
    {
        return neutral_point;
    }

    /**
     * Sets the index of the scale used for strangeness measurements.
     */
    public static void setNeutral(int i)
    {
        neutral_point = i;
    }

    /**
     * Stores the current filter list in a variable.
     */
    public static void storeFilters()
    {
        storedFilters = Arrays.copyOf(curFilters,curFilters.length);
        storedFilterStatuses = Arrays.copyOf(filterStatuses,filterStatuses.length);
        refresh();
    }

    /**
     * Returns any temporarily stored filters.
     */
    public static Filter[] getStoredFilters()
    {
        return storedFilters;
    }

    /**
     * Returns all currently created filters, even the ones that aren't active.
     */
    public static Filter[] getCurrentFilters()
    {
        return curFilters;
    }

    /**
     * Returns any temporarily stored filter statuses.
     */
    public static boolean[] getStoredStatuses()
    {
        return storedFilterStatuses;
    }

    /**
     * Sets the statuses of the filters.
     */
    public static void setFilterStatuses(boolean[] newThings)
    {
        filterStatuses = newThings;
    }

    /**
     * Gets the statuses of the filters.
     */
    public static boolean[] getFilterStatuses()
    {
        return filterStatuses;
    }

    /**
     * Refreshes the view with any updated information about filters and sorting.
     */
    public static void refresh()
    {
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

    private static void updateKeys(int[][] keys)
    {
        innerPanel.removeAll();
        innerPanel.makeCenteredList();
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

                innerPanel.add(keyPanel,BorderLayout.WEST);

            }
        }

        //outer.setLayout(new ScrollPaneLayout());
        EasyPanel top = new EasyPanel();
        top.addHeader("Showing " + num + " out of " + masterList.length 
                + " keys. Hover over a key to see its modal relationships, if applicable.");
        innerPanel.add(top,0);

        mainWindow.pack();
        mainWindow.setWidth(800);
        
    }


    private static int[] styleSort(int[] list)
    {
        int[] result = new int[list.length];

        switch(sortStyle)
        {
            case Brightness_Ascending:
            {
                return list;
            }
            case Brightness_Descending:
            {
                return ArrayHelper.reverse(list);
            }
            case Strangeness_Ascending:
            {
                Integer[] temp = new Integer[]{};
                for(int c : list)
                {
                    temp = ArrayHelper.addX(temp,c);
                }
                Arrays.sort(temp, new StrangeCompare(neutral_point));
                int i = 0;
                //result = list
                for(int d : temp)
                {
                    result[i] = d;
                    i++;
                }
                return result;
            }
            case Strangeness_Descending: //descending "strangeness"
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

    public void setOffset(int n)
    {
        keyOffset = n;
        refresh();
    }


    private static String getKeyName(int[] key, int ind)
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
        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
        innerPanel = new EasyPanel();
        JScrollPane outer = new JScrollPane(innerPanel);
        outer.getVerticalScrollBar().setUnitIncrement(16);

        OffsetEditor ofs = new OffsetEditor(this);
        
        mainWindow.add(outer);

        JMenu audio,addfilter,viewops,sortops,ftemplates,other;
        JMenuItem i1, i2, i3, i4, i5, i6,livemaker;
        JMenuItem s1,s2,s3,s4,neu;
        JMenuItem a1, a2;  
        
        
        JMenuBar mb=new JMenuBar();  
        viewops=new JMenu("Sorting Options");
        filter_menu =new JMenu("Filter Options");
        audio=new JMenu("Audio Options");
        other=new JMenu("Viewing Options");
        

        sortops=new JMenu("Change Sorting Order");  
        neu=new JMenuItem("Change Neutral Point");  

        s1=new JMenuItem(SORT1);  
        s2=new JMenuItem(SORT2);  
        s3=new JMenuItem(SORT3);  
        s4=new JMenuItem(SORT4);  

        s1.addActionListener(new ModActor(SortOption.Brightness_Ascending));
        s2.addActionListener(new ModActor(SortOption.Brightness_Descending));
        s3.addActionListener(new ModActor(SortOption.Strangeness_Ascending));
        s4.addActionListener(new ModActor(SortOption.Strangeness_Descending));
        neu.addActionListener(new StrangeBox());

        view_filters =new JMenu("View/Toggle Active Filters");
        addfilter=new JMenu("Add New Filter"); 
        remove_filters =new JMenu("Remove Filter");
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
            item.addActionListener(new FilterCreator());
        }

        for (FilterTemplate t : TemplatesHelper.getAll())
        {
            JMenuItem t1 = new JMenuItem(t.getName());
            t1.addActionListener(new ModActor( t.getFilters()));
            ftemplates.add(t1);
        }

        a1=new JMenuItem("Change Note Speed");  
        a1.addActionListener(new TempoBox()); //not working?

        livemaker.addActionListener(new VirtualPiano()); //not working?

        viewops.add(sortops);
        viewops.add(neu);
        
        filter_menu.add(view_filters); filter_menu.add(addfilter);
        filter_menu.add(remove_filters); filter_menu.add(ftemplates);
        filter_menu.add(livemaker);
        addfilter.add(i1); addfilter.add(i2); 
        addfilter.add(i3); addfilter.add(i4);  
        addfilter.add(i5); addfilter.add(i6);
        //f.add(livemaker);

        s3.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");
        s4.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");

        sortops.add(s1); sortops.add(s2); sortops.add(s3); sortops.add(s4);

        audio.add(a1); //audio.add(a2);
        mb.add(viewops); mb.add(filter_menu); mb.add(audio); mb.add(ofs);
        mainWindow.setJMenuBar(mb);  
        
        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
        mainWindow.appear(mainWindow.MAIN);
    }

    /**
     * Changes the setting for sorting of scales.
     */
    public static void setSortStyle(SortOption newStyle)
    {
        sortStyle = newStyle;
        refresh();
    }

    private static void updateFilterList(Filter[] flist)
    {
        view_filters.removeAll();
        remove_filters.removeAll();
        int counter = 0;
        while (filterStatuses.length < flist.length)
        {
            filterStatuses = ArrayHelper.addX(filterStatuses,true);
        }

        if (flist.length == 0)
        {
            view_filters.add(new JMenuItem("(None)"));
            remove_filters.add(new JMenuItem("(None)"));

        }

        for (Filter f : flist)
        {
            String status;
            if (filterStatuses[counter])
            {
                status = "on";
            }
            else
            {
                status = "off";
            }
            //String status = enableText.get(filterStatuses[counter]);
            //System.out.println(filterStatuses[0]);
            JMenuItem button = new JMenuItem(f.translateToReadable() + " [" + status + "]" ); //the 

            JMenuItem button2 = new JMenuItem(f.translateToReadable() ); //the remove button

            button.addActionListener(new ModActor(ModAction.TOGGLE_FILTER,counter));
            button2.addActionListener(new ModActor(ModAction.REMOVE_FILTER,counter));
            view_filters.add(button); remove_filters.add(button2);
            counter++;
        }
        
    }

    /**
     * Toggles a filter's activeness.
     */
    public static void toggleFilter(int index)
    {
        boolean newSet = !filterStatuses[index];
        printlnDebug(String.valueOf(index) + newSet);
        filterStatuses[index] = newSet;
        refresh();
    }

    /**
     * Removes a filter.
     */
    public static void removeFilter(int index)
    {
        curFilters = ArrayHelper.removeOne(curFilters,index);
        filterStatuses = ArrayHelper.removeOne(filterStatuses,index);
        refresh();

    }

    private static int[][] filterKeys(int[][] keyList, Filter[] filterList)
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