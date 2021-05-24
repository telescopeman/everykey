import java.util.HashMap;

public abstract class BucketCrabManager {

    private static final HashMap<LowerBucketCrab,UpperBucketCrab> crabs = new HashMap<>();

    public static void pullDown(LowerBucketCrab crab)
    {
        crabs.get(crab).onPulledDown();
    }

    public static void addCrabPair(LowerBucketCrab lowerBucketCrab,UpperBucketCrab upperBucketCrab)
    {
        crabs.put(lowerBucketCrab,upperBucketCrab);
    }
}
