/**
 * Something that's bound to the existence of another object.
 */

public abstract class UpperBucketCrab {

     protected abstract void onPulledDown();

     public UpperBucketCrab(LowerBucketCrab linked_crab)
     {
          link_to_crab(linked_crab);
     }

     protected void link_to_crab(LowerBucketCrab linked_crab)
     {
          BucketCrabManager.addCrabPair(linked_crab,this);
     }
}
