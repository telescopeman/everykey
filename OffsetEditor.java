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
    private int offset = 0;
    private final JSpinner spinner;

    /**
     * Constructor for objects of class OffsetEditor
     */
    public OffsetEditor()
    {
        super("Change Key");
        CyclingSpinnerListModel mdl = new CyclingSpinnerListModel(TheoryObj.CHROMATIC_SCALE);
        spinner = new JSpinner(mdl);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        setWidth(2);
        add(spinner);

        ChangeListener listener = e -> {
            String val = (String) spinner.getValue();
            int n = ArrayHelper.search(TheoryObj.CHROMATIC_SCALE,val);
            if (n < 0)
            {
                throw new IllegalArgumentException("Illegal selection "+ n);
            }
            else
            {
                offset = n;
                StateWatcher.setOffset(n);
                SpeedCache.setOffset(n);
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

    /**
     * Gets the offset of the current root note. C = 0 and D = 2, and so on.
     */
    public int getOffset()
    {
        return offset;
    }
}
