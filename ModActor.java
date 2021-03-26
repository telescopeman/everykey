import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Write a description of class QuickParamHelp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ModActor implements ActionListener
{
    // instance variables - replace the example below with your own
    private UIStuff uiref;
    private int index;
    private String setting;
    private String action;

    /**
     * Constructor for objects of class QuickParamHelp
     */
    public ModActor(UIStuff ui)
    {
        // initialise instance variables
        uiref = ui;
        index = -1;
        action = "";
    }
    
    /**
     * Constructor for objects of class QuickParamHelp
     */
    public ModActor(UIStuff ui, String act, int ind)
    {
        uiref = ui;
        index = ind;
        action = act;
        
        // initialise instance variables
        
    }
    
    /**
     * Constructor for objects of class QuickParamHelp
     */
    public ModActor(UIStuff ui, String act, String set)
    {
        uiref = ui;
        setting = set;
        action = act;
        
        // initialise instance variables
        
    }


    public void actionPerformed(ActionEvent e)
    {
        // put your code here
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
            }
            
            default:
            
            
            
        }
    }
}
