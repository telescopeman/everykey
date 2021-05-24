
/**
 * Contains default templates, and manages retrieval.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public abstract class TemplatesHelper
{
    // instance variables - replace the example below with your own
    private static final FilterTemplate[] templates = new FilterTemplate[]{
        new FilterTemplate("Blank", new Filter[]{}),
        new FilterTemplate("Useful Scales", new Filter[]{
            new Filter(8,4), // has perfect fifth
            new Filter(new int[]{4,5},2) //major or minor tonality
        }),
        new FilterTemplate("Beginner-Friendly Scales", new Filter[]{
            new Filter("isNamed"),  //named
            new Filter("World Scales",true),
            new Filter("Deeper Jazz Scales",true),
            new Filter("Constructed Scales",true),
            new Filter(new int[]{4,5},2) //major or minor tonality
        })
    };

    /**
     * Gets list of all templates.
     */
    public static FilterTemplate[] getAll()
    {
        // put your code here
        return templates;
    }
}
