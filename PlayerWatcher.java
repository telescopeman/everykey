
/**
 * Makes sure two audio clips don't play at once.
 *
 * @author Caleb Copeland
 * @version 3/28/21
   */
public class PlayerWatcher
{
    // instance variables - replace the example below with your own
    static private MusicHelper curPlayer;
    static private boolean isPlaying = false;
    static private int tempo;
    
    public static boolean getStatus()
    {
        return isPlaying;
    }
    
    public static void setTempo(int t)
    {
        tempo = t;
    }
    
    public static void updateStatus(boolean isOn)
    {
        isPlaying = isOn;
    }

    public static void requestControl(MusicHelper src)
    {
        if (curPlayer != null)
        {
            curPlayer.stop();
        }
        curPlayer = src;
        try
        {
            curPlayer.setTempo(tempo);
        }
        catch (javax.sound.midi.MidiUnavailableException mue)
        {
            mue.printStackTrace();
            System.out.println("Err");
        }
    }
    
    public static void updatePlayer(MusicHelper m)
    {
        curPlayer = m;
    }
}
