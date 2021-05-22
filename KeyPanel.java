import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font; 

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays a scale's name and info.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 */
public class KeyPanel extends EasyPanel
{
    
    private MusicHelper playr;
    private Popup p;

    


    /**
     * Filters out tags.
     */
    public static String parse(String name)
    {
        int[] pt = StringHelper.getEnclosers(name,"[]");
        String dispName;
        if (pt[0] > -1)
        {
            dispName =  StringHelper.quickSubstring(name,pt);
        }
        else
        {
            dispName =  name;
        }
        for(boolean i = true; i;)
        {
            pt = StringHelper.getEnclosers(dispName,"{}");
            i = false;
            if (pt[0] > -1 && pt[1] > -1)
            {
                dispName = StringHelper.quickSubstring(dispName,pt); 
                i = true;
            }
        }
        return dispName;
    }

    

    /**
     * Constructor for objects of class KeyPanel
     */
    public KeyPanel(int num, int[] key, String name)
    {

        super();
        String lbl = "#" + String.format("%03d",num)  //this just means to make sure it has three digits
            + ": " + parse(name);
            
        ChordViewer chrds = new ChordViewer(key,name);

        JButton jb2 = new JButton("Intervals");
        try{
            playr = new MusicHelper(key);
        }
        catch(Exception ed) {
            ed.printStackTrace();
        }
        JButton b = new JButton("Info");
        JPopupMenu jb5 = new JPopupMenu();
        //JMenu jb5 = new JMenu("Info");
        JMenuItem ch = new JMenuItem("Chords");
        ch.addActionListener(chrds);
        JMenuItem intervals = new JMenuItem("Intervals");
        intervals.addActionListener(new Infobox(key,name,"Intervals"));
        JMenuItem sampler = new JMenuItem("Sampler");
        sampler.addActionListener(new Sampler(key,name));
        jb5.add(ch); jb5.add(intervals); jb5.add(sampler); 
        //jb5.add(ch); 

        class PopupListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                jb5.show(e.getComponent(),
                        e.getX(), e.getY());
                
            }
        }
        //jb5.addActionListener(new Infobox(key,name));
        MouseListener popupListener = new PopupListener();
        //output.addMouseListener(popupListener);
        b.addMouseListener(popupListener);

        //Popup p = infoPanel();
        JLabel label = new JLabel(lbl);
        int[] pt = StringHelper.getEnclosers(name,"[]");
        if (pt[0] > -1 && pt[1] > -1) // makes it so you can hover for modal info.
        {
            label.setToolTipText(name.substring(pt[0]+1,pt[1]));
        }
        
        if (parse(name).substring(0,11).equals("Unnamed Key"))
        {
            label.setFont(new Font(label.getFont().getFontName(),Font.ITALIC,12));
        }
        add(label);
        add(b); 
        addButton("Listen",playr); 
    }

    private Popup infoPanel()
    {
        EasyPanel submenu = new EasyPanel("Info");
        PopupFactory pf = new PopupFactory();
        p = pf.getPopup(this, submenu,0,0);
        return p;
    }

    
    
    public void act(String d)
    {
        if (d.equals("Info"))
        {
            p.show();
        }
    }
}
