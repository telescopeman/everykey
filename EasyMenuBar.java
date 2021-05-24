import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class EasyMenuBar extends JMenuBar implements VariableColor {
    Color bgColor= Color.BLUE;

    public EasyMenuBar()
    {
        super();
        setOpaque(true);
    }

    @Override
    public void doLightMode() {
        bgColor = ColorsHelper.LIGHT_MODE;
        setForeground(ColorsHelper.DARK_TEXT);
    }

    @Override
    public void doDarkMode() {
        bgColor = ColorsHelper.DARK_MODE;
        setForeground(ColorsHelper.LIGHT_TEXT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        UI.adjustColors(this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
