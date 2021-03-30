import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 
import java.util.Arrays;
/**
 * Represents a dialog box that will modify the main class through user input.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public abstract class ModBox extends EasyFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    public UIStuff ui;
    private final Dimension STANDARD = new Dimension(350,100);
    public final Dimension SUPERSTANDARD = new Dimension(550,150);
    ArrayHelper arr;
    
    public final String[] CHROMATICSCALE = new String[]{"C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B"};
    /**
     * Constructor for objects of class ModBox
     */
    public ModBox(UIStuff uiref)
    {
        // initialise instance variables
        ui = uiref;
        arr = new ArrayHelper();
        setLayout(new GridLayout(3,1));
    }
    
    /**
     * Constructor for objects of class ModBox
     */
    public ModBox(UIStuff uiref, boolean t)
    {
        // initialise instance variables
        ui = uiref;
        arr = new ArrayHelper();
        
    }
    
    
    public static FilterCreator buildFilterCreator(UIStuff uiref)
    {
        return new FilterCreator(uiref);
        
    }
    
    public static SettingsBox buildSettingsBox(UIStuff uiref)
    {
        return new SettingsBox(uiref);
        
    }
    
    public static VirtualPiano buildVirtualPiano(UIStuff uiref)
    {
        return new VirtualPiano(uiref);
        
    }
    
    // /**
     // * Gets a child of ModBox.
     // */
    // private ModBox makeModBox(String type)
    // {
        // switch (type)
        // {
            // case "FilterCreator":
                // return new FilterCreator(this);
            // case "AudioSpeed":
                // return new SettingsBox(this);
            // default:
                // return new FilterCreator(this);
        // }
    // }

    
    
    
    public void actionPerformed(ActionEvent e)
    {
        clear();
        appear(STANDARD);
    }
    
    
    
    
}
