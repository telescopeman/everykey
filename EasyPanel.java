import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
/**
 * Basically just a JPanel with a few extra methods.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class EasyPanel extends JPanel
{

    public EasyPanel()
    {
        //do nothing
    }
    
    public EasyPanel(String name)
    {
        // initialise instance variables
        addHeader(name);
    }

    public void addButton(String name, ActionListener trig)
    {
        JButton jb3 = new JButton(name);    
        jb3.addActionListener(trig);
        add(jb3);
    }
    
    public void addHeader(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);
        
    }
    
    private Dimension getDim(int x, int y)
    {
        return new Dimension(x,y);
        
    }

    public void appear()
    {
        show();
        requestFocusInWindow();

    }
    public void appear(Dimension dim)
    {
        show();
        requestFocusInWindow();
        setSize(dim);

    }
    
    public void clear()
    {
        for(Component child : getComponents())
        {
            remove(child);
        }
    }
}
