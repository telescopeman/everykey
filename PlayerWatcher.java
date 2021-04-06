
/**
 * Assures consistency between audio clips.
 * Makes sure two audio clips don't play at once, keeps tempo, etc.
 *
 * @author Caleb Copeland
 * @version 4/6/21
   */
public abstract class PlayerWatcher
{
    // instance variables - replace the example below with your own
    static private MusicHelper curPlayer;
    static private boolean isPlaying = false;
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

    public static void requestControl(MusicHelper src) 
        throws javax.sound.midi.MidiUnavailableException
    {
        if (curPlayer != null)
        {
            curPlayer.stop();
        }
        updatePlayer(src);
        //System.out.println(src);
        curPlayer.setTempo(tempo);
        
    }
    
    private static void updatePlayer(MusicHelper m)
    {
        curPlayer = m;
    }
}
