import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Basically just a JPanel with a few extra methods.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class EasyPanel extends JPanel
{
    public EasyPanel()
    {
        //do nothing
    }
    
    public EasyPanel(String name)
    {
        addHeader(name);
    }
    
    public void addButton(String name, ActionListener trig)
    {
        EasyButton jb3 = new EasyButton(name, CENTER_ALIGNMENT);
        jb3.addActionListener(trig);
        add(jb3);
    }
    
    public void addHeader(String text)
    {
        EasyLabel title = new EasyLabel(text,JLabel.CENTER);
        title.embolden();
        add(title);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        UI.adjustColors(this);
    }

}
