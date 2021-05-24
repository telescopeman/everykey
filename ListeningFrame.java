import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @since 5/23/21
 * @version 5/24/21
 */
public abstract class ListeningFrame extends EasyFrame implements ActionListener {

    public ListeningFrame()
    {
        super();
    }

    public ListeningFrame(String s) {
        super(s);
    }

    public ListeningFrame(String name, Dimension dimension) {
        super(name,dimension);
    }

    public ListeningFrame(Dimension dimension) {
        super(dimension);
    }

    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    protected abstract void act(String id);
}
