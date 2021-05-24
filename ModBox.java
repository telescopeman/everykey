import java.awt.Dimension;

/**
 * Represents a box that will modify the main class through user input.
 *
 * @author Caleb Copeland
 * @version 5/24/21
 */
public abstract class ModBox extends ListeningFrame
{

    /**
     * Creates a ModBox with a default grid layout of 3x1.
     */
    public ModBox()
    {
        setGrid(3,1);
    }

    /**
     * Creates a ModBox with a custom grid layout.
     */
    public ModBox(int x, int y)
    {
        setGrid(x,y);
    }

    public ModBox(Dimension dimension) {
        super(dimension);
    }

    /**
     * Applies whatever modifications the ModBox is designed for to the program.
     */
    protected abstract void apply();

}
