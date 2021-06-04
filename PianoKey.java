import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.Color;

public class PianoKey extends JButton {
    private static final String[]
            WHITE_KEY_BUTTONS = new String[]{"A","S","D","F","G","H","J","K","L","SEMICOLON"},
            BLACK_KEY_BUTTONS = new String[]{"W","E","T","Y","U","O","P"};



    public int getIndex()
    {
        return index;
    }


    public final boolean isWhite;

    private final int index;
    private final VirtualPiano myVirtualPiano;

    public PianoKey(int ind,VirtualPiano myVirtualPiano)
    {
        index = ind;
        this.myVirtualPiano = myVirtualPiano;
        int j = index % 12;
        isWhite = (j == 0 || j == 2|| j == 4|| j == 5|| j == 7|| j ==9|| j == 11);
        addMouseListener(myVirtualPiano);
        setOpaque(true);

        String text_on_button;
        if (isWhite)
        {
            text_on_button = WHITE_KEY_BUTTONS[getPiano().getNumWhite()];
        }
        else
        {
            text_on_button = BLACK_KEY_BUTTONS[getPiano().getNumBlack()];
        }
        setVerticalAlignment( SwingConstants.BOTTOM );
        if (text_on_button.equals(WHITE_KEY_BUTTONS[9]))
        {
            setText(";");
        }
        else
        {
            setText(text_on_button);
        }

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed " + text_on_button), VirtualPiano.TYPED);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + text_on_button), VirtualPiano.RELEASED);

        setBackground(Color.WHITE);

        if (isWhite)
        {
            setLocation(getPiano().getNumWhite() * VirtualPiano.WHITE_WIDTH, 0);
            setSize(VirtualPiano.WHITE_WIDTH, VirtualPiano.WHITE_HEIGHT);
        }
        else
        {
            setLocation((getPiano().getNumWhite()-1)*(VirtualPiano.WHITE_WIDTH) + (VirtualPiano.BLACK_WIDTH *3/4), 0);
            setSize(VirtualPiano.BLACK_WIDTH, VirtualPiano.BLACK_HEIGHT);
        }
    }

    private VirtualPiano getPiano()
    {
        return myVirtualPiano;
    }


}
