import java.util.Random;

public abstract class MathHelper {
    private static final Random r = new Random();

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        return r.nextInt((max - min) + 1) + min;
    }
}
