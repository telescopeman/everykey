import javax.swing.*;

public class EasyButton extends JButton implements VariableColor {
    public EasyButton(String str) {
        super(str);
        setOpaque(false);
        //setContentAreaFilled();
        setBorderPainted(true);
        setBackground(UIStuff.LIGHT_MODE);
    }

    @Override
    public void doLightMode() {
        setBackground(UIStuff.LIGHT_MODE);
        //setForeground(UIStuff.DARK_TEXT);
    }

    @Override
    public void doDarkMode() {
        setBackground(UIStuff.DARK_MODE);
    }


}
