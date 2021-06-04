import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeListener;

/**
 * @author Caleb Copeland, GingerHead [setWidth() method only]
 * @since 5/24/21
 * @version 6/4/21
 */
public abstract class SpinnerEditor extends EasyPanel {

    protected final JSpinner spinner;

    public SpinnerEditor(String text, SpinnerModel model, int width, boolean isTextEditable)
    {
        super(text);
        spinner = new JSpinner(model);
        JFormattedTextField textField = ((JSpinner.DefaultEditor) (spinner.getEditor())).getTextField();

        // sets whether the text is editable or not.
        textField.setEditable(isTextEditable);

        // Sets the width of the text field:
        textField.setColumns(width);

        add(spinner);

        ChangeListener listener = e -> {
            int currentOffset = getCurrentValue();
            if (currentOffset < 0)
            {
                throw new IllegalArgumentException("Illegal selection of "+ currentOffset);
            }
            else
            {
                apply(currentOffset);
            }
        };

        spinner.addChangeListener(listener);
    }

    protected abstract int getCurrentValue();

    protected abstract void apply(int curValue);

}
