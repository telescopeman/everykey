import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 
import java.util.Arrays;
/**
 * Write a description of class ModBox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class ModBox extends EasyFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    UIStuff ui;
    public final Dimension STANDARD = new Dimension(350,100);
    /**
     * Constructor for objects of class ModBox
     */
    public ModBox(UIStuff uiref)
    {
        // initialise instance variables
        ui = uiref;
        setLayout(new GridLayout(3,1));
    }

    public void addHeader(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);
        
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        clear();
        appear(STANDARD);
    }
}
