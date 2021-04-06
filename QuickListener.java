import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Write a description of class QuickListener here.
 *
 * @author Caleb Copeland
 * @version 4/6/21
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
    public void act(String id)
    {
        act();
    }
    
    public void act()
    {

    }
}
