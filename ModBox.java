/**
 * Represents a box that will modify the main class through user input.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 */
public abstract class ModBox extends EasyFrame
{

    private UIStuff ui;

    /**
     * Constructor for objects of class ModBox
     */
    public ModBox(UIStuff uiref)
    {
        // initialise instance variables
        ui = uiref;
        //arr = new ArrayHelper();
        setGrid(3,1);
    }

    /**
     * Creates a ModBox without automatically assuming a grid layout.
     */
    public ModBox(UIStuff uiref, boolean t)
    {
        // initialise instance variables
        ui = uiref;
        //arr = new ArrayHelper();

    }

    /**
     * Provides access to the ModBox's link back to the UI controller.
     */
    public UIStuff getUI()
    {
        return ui;
    }

    
}
