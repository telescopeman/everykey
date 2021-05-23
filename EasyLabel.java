import javax.swing.JLabel;

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
        setForeground(UIStuff.DARK_TEXT);
    }

    @Override
    public void doDarkMode() {
        setForeground(UIStuff.LIGHT_TEXT);
    }
}
