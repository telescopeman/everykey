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
    int[][] masterList;
    JFrame mainWindow;
    /**
     * Constructor for objects of class UIStuff
     */
    public UIStuff()
    {
        mainWindow = new JFrame("SkeletonKey");
        //myWindow.pack();
        mainWindow.setSize(new Dimension(400, 1000));
        //myWindow.setVisible(true);
        masterList = MathHelper.getAllKeys();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.show();
    }

    
}
