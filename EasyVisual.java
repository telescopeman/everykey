import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
/**
 * Write a description of interface EasyVis here.
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
