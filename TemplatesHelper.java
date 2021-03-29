
/**
 * Contains default templates, and manages retrieval.
 *
 * @author Caleb Copeland
 * @version 3/28/21
 */
public class TemplatesHelper
{
    // instance variables - replace the example below with your own
    private static FilterTemplate[] templates = new FilterTemplate[]{
        new FilterTemplate("Caleb's Useful Scales", new Filter[]{
            new Filter("isNamed"),  //named
            new Filter(8,4), // has perfect fifth
            new Filter(new int[]{4,5},2) //major or minor tonality
        })
    };

    
    public static FilterTemplate[] getAll()
    {
        // put your code here
        return templates;
    }
}
