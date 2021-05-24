
/**
 * Assures consistency between classes.
 * Makes sure two audio clips don't play at once, keeps tempo, etc.
 *
 * @author Caleb Copeland
 * @version 4/6/21
   */
public abstract class StateWatcher
{
    // instance variables - replace the example below with your own
    static private MusicPlayer curPlayer;
    static private float tempo = 140.0f;
    
    
    /**
     * Sets the tempo.
     */
    public static void setTempo(float t)
    {
        tempo = t;
    }
    
    /**
     * Returns the current global tempo.
     */
    public static float getTempo()
    {
        return tempo;
    }

    public static void requestControl(MusicPlayer src) {
        if (curPlayer != null)
        {
            curPlayer.stop();
        }
        updatePlayer(src);
        curPlayer.setTempo(tempo);
        
    }
    
    private static void updatePlayer(MusicPlayer m)
    {
        curPlayer = m;
    }

    
    private static int keyOffset = 0;
    
    public static void setOffset(int n)
    {
        keyOffset = n;
        //System.out.println("set " + n);
    }

    
    public static int getOffset()
    {
        // put your code here
        return keyOffset;
    }
}
