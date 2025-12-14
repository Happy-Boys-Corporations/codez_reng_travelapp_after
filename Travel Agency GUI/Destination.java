import java.util.List;

/**
 * A data class to hold all information for a travel destination,
 * including its name and a list of available packages.
 */
public class Destination {
    private final String name;
    private final List<PackageInfo> packages;
    private final String currencySymbol;

    public Destination(String name, List<PackageInfo> packages, String currencySymbol) {
        this.name = name;
        this.packages = packages;
        this.currencySymbol = currencySymbol;
    }

    public String getName() { return name; }

    public List<PackageInfo> getPackages() { return packages; }

    public String getCurrencySymbol() { return currencySymbol; }
}