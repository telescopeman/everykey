import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Basically just a JPanel with a few extra methods.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class EasyPanel extends JPanel implements ActionListener
{

    public EasyPanel()
    {
        //do nothing
    }
    
    public EasyPanel(String name)
    {
        addHeader(name);
    }
    
    public void makeCenteredList()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addButton(String name, ActionListener trig)
    {
        EasyButton jb3 = new EasyButton(name);
        jb3.addActionListener(trig);
        add(jb3);
    }
    
    public void addHeader(String text)
    {
        EasyLabel title = new EasyLabel(text,JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        UIStuff.adjustColors(this);
    }


    
    private Dimension getDim(int x, int y)
    {
        return new Dimension(x,y);
        
    }

    public void appear()
    {
        setVisible(true);
        requestFocusInWindow();


    }


    public void appear(Dimension dim)
    {
        appear();
        setSize(dim);

    }
    
    public void clear()
    {
        for(Component child : getComponents())
        {
            remove(child);
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    /**
     * If not overridden, assumes no-arg method should be used.
     */
    public void act(String id)
    {
        act();
    }
    
    
    public void act()
    {
        
    }
}
