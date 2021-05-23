import javax.swing.*;
import java.awt.*;

public class EasyMenuBar extends JMenuBar implements VariableColor {
    Color bgColor= Color.BLUE;

    @Override
    public void doLightMode() {
        bgColor = UIStuff.LIGHT_MODE;
        setForeground(UIStuff.DARK_TEXT);
    }

    @Override
    public void doDarkMode() {
        bgColor = UIStuff.DARK_MODE;
        //setBackground(UIStuff.DARK_MODE);
        setForeground(UIStuff.LIGHT_TEXT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        UIStuff.adjustColors(this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
