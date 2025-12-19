import java.util.List;

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