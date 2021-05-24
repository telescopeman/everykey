import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

/**
 * @since 5/23/21
 */
public class UI {

    private static EasyFrame mainWindow;
    private static EasyPanel innerPanel;
    private static final EasyMenuBar menuBar = new EasyMenuBar();
    private static JMenu view_filters,
            remove_filters,
            filter_menu;

    private static int[][] masterList, curList;

    private static boolean is_dark_mode = false;


    public static int[][] getMasterList()
    {
        return masterList;
    }


    public static void initialize()
    {
        masterList = MathHelper.getAllKeys();
    }

    public static void oneTimeSetup()
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

        s1=new JMenuItem(String.valueOf(SortOption.Brightness_Ascending));
        s2=new JMenuItem(String.valueOf(SortOption.Brightness_Descending));
        s3=new JMenuItem(String.valueOf(SortOption.Strangeness_Ascending));
        s4=new JMenuItem(String.valueOf(SortOption.Strangeness_Descending));

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
        mainWindow.appear(EasyFrame.MAIN);
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
        mainWindow.setWidth(800);

    }

    public static void refresh()
    {
        updateFilterList(FilterBank.getCurrentFilters());
        curList = Control.filterKeys();
        adjustColors(menuBar);
        updateKeys(curList);
    }

    private static void updateFilterList(Filter[] flist)
    {
        view_filters.removeAll();
        remove_filters.removeAll();
        int counter = 0;
        while (FilterBank.getFilterStatuses().length < flist.length)
        {
            FilterBank.setFilterStatuses(
                    ArrayHelper.addX(FilterBank.getFilterStatuses(),true));
        }

        if (flist.length == 0)
        {
            view_filters.add(new JMenuItem("(None)"));
            remove_filters.add(new JMenuItem("(None)"));

        }

        for (Filter f : flist)
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

    public static boolean get_is_dark_mode()
    {
        return is_dark_mode;
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
