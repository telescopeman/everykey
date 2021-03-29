import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays a scale's name and info.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class KeyPanel extends EasyPanel implements ActionListener
{
    // instance variables - replace the example below with your own

    MusicHelper playr;
    Popup p;

    public void resize(int num, int dummy) //sets tempo throws javax.sound.midi.MidiUnavailableException
    {
        try
        {
            playr.setTempo(num);
        }
        catch (javax.sound.midi.MidiUnavailableException mue)
        {
            mue.printStackTrace();
        }
    }

    public static int[] getEnclosers(String str, String special)
    {
        if (! (special.length() == 2))
        {
            return new int[]{-1,-1};

        }
        return new int[]{str.indexOf(special.substring(0,1)),str.indexOf(special.substring(1,2))};

    }

    /**
     * Filters out tags
     */
    public static String parse(String name)
    {
        int[] pt = getEnclosers(name,"[]");
        String dispName;
        if (pt[0] > -1)
        {
            dispName =  quickSubstring(name,pt);
        }
        else
        {
            dispName =  name;
        }
        for(boolean i = true; i == true;)
        {
            pt = getEnclosers(dispName,"{}");
            i = false;
            if (pt[0] > -1 && pt[1] > -1)
            {
                dispName = dispName.substring(0,pt[0]) 
                + dispName.substring(pt[1]+1); //name + notes
                i = true;
            }

        }
        return dispName;

    }

    private static String quickSubstring(String name, int[] pts)
    {
        String newName =  name.substring(0,pts[0]-1) + name.substring(pts[1]+1);
        return newName;

    }

    /**
     * Constructor for objects of class KeyPanel
     */
    public KeyPanel(int num, int[] key, String name)
    {

        super();
        String lbl = "#" + String.format("%03d",num) + ": " + parse(name);
        ChordViewer chrds = new ChordViewer(key,name);

        JButton jb2 = new JButton("Intervals");
        try{
            playr = new MusicHelper(key);
        }
        catch(Exception ed)
        {

        }
        //jb3.addActionListener(playr);
        JButton b = new JButton("Info");
        JPopupMenu jb5 = new JPopupMenu();
        //JMenu jb5 = new JMenu("Info");
        JMenuItem ch = new JMenuItem("Chords");
        ch.addActionListener(chrds);
        JMenuItem intervals = new JMenuItem("Intervals");
        intervals.addActionListener(new Infobox(key,name,"Intervals"));
        jb5.add(ch); jb5.add(intervals); 
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
        int[] pt = getEnclosers(name,"[]");
        if (pt[0] > -1 && pt[1] > -1)
        {
            label.setToolTipText(name.substring(pt[0]+1,pt[1]));
        }
        //System.out.println(key.toString());
        //System.out.println(parse(name).substring(0,11));
        if (parse(name).substring(0,11).equals("Unnamed Key"))
        {
            label.setFont(new Font(label.getFont().getFontName(),Font.ITALIC,12));
        }
        add(label);
        add(b); 
        addButton("Listen",playr); 
    }

    public Popup infoPanel()
    {
        EasyPanel submenu = new EasyPanel("Info");
        PopupFactory pf = new PopupFactory();
        p = pf.getPopup(this, submenu,0,0);
        return p;
    }

    public void actionPerformed(ActionEvent e)
    {
        String d = e.getActionCommand();
        if (d.equals("Info"))
        {
            p.show();
        }
    }
}
