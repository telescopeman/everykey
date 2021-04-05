import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
/**
 * A customized ActionListener that does stuff to the main UI.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class ModActor implements ActionListener
{
    
    private UIStuff uiref;
    private int index;
    private String setting;
    private String action;
    private Filter[] filters;
    
    /**
     * Constructor for objects of class ModActor
     */
    public ModActor(UIStuff ui)
    {
        uiref = ui;
        index = -1;
        action = "";
    }
    
    /**
     * Constructor for objects of class ModActor
     */
    public ModActor(UIStuff ui, String act, int ind)
    {
        uiref = ui;
        index = ind;
        action = act;
    }
    
    /**
     * Constructor for objects of class QuickParamHelp
     */
    public ModActor(UIStuff ui, String act, String set)
    {
        uiref = ui;
        setting = set;
        action = act;
    }

    /**
     * Template setter
     */
    public ModActor(UIStuff ui, Filter[] template)
    {
        uiref = ui;
        filters = template;
        action = "setTemplate";
    }

    public void actionPerformed(ActionEvent e)
    {
        switch(action)
        {
            case "toggle":
            {
                uiref.toggleFilter(index);
                break;
            }
            case "remove":
            {
                uiref.removeFilter(index);
                break;
            }
            case "setSortStyle":
            {
                uiref.setSortStyle(setting);
                break;
            }
            case "setTemplate":
            {
                int input = JOptionPane.showConfirmDialog(null, 
                "Applying a template will erase all current filters. Are you sure you want to do this?", "Confirmation",JOptionPane.YES_NO_OPTION);
                if (input == 0)
                {
                    uiref.setFilterStatuses(ArrayHelper.getGroupOf(true,filters.length));
                    uiref.setCurFilters(filters);
                }
            }
            default:
                throw new IllegalArgumentException("Illegal ModActor action in constructor!");
        }
    }
}
