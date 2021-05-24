public enum SortOption {
    Brightness_Ascending("Brightness (Ascending)"),
    Brightness_Descending("Brightness (Descending)"),
    Strangeness_Ascending("Strangeness (Ascending)"),
    Strangeness_Descending("Strangeness (Descending)"),
    Intervalic_Oddities_Ascending("Interval Oddness (Ascending)"),
    Intervalic_Oddities_Descending("Interval Oddness (Descending)");

    private final String string;

    // constructor to set the string
    SortOption(String name){string = name;}

    @Override
    public String toString()
    {
        return string;
    }
}
