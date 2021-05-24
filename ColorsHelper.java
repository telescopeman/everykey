import java.awt.Color;

/**
 * Does some things with colors.
 *
 * @author Caleb Copeland
 * @since 5/23/21
 * @version 5/23/21
 */
public abstract class ColorsHelper
{
    public static final Color LIGHT_MODE = Color.LIGHT_GRAY,
            DARK_MODE = ColorsHelper.soften(Color.DARK_GRAY,5),
            LIGHT_TEXT = Color.WHITE,
            DARK_TEXT = Color.BLACK;
            


    /**
     * Makes a color slightly less vibrant.
     */
    public static Color soften(Color color, int times) {
        if (times < 1)
        {
            throw new IllegalArgumentException("Times cannot be lower than 1");
        }
        else if (times == 1)
        {
            return soften(color);
        }
        else
        {
            return soften(soften(color),times-1);
        }
    }
    

    /**
     * Makes a color slightly less vibrant.
     */
    public static Color soften(Color color)
    {
        float r = color.getRed()/255f;
        float g = color.getGreen()/255f;
        float b = color.getBlue()/255f;
        float m = 0.9f;
        return new Color(m*r,m*g,m*b);
    }
}
