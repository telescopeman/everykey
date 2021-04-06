import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Manages root note.
 *
 * @author Caleb Copeland, GingerHead [setWidth() method only]
 * @version 4/6/21
 * @since 4/6/21
 */
public class OffsetEditor extends EasyPanel
{
    // instance variables - replace the example below with your own
    private int offset;
    private JSpinner spinner;
    private final int WIDTH = 3;

    private final String[] SCALE = TheoryObj.CHROMATICSCALE;

    /**
     * Constructor for objects of class OffsetEditor
     */
    public OffsetEditor(UIStuff ui)
    {
        super("Change Key");

        offset = 0;
        CyclingSpinnerListModel mdl = new CyclingSpinnerListModel(TheoryObj.CHROMATICSCALE);
        spinner = new JSpinner((mdl));
        setWidth(WIDTH);
        add(spinner);

        ChangeListener listener = new ChangeListener()
        {
                public void stateChanged(ChangeEvent e) {
                    String val = (String) spinner.getValue();
                    //System.out.println(val);
                    int n = ArrayHelper.search(SCALE,val);
                    if (n < 0)
                    {
                        throw new IllegalArgumentException("Illegal selection "+ n);
                    }
                    else
                    {
                        offset = n;
                        OffsetWatcher.setOffset(n);
                        ui.setOffset(n);
                    }
                }
            };
        
        spinner.addChangeListener(listener);
    }

    public void setWidth(int w)
    {
        JComponent mySpinnerEditor = spinner.getEditor();
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
        jftf.setColumns(w);
    }

    public int getOffset()
    {
        return offset;
    }

    public void act()
    {
       System.out.println("nice");
    }
}
