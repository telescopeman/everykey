/**
 * Represents a dialog box that will modify the main class through user input.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public abstract class ModBox extends EasyFrame
{
    // instance variables - replace the example below with your own
    /**
     * The ModBox's link back to the UI controller.
     */
    public UIStuff ui;
    
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
        setGrid(3,1);
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
    
    public static TempoBox buildTempoBox(UIStuff uiref)
    {
        return new TempoBox(uiref);
        
    }
    
    public static VirtualPiano buildVirtualPiano(UIStuff uiref)
    {
        return new VirtualPiano(uiref);
        
    }
    
    
    
    
    
}
