
/**
 * Controls key offsets.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 * @since 4/6/21
 */
public abstract class OffsetWatcher
{
    // instance variables - replace the example below with your own
    private static int keyOffset = 0;
    
    public static void setOffset(int n)
    {
        keyOffset = n;
        System.out.println("set " + n);
    }

    
    public static int getOffset()
    {
        // put your code here
        return keyOffset;
    }
}
