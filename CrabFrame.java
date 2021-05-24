import java.awt.*;

public abstract class CrabFrame extends ListeningFrame implements LowerBucketCrab {

    public CrabFrame(String s, Dimension dimension) {
        super(s,dimension);
    }

    @Override
    protected void onClosed() {
        BucketCrabManager.pullDown(this);
    }
}
