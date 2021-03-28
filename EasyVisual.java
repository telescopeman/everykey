import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
/**
 * Helps with several visual methods for JFrames and JPanels.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public interface EasyVisual
{
    
    void addButton(String name, ActionListener trig);
    
    void appear();
    
    void appear(Dimension dim);
    
    public void clear();
}
