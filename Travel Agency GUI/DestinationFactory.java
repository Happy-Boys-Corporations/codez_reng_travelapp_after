import java.util.Arrays;
import java.util.List;

/**
 * A factory class to create pre-defined Destination objects.
 * This centralizes the data for all tour packages.
 */
public class DestinationFactory {

    private static final String CURRENCY = "$";

    private static List<PackageInfo> createStandardDomesticPackages(int cost1, int cost2, int cost3) {
        return Arrays.asList(
            new PackageInfo("Pack 1", Arrays.asList("* First Class", "* 5 Star Hotel", "* By Car", "* 5 Persons", "* 6 Days"), cost1),
            new PackageInfo("Pack 2", Arrays.asList("* Second Class", "* 3 Star Hotel", "* By Bus", "* 4 Persons", "* 4 Days"), cost2),
            new PackageInfo("Pack 3", Arrays.asList("* Third Class", "* 2 Star Hotel", "* By Train", "* 4 Persons", "* 3 Days"), cost3)
        );
    }

    private static List<PackageInfo> createStandardInternationalPackages(int cost1, int cost2, int cost3) {
        return Arrays.asList(
            new PackageInfo("Pack 1", Arrays.asList("* First Class", "* 5 Star Hotel", "* By Airplane", "* 5 Persons", "* 6 Days"), cost1),
            new PackageInfo("Pack 2", Arrays.asList("* Second Class", "* 3 Star Hotel", "* By Airplane", "* 4 Persons", "* 4 Days"), cost2),
            new PackageInfo("Pack 3", Arrays.asList("* Third Class", "* 2 Star Hotel", "* By Airplane", "* 4 Persons", "* 3 Days"), cost3)
        );
    }

    public static Destination createCoxsBazar() {
        List<PackageInfo> packages = createStandardDomesticPackages(300, 200, 150);
        return new Destination("Cox's Bazar", packages, CURRENCY);
    }

    public static Destination createBandarban() {
        List<PackageInfo> packages = createStandardDomesticPackages(350, 260, 120);
        return new Destination("Bandarban", packages, CURRENCY);
    }

    public static Destination createRangamati() {
        List<PackageInfo> packages = createStandardDomesticPackages(350, 260, 120);
        return new Destination("Rangamati", packages, CURRENCY);
    }

    public static Destination createSajekValley() {
        List<PackageInfo> packages = createStandardDomesticPackages(350, 260, 120);
        return new Destination("Sajek Valley", packages, CURRENCY);
    }

    public static Destination createSreemangal() {
        List<PackageInfo> packages = createStandardDomesticPackages(350, 260, 120);
        return new Destination("Sreemangal", packages, CURRENCY);
    }

    public static Destination createFrance() {
        List<PackageInfo> packages = createStandardInternationalPackages(3000, 1500, 1000);
        return new Destination("France", packages, CURRENCY);
    }

    public static Destination createIndonesia(){
        List<PackageInfo> packages = createStandardInternationalPackages(2000, 1000, 500);
        return new Destination("Indonesia", packages, CURRENCY);
    }

    public static Destination createGreece(){
        List<PackageInfo> packages = createStandardInternationalPackages(3000, 1500, 1000);
        return new Destination("Greece", packages, CURRENCY);
    }

    public static Destination createItaly(){
        List<PackageInfo> packages = createStandardInternationalPackages(3000, 1500, 1000);
        return new Destination("Italy", packages, CURRENCY);
    }

    public static Destination createSouthAfrica(){
        List<PackageInfo> packages = createStandardInternationalPackages(3000, 1500, 1000);
        return new Destination("South Africa", packages, CURRENCY);
    }

    // ... Add static methods for all other destinations (Sajek, Italy, Greece, etc.)
}