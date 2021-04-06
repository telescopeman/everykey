import javax.swing.SpinnerListModel;
import javax.swing.JComponent;

/**
 * Manages root note.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 * @since 4/6/21
 */
public class OffsetEditor extends CyclingSpinnerListModel
{
    // instance variables - replace the example below with your own
    private int offset;

    
    //public final 
    /**
     * Constructor for objects of class OffsetEditor
     */
    public OffsetEditor()
    {
        //final String[] CHROMATICSCALE = ;
        // initialise instance variables
        super(TheoryObj.CHROMATICSCALE);
        //offset = 0;
        
    }

    
    
    public int getOffset()
    {
        // put your code here
        return offset;
    }
}
