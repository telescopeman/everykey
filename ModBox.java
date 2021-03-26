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
    public final Dimension SUPERSTANDARD = new Dimension(550,150);
    ArrayHelper arr;
    
    public final String[] CHROMATICSCALE = new String[]{"C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B"};
    /**
     * Constructor for objects of class ModBox
     */
    public ModBox(UIStuff uiref)
    {
        // initialise instance variables
        ui = uiref;
        arr = new ArrayHelper();
        setLayout(new GridLayout(3,1));
    }
    
    
    public static FilterCreator buildFilterCreator(UIStuff uiref)
    {
        return new FilterCreator(uiref);
        
    }
    
    public static SettingsBox buildSettingsBox(UIStuff uiref)
    {
        return new SettingsBox(uiref);
        
    }
    
    // /**
     // * Gets a child of ModBox.
     // */
    // private ModBox makeModBox(String type)
    // {
        // switch (type)
        // {
            // case "FilterCreator":
                // return new FilterCreator(this);
            // case "AudioSpeed":
                // return new SettingsBox(this);
            // default:
                // return new FilterCreator(this);
        // }
    // }

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
