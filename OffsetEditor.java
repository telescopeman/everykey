import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;

//import javax.swing.JComponent;

/**
 * Manages root note.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 * @since 4/6/21
 */
public class OffsetEditor extends EasyPanel
{
    // instance variables - replace the example below with your own
    private int offset;
    //public final 
    /**
     * Constructor for objects of class OffsetEditor
     */
    public OffsetEditor()
    {
        
        super("Change Key");
        offset = 0;
        CyclingSpinnerListModel mdl = new CyclingSpinnerListModel(TheoryObj.CHROMATICSCALE);
        JSpinner spinner = new JSpinner((mdl));
        add(spinner);
        
    }

    
    
    public int getOffset()
    {
        // put your code here
        return offset;
    }
}
