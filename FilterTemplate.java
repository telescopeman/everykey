
/**
 * Write a description of class FilterTemplate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FilterTemplate
{
    // instance variables - replace the example below with your own
    private Filter[] filters;
    private String name;
    
    /**
     * Constructor for objects of class FilterTemplate
     */
    public FilterTemplate(String n, Filter[] f)
    {
        // initialise instance variables
        name = n;
        filters = f;
    }

    

    public Filter[] getFilters()
    {
        // put your code here
        return filters;
    }
    
    public String getName()
    {
        // put your code here
        return name;
    }
}
