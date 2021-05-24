
/**
 * Abstract class NoteIdentifier - write a description of the class here
 *
 * @author Caleb Copeland
 * @version 5/23/21
 * @since 5/23/21
 */
public abstract class BasicTheoryObj
{
    private static String[] noteNames = new String[]{"Null","C","D♭","D","E♭","E","F","G♭","G","A♭","A","B♭","B","C"};

    protected static String getNoteName(int ind)
    {
        int index = ind + StateWatcher.getOffset();
        if (index > 13)
        {
            return getNoteNameHelper(index-12);

        }
        else if (index <= 0)
        {
            return getNoteNameHelper(index+12);
        }
        else
        {
            return noteNames[index];
        }
    }

    private static String getNoteNameHelper(int ind) //Assumes root note is C.
    {

        if (ind > 13)
        {
            return getNoteNameHelper(ind-12);

        }
        else if (ind <= 0)
        {
            return getNoteNameHelper(ind+12);
        }
        else
        {
            return noteNames[ind];
        }
    }
}
