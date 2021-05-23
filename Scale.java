import java.security.Key;

public class Scale extends NoteGroup {


    public String[] name_with_enharmonics = new String[12];
    public String[] name_without_enharmonics = new String[12];

    public Scale(int[] notes) {
        super(notes);
    }

    public String getShortName()
    {
        KeyNamesHelper.initialize();
        String str = expandSmart(0, false);
        String name = KeyNamesHelper.get(this);
        System.out.println(str);
        if (name == null)
        {
            return "Unnamed Key";
        }
        return name;
    }

    public String getName(int offset, boolean enharmonicsOn)
    {
        if(enharmonicsOn)
        {
            if(name_with_enharmonics[offset]==null)
            {
                String name = getShortName();
                //String notes_as_string = expandSmart(offset, true);

                //name_with_enharmonics[offset] = name + " (" + notes_as_string + ")";
                return name;

            }
            return name_with_enharmonics[offset];
        }
        else
        {
            if(name_without_enharmonics[offset]==null)
            {
                String name = getShortName();
                String notes_as_string = expandSmart(offset,false);

                name_without_enharmonics[offset] = name + " (" + notes_as_string + ")";
            }
            return name_without_enharmonics[offset];
        }
        //this is inefficient. not sure how to fix this.
    }




    /**
     * Checks to see if a certain index of the cache is used or not.
     * @param enh Whether the description being cached is taking enharmonics into account.
     */
    public boolean needsCache(int ind, boolean enh)
    {

        if (enh)
        {
            return (null == name_with_enharmonics[ind]);
        }
        else
        {
            return (null == name_without_enharmonics[ind]);
        }
    }

    public String expandSmart(int ind, boolean enh)
    {
        if (needsCache(ind,enh))
        {
            String str = expand(enh);
            if (enh)
            {
                name_with_enharmonics[ind] = str;
            }
            else
            {
                name_without_enharmonics[ind] = str;
            }
            return str;
        }
        else
        {
            if (enh)
            {
                return name_with_enharmonics[ind];
            }
            else
            {
                return name_without_enharmonics[ind];
            }
        }
    }




    public String[] getTags()
    {
        return new String[]{""};
    }

    public boolean validityCheck()
    {
        return notes[0] != 0;
    }

}
