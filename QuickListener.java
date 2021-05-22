import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A fast version of ActionListener I like.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public abstract class QuickListener implements ActionListener
{
    
    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    /**
     * If this is not overridden, assumes the no-arg method should be used.
     */
    public abstract void act(String id);
}
