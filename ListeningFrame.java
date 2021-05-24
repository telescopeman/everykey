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
        init_listener();
    }

    private void init_listener()
    {
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                onClosed();
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent)
            {
                onClosed();
            }
        });
    }

    public ListeningFrame(String name, Dimension dimension) {
        super(name,dimension);
        init_listener();
    }

    public ListeningFrame(Dimension dimension) {
        super(dimension);
        init_listener();
    }

    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    protected abstract void act(String id);

    protected abstract void onClosed();
}
