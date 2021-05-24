import javax.swing.*;
import javax.swing.event.ChangeListener;

/**
 * @author Caleb Copeland, GingerHead [setWidth() method only]
 * @since 5/24/21
 * @version 5/24/21
 */
public abstract class SpinnerEditor extends EasyPanel {

    protected final JSpinner spinner;
    protected final SpinnerModel model;

    public SpinnerEditor(String text, SpinnerModel model, int width, boolean isTextEditable)
    {
        super(text);
        this.model = model;
        spinner = new JSpinner(model);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(isTextEditable);
        setWidth(width);
        add(spinner);

        ChangeListener listener = e -> {
            int currentOffset = getCurrentValue();
            if (currentOffset < 0)
            {
                throw new IllegalArgumentException("Illegal selection "+ currentOffset);
            }
            else
            {
                apply(currentOffset);
            }
        };

        spinner.addChangeListener(listener);
    }

    protected abstract int getCurrentValue();


    public void setWidth(int w)
    {
        JSpinner.DefaultEditor mySpinnerEditor = (JSpinner.DefaultEditor) spinner.getEditor();
        JFormattedTextField jFormattedTextField = mySpinnerEditor.getTextField();
        jFormattedTextField.setColumns(w);
    }

    public abstract void apply(int curValue);



}
