import javax.swing.*;
import java.awt.Dimension;
/**
 * Write a description of class UIStuff here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UIStuff
{
    // instance variables - replace the example below with your own
    
    JFrame myWindow;
    /**
     * Constructor for objects of class UIStuff
     */
    public UIStuff()
    {
        myWindow = new JFrame("SkeletonKey");
        //myWindow.pack();
        myWindow.setSize(new Dimension(400, 1000));
        //myWindow.setVisible(true);
        MathHelper.getAllKeys();
        

    }

    
}
