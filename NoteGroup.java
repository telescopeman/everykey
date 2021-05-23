import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

public class NoteGroup extends TheoryObj implements Iterable<Integer> {

    protected final int[] notes;
    public final int length;

    public NoteGroup(int[] notes)
    {
        this.notes = notes;
        length = notes.length;
    }

    public NoteGroup(int note)
    {
        this.notes = new int[]{note};
        length = 1;
    }

    public int[] getNotes()
    {
        return notes;
    }

    public int getNote(int index)
    {
        return notes[index];
    }

    /**
     * Takes the notes of a scale and expands them into a list of the notes in words.
     * @param enharmonicsOn Whether the program should prioritize proper forms of notes so that each letter is used only once.
     */
    public String expand(boolean enharmonicsOn)
    {
        //System.out.println("Exp");
        int[] key2 = new int[notes.length];
        int c = 0;
        for(int i : notes)
        {
            key2[c] = ((i-1)%12)+1 ;
            c++;
        }
        String name = "";
        String[] rawNotes = expandRaw(key2,enharmonicsOn);

        int count3 = 0;
        for (String newNote : rawNotes)
        {
            name = name + newNote;
            if (count3 < notes.length - 2) // most
            {
                name = name + ", ";
            }
            else if (count3 == notes.length - 2) //second to last
            {
                name = name + ", and ";
            }
            else // last
            {
                name = name + ".";
            }

            count3++;
        }
        return name;
    }

    private static String[] expandRaw(int[] key, boolean enharmonicsOn)
    {
        String[] rawNotes = new String[7];
        for(int count = 0; count < key.length; count++)
        {
            int i = key[count];
            String newNote = getNoteName(i);
            rawNotes[count] = newNote;
        }

        if (enharmonicsOn)
        {
            return doEnharmonics(rawNotes);
        }
        else {
            return rawNotes;
        }
    }

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(notes).iterator();
    }
}
