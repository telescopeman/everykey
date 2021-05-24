import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;

/**
 * @since 5/23/21
 */
public class UI {

    private static final EasyFrame mainWindow = new EasyFrame("Skeleton Key",EasyFrame.MAIN_WINDOW);
    private static final EasyPanel innerPanel = new EasyPanel();
    private static final EasyMenuBar menuBar = new EasyMenuBar();
    private static final JMenu view_filters =new JMenu("View/Toggle Active Filters"),
            remove_filters =new JMenu("Remove Filter");

    private static final int[][] masterList = ScalesGenerator.getAllKeys();
    private static int[][] curList;

    private static boolean is_dark_mode = false;


    /**
     * Retrieves the master list of all scales.
     */
    public static int[][] getMasterList()
    {
        return masterList;
    }


    /**
     * Sets up the basic UI elements.
     */
    public static void initialize()
    {
        JScrollPane outer = new JScrollPane(innerPanel);
        outer.getVerticalScrollBar().setUnitIncrement(16);
        mainWindow.add(outer);

        JMenu audio,add_filter,sorting_options,sort_order_change,
                choose_filter_templates,viewing_options;

        JMenuItem musical_typing,
                change_neutral_point,
                note_speed_settings,
                dark_mode;


        sorting_options=new JMenu("Sorting Options");
        JMenu filter_menu = new JMenu("Filter Options");
        audio=new JMenu("Audio Options");
        viewing_options=new JMenu("Viewing Options");

        sorting_options.setOpaque(false);
        filter_menu.setOpaque(false);
        audio.setOpaque(false);
        viewing_options.setOpaque(false);

        //sorting options
        sort_order_change=new JMenu("Change Sorting Order");
        change_neutral_point=new JMenuItem("Change Neutral Point");

        ActionItem s1=new ActionItem(SortOption.Brightness_Ascending);
        ActionItem s2=new ActionItem(SortOption.Brightness_Descending);
        ActionItem s3=new ActionItem(SortOption.Strangeness_Ascending);
        ActionItem s4=new ActionItem(SortOption.Strangeness_Descending);
        ActionItem s5=new ActionItem(SortOption.Interval_Oddness_Ascending);
        ActionItem s6=new ActionItem(SortOption.Interval_Oddness_Descending);

        change_neutral_point.addActionListener(new StrangeBox());

        sorting_options.add(sort_order_change);
        sorting_options.add(change_neutral_point);

        s3.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");
        s4.setToolTipText("\"Strangeness\" refers to a scale's distance from Dorian.");

        sort_order_change.add(s1); sort_order_change.add(s2);
        sort_order_change.add(s3); sort_order_change.add(s4);
        sort_order_change.add(s5); sort_order_change.add(s6);

        //filter stuff

        add_filter=new JMenu("Add New Filter");
        choose_filter_templates=new JMenu("Filter Templates");
        musical_typing=new JMenuItem("Open Musical Typing");

        ActionItem i1=new ActionItem(FilterCreationSetting.TONALITY,"Filter by Tonality");
        ActionItem i2=new ActionItem(FilterCreationSetting.NOTE,"Filter by Note");
        ActionItem i3=new ActionItem(FilterCreationSetting.CHORD,"Filter by Chord");
        ActionItem i4=new ActionItem(FilterCreationSetting.TAGS,"Filter by Tags");
        ActionItem i5=new ActionItem(FilterCreationSetting.MODE,"Filter by Mode");
        ActionItem i6=new ActionItem(FilterCreationSetting.SPECIAL, "Filter by Special");

        for (FilterTemplate t : TemplatesHelper.getAll())
        {
            JMenuItem t1 = new JMenuItem(t.getName());
            t1.addActionListener(new ModActor( t.getFilters()));
            choose_filter_templates.add(t1);
        }

        musical_typing.addActionListener(new VirtualPiano());


        note_speed_settings=new JMenuItem("Change Note Speed");
        note_speed_settings.addActionListener(new TempoBox());



        filter_menu.add(view_filters); filter_menu.add(add_filter);
        filter_menu.add(remove_filters); filter_menu.add(choose_filter_templates);
        filter_menu.add(musical_typing);
        add_filter.add(i1); add_filter.add(i2);
        add_filter.add(i3); add_filter.add(i4);
        add_filter.add(i5); add_filter.add(i6);

        //audio

        audio.add(note_speed_settings);

        //viewing options

        dark_mode = new ActionItem(new ModActor(ModAction.TOGGLE_DARK_MODE),"Toggle Dark Mode");
        viewing_options.add(dark_mode);
        OffsetEditor ofs = new OffsetEditor();

        menuBar.add(sorting_options); menuBar.add(filter_menu); menuBar.add(audio);
        menuBar.add(viewing_options); menuBar.add(ofs);

        mainWindow.setJMenuBar(menuBar);

        mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
        mainWindow.appear();
    }




    /**
     * Refreshes the view with any updated information about filters and sorting.
     */


    private static void updateKeys(int[][] keys)
    {
        innerPanel.removeAll();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        int num = 0;
        int[] specificList = Control.styleSort();
        for (int ind : specificList)
        {
            if (keys[ind][0] != 0) //if the first note is nonexistent
            {
                num++;
                KeyPanel keyPanel =
                        new KeyPanel(ind, keys[ind],KeyNamesHelper.getKeyName(keys[ind],ind));

                innerPanel.add(keyPanel, BorderLayout.WEST);

            }
        }

        EasyPanel top = new EasyPanel();
        top.addHeader("Showing " + num + " out of " + masterList.length
                + " keys. Hover over a key to see its modal relationships, if applicable.");
        innerPanel.add(top,0);

        mainWindow.pack();
        mainWindow.setSize(new Dimension(800,mainWindow.getSize().height));

    }

    public static void refresh()
    {
        updateFilterList(FilterBank.getCurrentFilters());
        curList = Control.filterKeys();
        adjustColors(menuBar);
        updateKeys(curList);
    }

    public static int[][] getCurrentList()
    {
        return curList;
    }

    private static void updateFilterList(Filter[] filters)
    {
        view_filters.removeAll();
        remove_filters.removeAll();
        int counter = 0;
        while (FilterBank.getFilterStatuses().length < filters.length)
        {
            FilterBank.setFilterStatuses(
                    ArrayHelper.addX(FilterBank.getFilterStatuses(),true));
        }

        if (filters.length == 0)
        {
            view_filters.add(new JMenuItem("(None)"));
            remove_filters.add(new JMenuItem("(None)"));

        }

        for (Filter f : filters)
        {
            String status;
            if (FilterBank.getFilterStatuses()[counter])
            {
                status = "on";
            }
            else
            {
                status = "off";
            }
            JMenuItem button = new JMenuItem(f.toString() + " [" + status + "]" ); //the

            JMenuItem button2 = new JMenuItem(f.toString() ); //the remove button

            button.addActionListener(new ModActor(ModAction.TOGGLE_FILTER,counter));
            button2.addActionListener(new ModActor(ModAction.REMOVE_FILTER,counter));
            view_filters.add(button); remove_filters.add(button2);
            counter++;
        }
    }


    public static void toggle_dark_mode() {
        is_dark_mode = !is_dark_mode;
        refresh();
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
                component.setBackground(ColorsHelper.DARK_MODE);
                component.setForeground(ColorsHelper.LIGHT_TEXT);
            } else {
                component.setBackground(ColorsHelper.LIGHT_MODE);
                component.setForeground(ColorsHelper.DARK_TEXT);
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



}
