import java.util.List;

public class PackageInfo {
    private final String name;
    private final List<String> features;
    private final int cost;

    public PackageInfo(String name, List<String> features, int cost) {
        this.name = name;
        this.features = features;
        this.cost = cost;
    }

    public String getName() { return name; }

    public List<String> getFeatures() { return features; }

    public int getCost() { return cost; }
}