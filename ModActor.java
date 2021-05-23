import javax.swing.JOptionPane;

/**
 * A customized ActionListener that does stuff to the main UI.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public class ModActor extends QuickListener
{
    private int index;
    private SortOption setting;
    private final ModAction action;
    private Filter[] filters;
    
    /**
     * Constructor for objects of class ModActor
     */
    public ModActor()
    {
        index = -1;
        action = ModAction.DO_NOTHING;
    }
    
    /**
     * Constructor for objects of class ModActor
     */
    public ModActor(ModAction act, int ind)
    {
        index = ind;
        action = act;
    }

    /**
     * Constructor for objects of class ModActor
     */
    public ModActor(ModAction act)
    {
        action = act;
    }
    
    /**
     * Constructor for objects of class QuickParamHelp
     */
    public ModActor(SortOption sortOption)
    {
        setting = sortOption;
        action = ModAction.SET_SORT_STYLE;
    }

    /**
     * Template setter
     */
    public ModActor(Filter[] template)
    {
        filters = template;
        action = ModAction.SET_FILTER_TEMPLATE;
    }

    public void act(String s)
    {
        switch(action)
        {
            case TOGGLE_FILTER:
            {
                UIStuff.toggleFilter(index);
                break;
            }
            case REMOVE_FILTER:
            {
                UIStuff.removeFilter(index);
                break;
            }
            case SET_SORT_STYLE:
            {
                UIStuff.setSortStyle(setting);
                break;
            }
            case SET_FILTER_TEMPLATE:
            {
                int input = JOptionPane.showConfirmDialog(null, 
                "Applying a template will erase all current filters. Are you sure you want to do this?", "Confirmation",JOptionPane.YES_NO_OPTION);
                if (input == 0)
                {
                    UIStuff.setFilterStatuses(ArrayHelper.getGroupOf(true,filters.length));
                    UIStuff.setCurFilters(filters);
                }
            }
            case TOGGLE_DARK_MODE:
            {
                UIStuff.toggle_dark_mode();
                break;
            }
            default:
                throw new IllegalArgumentException("Illegal ModActor action in constructor!");
        }
    }
}
