import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClosed();
            }
        });
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

    protected void onClosed()
    {

    }
}
