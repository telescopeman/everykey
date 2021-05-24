import java.util.Arrays;

/**
 * @since 5/23/21
 */
public class FilterBank {

    private static final Filter[] defaultFilters = new Filter[]{new Filter("isNamed")};
    private static Filter[] curFilters, storedFilters;
    private static boolean[] filterStatuses, storedFilterStatuses;


    public static void initialize()
    {
        curFilters = defaultFilters;
        filterStatuses = new boolean[]{true};
    }

    /**
     * Stores the current filter list in a variable.
     */
    public static void storeFilters()
    {
        storedFilters = Arrays.copyOf(curFilters,curFilters.length);
        storedFilterStatuses = Arrays.copyOf(filterStatuses,filterStatuses.length);
        UI.refresh();
    }


    /**
     * Returns any temporarily stored filters.
     */
    public static Filter[] getStoredFilters()
    {
        return storedFilters;
    }

    /**
     * Returns all currently created filters, even the ones that aren't active.
     */
    public static Filter[] getCurrentFilters()
    {
        return curFilters;
    }

    /**
     * Returns any temporarily stored filter statuses.
     */
    public static boolean[] getStoredStatuses()
    {
        return storedFilterStatuses;
    }

    /**
     * Sets the statuses of the filters.
     */
    public static void setFilterStatuses(boolean[] newThings)
    {
        filterStatuses = newThings;
    }

    /**
     * Gets the statuses of the filters.
     */
    public static boolean[] getFilterStatuses()
    {
        return filterStatuses;
    }


    /**
     * Toggles a filter's activeness.
     */
    public static void toggleFilter(int index)
    {
        boolean newSet = !filterStatuses[index];
        filterStatuses[index] = newSet;
        UI.refresh();
    }

    /**
     * Sets the list of filters.
     */
    public static void setCurFilters(Filter[] newFilters)
    {
        curFilters = newFilters;
        UI.refresh();
    }

    /**
     * Removes a filter.
     */
    public static void removeFilter(int index)
    {
        curFilters = ArrayHelper.removeOne(curFilters,index);
        filterStatuses = ArrayHelper.removeOne(filterStatuses,index);
        UI.refresh();
    }


}
