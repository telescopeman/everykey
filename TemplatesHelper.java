
/**
 * Write a description of class TemplatesHelper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TemplatesHelper
{
    // instance variables - replace the example below with your own
    private static FilterTemplate[] templates = new FilterTemplate[]{
        new FilterTemplate("Useful Scales", new Filter[]{
            new Filter("isNamed"),  //named
            new Filter(8,4), //has perfect fifth at correct place
            new Filter(new int[]{4,5},2) //major or minor
        })
    };

    
    public static FilterTemplate[] getAll()
    {
        // put your code here
        return templates;
    }
}
