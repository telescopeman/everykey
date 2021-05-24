import javax.swing.*;

public class EasyButton extends JButton implements VariableColor {
    public EasyButton(String str, float alignment) {
        super(str);
        setAlignmentY(alignment);
        setOpaque(false);
        //setContentAreaFilled();
        setBorderPainted(true);
        setBackground(ColorsHelper.LIGHT_MODE);
    }

    @Override
    public void doLightMode() {
        setBackground(ColorsHelper.LIGHT_MODE);
        //setForeground(UIStuff.DARK_TEXT);
    }

    @Override
    public void doDarkMode() {
        setBackground(ColorsHelper.DARK_MODE);
    }


}
