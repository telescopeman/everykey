import javax.swing.*;


import java.awt.*;
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

    public static final Color LIGHT_MODE = Color.LIGHT_GRAY,
            DARK_MODE = MyChord.soften(Color.DARK_GRAY,5),
            LIGHT_TEXT = Color.WHITE,
            DARK_TEXT = Color.BLACK;

    private static EasyMenuBar menuBar = new EasyMenuBar();


    private static JMenu view_filters,
            remove_filters,
            filter_menu;


    private final Filter[] defaultFilters = new Filter[]{new Filter("isNamed")};

    private static boolean[] filterStatuses, storedFilterStatuses;
    private static Filter[] curFilters, storedFilters;

    private static int neutral_point = 300;

    private static SortOption sortStyle;
    private static final String SORT1 = "Brightness (Ascending)",
            SORT2 = "Brightness (Descending)",
            SORT3 = "Strangeness (Ascending)",
            SORT4 = "Strangeness (Descending)";

    private static int[] absoluteList;

    private static boolean is_dark_mode = false;

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
        adjustColors(menuBar);
        updateKeys(curList);
    }

    public static void adjustColors(Component component)
    {
        try {

            if (is_dark_mode) {
                ((VariableColor) component).doDarkMode();

            } else {
                ((VariableColor) component).doLightMode();
            }
        }
        catch (ClassCastException e)
        {
            if (is_dark_mode) {
                component.setBackground(DARK_MODE);
                component.setForeground(LIGHT_TEXT);

            } else {
                component.setBackground(LIGHT_MODE);
                component.setForeground(DARK_TEXT);
            }
        }

        try
        {

            Container container = (Container) component;
            for(Component c : container.getComponents())
            {
                adjustColors(c);
            }
        }
        catch (ClassCastException e)
        {

            // do nothing
        }
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


    private static String getKeyName(int[] key, int ind)
    {
        String name = KeyNamesHelper.smartGet(key,ind);
        String dispName = TheoryObj.expandSmart(key,ind,true);
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

    public static void toggle_dark_mode() {
        is_dark_mode = !is_dark_mode;
        System.out.println("Ffff");
        refresh();
    }

    public static boolean get_is_dark_mode()
    {
        return is_dark_mode;
    }

    private void oneTimeSetup()
    {
        mainWindow = new EasyFrame("Skeleton Key");
        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
        innerPanel = new EasyPanel();

        menuBar.setOpaque(true);

        JScrollPane outer = new JScrollPane(innerPanel);
        outer.getVerticalScrollBar().setUnitIncrement(16);

        OffsetEditor ofs = new OffsetEditor();
        
        mainWindow.add(outer);

        JMenu audio,add_filter,sorting_options,sort_order_change,choose_filter_templates,viewing_options;
        JMenuItem i1, i2, i3,
                i4, i5, i6,
                musical_typing,
                s1,s2,s3,s4,
                change_neutral_point,
                note_speed_settings,
                dark_mode;
        

        sorting_options=new JMenu("Sorting Options");
        filter_menu =new JMenu("Filter Options");
        audio=new JMenu("Audio Options");
        viewing_options=new JMenu("Viewing Options");

        sorting_options.setOpaque(false);
        filter_menu.setOpaque(false);
        audio.setOpaque(false);
        viewing_options.setOpaque(false);

        //sorting options
        sort_order_change=new JMenu("Change Sorting Order");
        change_neutral_point=new JMenuItem("Change Neutral Point");

        s1=new JMenuItem(SORT1);  
        s2=new JMenuItem(SORT2);  
        s3=new JMenuItem(SORT3);  
        s4=new JMenuItem(SORT4);  

        s1.addActionListener(new ModActor(SortOption.Brightness_Ascending));
        s2.addActionListener(new ModActor(SortOption.Brightness_Descending));
        s3.addActionListener(new ModActor(SortOption.Strangeness_Ascending));
        s4.addActionListener(new ModActor(SortOption.Strangeness_Descending));
        change_neutral_point.addActionListener(new StrangeBox());

        sorting_options.add(sort_order_change);
        sorting_options.add(change_neutral_point);

        s3.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");
        s4.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");

        sort_order_change.add(s1); sort_order_change.add(s2); sort_order_change.add(s3); sort_order_change.add(s4);

        //filtering stuff

        view_filters =new JMenu("View/Toggle Active Filters");
        add_filter=new JMenu("Add New Filter");
        remove_filters =new JMenu("Remove Filter");
        choose_filter_templates=new JMenu("Filter Templates");
        musical_typing=new JMenuItem("Open Musical Typing");

        i1=new ActionItem(FilterCreationSetting.TONALITY,"Filter by Tonality");
        i2=new ActionItem(FilterCreationSetting.NOTE,"Filter by Note");
        i3=new ActionItem(FilterCreationSetting.CHORD,"Filter by Chord");
        i4=new ActionItem(FilterCreationSetting.TAGS,"Filter by Tags");
        i5=new ActionItem(FilterCreationSetting.MODE,"Filter by Mode");
        i6=new ActionItem(FilterCreationSetting.SPECIAL, "Filter by Special");

        for (FilterTemplate t : TemplatesHelper.getAll())
        {
            JMenuItem t1 = new JMenuItem(t.getName());
            t1.addActionListener(new ModActor( t.getFilters()));
            choose_filter_templates.add(t1);
        }

        musical_typing.addActionListener(new VirtualPiano()); //not working?


        note_speed_settings=new JMenuItem("Change Note Speed");
        note_speed_settings.addActionListener(new TempoBox()); //not working?


        
        filter_menu.add(view_filters); filter_menu.add(add_filter);
        filter_menu.add(remove_filters); filter_menu.add(choose_filter_templates);
        filter_menu.add(musical_typing);
        add_filter.add(i1); add_filter.add(i2);
        add_filter.add(i3); add_filter.add(i4);
        add_filter.add(i5); add_filter.add(i6);

        //audio

        audio.add(note_speed_settings); //audio.add(a2);

        //viewing options

        dark_mode = new ActionItem(new ModActor(ModAction.TOGGLE_DARK_MODE),"Toggle Dark Mode");
        viewing_options.add(dark_mode);


        menuBar.add(sorting_options); menuBar.add(filter_menu); menuBar.add(audio);
        menuBar.add(viewing_options); menuBar.add(ofs);

        mainWindow.setJMenuBar(menuBar);
        
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
            JMenuItem button = new JMenuItem(f.toString() + " [" + status + "]" ); //the

            JMenuItem button2 = new JMenuItem(f.toString() ); //the remove button

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