import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A simple class just designed to speed things up a bit in some parts of code.
 * @since 5/22/21
 * @version 5/22/21
 */
public class ActionItem extends JMenuItem {

    public ActionItem(ActionListener listener)
    {
        super();
        addActionListener(listener);
    }

    public ActionItem(ActionListener listener, String str)
    {
        super(str);
        addActionListener(listener);
    }

    public ActionItem(FilterCreationSetting setting)
    {
        super();
        addActionListener(new FilterCreator(setting));
    }

    public ActionItem(FilterCreationSetting setting, String str)
    {
        super(str);
        addActionListener(new FilterCreator(setting));
    }


}
