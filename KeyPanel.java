import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays a scale's name and info.
 *
 * @author Caleb Copeland
 * @version 5/23/21
 */
public class KeyPanel extends EasyPanel implements LowerBucketCrab
{
    private MusicPlayer musicPlayer;
    public static final String PLAY_TEXT = "Listen";
    public static final String SAMPLE_TEXT = "Sampler";

    /**
     * Constructor for objects of class KeyPanel
     */
    public KeyPanel(int num, int[] key, String name)
    {

        super();
        String lbl = "#" + String.format("%03d",num)  //this just means to make sure it has three digits
            + ": " + StringHelper.filterOutTags(name);
            
        ChordViewer chordViewer = new ChordViewer(key,name);
        try{
            musicPlayer = new MusicPlayer(key,this);
        }
        catch(Exception ed) {
            ed.printStackTrace();
        }

        EasyButton b = new EasyButton("Info", CENTER_ALIGNMENT);
        JPopupMenu jb5 = new JPopupMenu();
        JMenuItem ch = new JMenuItem("Chords");
        ch.addActionListener(chordViewer);
        JMenuItem intervals = new JMenuItem("Intervals");
        intervals.addActionListener(new Infobox(key,name,"Intervals"));
        JMenuItem sampler = new JMenuItem(SAMPLE_TEXT);
        sampler.addActionListener(new Sampler(key,name));
        jb5.add(ch); jb5.add(intervals); jb5.add(sampler);

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

        MouseListener popupListener = new PopupListener();
        b.addMouseListener(popupListener);

        EasyLabel label = new EasyLabel(lbl);
        int[] pt = StringHelper.getEnclosers(name,"[]");
        if (pt[0] > -1 && pt[1] > -1) // makes it so you can hover for modal info.
        {
            label.setToolTipText(name.substring(pt[0]+1,pt[1]));
        }
        
        if (StringHelper.filterOutTags(name).startsWith("Unnamed Key"))
        {
            label.italicize();
        }
        add(label);
        add(b); 
        addButton(PLAY_TEXT, musicPlayer);
    }


}
