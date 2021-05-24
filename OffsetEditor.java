import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeListener;

/**
 * Manages root note.
 *
 * @author Caleb Copeland, GingerHead [setWidth() method only]
 * @version 5/24/21
 * @since 4/6/21
 */
public class OffsetEditor extends EasyPanel
{
    private final JSpinner spinner;

    /**
     * Constructor for objects of class OffsetEditor
     */
    public OffsetEditor()
    {
        super("Change Root Note");
        CyclingSpinnerListModel mdl = new CyclingSpinnerListModel(TheoryObj.CHROMATIC_SCALE);
        spinner = new JSpinner(mdl);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        setWidth(2);
        add(spinner);

        ChangeListener listener = e -> {
            String val = (String) spinner.getValue();
            int currentOffset = ArrayHelper.search(TheoryObj.CHROMATIC_SCALE,val);
            if (currentOffset < 0)
            {
                throw new IllegalArgumentException("Illegal selection "+ currentOffset);
            }
            else
            {
                StateWatcher.setOffset(currentOffset);
                SpeedCache.setOffset(currentOffset);
                UI.refresh();
            }
        };
        
        spinner.addChangeListener(listener);
    }

    public void setWidth(int w)
    {
        JSpinner.DefaultEditor mySpinnerEditor = (JSpinner.DefaultEditor) spinner.getEditor();
        JFormattedTextField jFormattedTextField = mySpinnerEditor.getTextField();
        jFormattedTextField.setColumns(w);
    }
}
