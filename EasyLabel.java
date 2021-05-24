import javax.swing.JLabel;
import java.awt.Font;

public class EasyLabel extends JLabel implements VariableColor
{

    public EasyLabel(String text)
    {
        super(text);
    }

    public EasyLabel(String text, int center) {
        super(text,center);
    }

    @Override
    public void doLightMode() {
        setForeground(ColorsHelper.DARK_TEXT);
    }

    @Override
    public void doDarkMode() {
        setForeground(ColorsHelper.LIGHT_TEXT);
    }

    public void italicize()
    {
        setFont(new Font(getFont().getFontName(),Font.ITALIC,getFont().getSize()));
    }

    public void embolden()
    {
        setFont(new Font(getFont().getFontName(),Font.BOLD,getFont().getSize()));
    }

    public void setFontSize(int size)
    {
        setFont(new Font(getFont().getFontName(),getFont().getStyle(),size));
    }

}
