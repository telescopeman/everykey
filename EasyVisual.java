import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
/**
 * Helps with several visual methods for JFrames and JPanels.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface EasyVisual
{
    
    void addButton(String name, ActionListener trig);
    
    void appear();
    
    void appear(Dimension dim);
    
    public void clear();
}
