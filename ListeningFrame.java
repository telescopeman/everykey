import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ListeningFrame extends EasyFrame implements ActionListener {

    public ListeningFrame()
    {
        super();
    }

    public ListeningFrame(String s) {
        super(s);
    }

    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    public abstract void act(String id);
}
