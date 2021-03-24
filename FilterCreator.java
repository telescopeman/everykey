import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
/**
 * Write a description of class FilterCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FilterCreator extends JFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    String type;

    
    
    
    /**
     * Constructor for objects of class ChordViewer
     */
    public FilterCreator(String kind)
    {
        type = kind;
        JLabel title = new JLabel("Filter Designer - " + kind, JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);
        setSize(new Dimension(350, 100));
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MenuButton m = new MenuButton("Eats");
        m.getItems().addAll(new MenuItem("Burger"), new MenuItem("Hot Dog"));
        
        
    }

 
     
    public void actionPerformed(ActionEvent e) {
        show();
        //System.out.println(e);
}
}
