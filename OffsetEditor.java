

/**
 * Manages root note of all scales.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 * @since 4/6/21
 */
public class OffsetEditor extends SpinnerEditor
{


    /**
     * Constructor for objects of class OffsetEditor
     */
    public OffsetEditor()
    {
        super("Change Root Note",
                 new CyclingSpinnerListModel(TheoryObj.CHROMATIC_SCALE),
                3,
                false);
    }


    @Override
    protected int getCurrentValue() {
        String val = (String) spinner.getValue();
        return ArrayHelper.search(TheoryObj.CHROMATIC_SCALE,val);
    }

    /**
     * Applies the offset of the key and refreshes the UI.
     */
    @Override
    public void apply(int curValue) {
        StateWatcher.setOffset(curValue);
        SpeedCache.setOffset(curValue);
        UI.refresh();
    }
}
