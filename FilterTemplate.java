
/**
 * Represents a template of a filter list with a name and a group of filters.
 *
 * @author Caleb Copeland
 * @version 3/31/21
 */
public class FilterTemplate
{
    // instance variables - replace the example below with your own
    private Filter[] filters;
    private String name;
    
    
    /**
     * Creates a FilterTemplate with a name and a filterbank.
     */
    public FilterTemplate(String n, Filter[] f)
    {
        // initialise instance variables
        name = n;
        filters = f;
    }

    
    /**
     * Gets the filters from the template.
     */
    public Filter[] getFilters()
    {
        // put your code here
        return filters;
    }
    
    
    /**
     * Gets the name of the template.
     */
    public String getName()
    {
        // put your code here
        return name;
    }
}
