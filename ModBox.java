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
    
    public final String[] CHROMATICSCALE = new String[]{"C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B"};
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
    
    public static Filter[] addX(Filter[] list, Filter x)
    {
        int i;

        // create a new array of size n+1
        Filter[] newarr = new Filter[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }
    
    public static boolean[] addX(boolean[] list, boolean x)
    {
        int i;

        // create a new array of size n+1
        boolean[] newarr = new boolean[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }
    
    public static Object[] addX(Object[] list, Object x)
    {
        int i;

        // create a new array of size n+1
        Object[] newarr = new Object[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }
}
