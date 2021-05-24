/**
 *
 */
public class IntervalCompare extends LayeredComparator<Integer> {


    public IntervalCompare() {
        super(new StrangeCompare());
    }

    /**
     * Basically says 'how much weirdness do the intervals in the scale have?'
     * This is janky. It only works like this because my coding earlier sucked.
     * It doesn't even really use the parameter. Idk, it works.
     *
     * @param o1 The index of the scale to measure.
     * @return The weirdness of the intervals in the scale.
     */
    public int getValue(Integer o1)
    {
        final double WEIGHT = 3;

        int[] scale = UI.getCurrentList()[o1];
        int sum = 0;
        for (int i = 0; i < scale.length; i++)
        {
            final int thisInterval;
            if (i < scale.length - 1) {
                // for the first six notes...
                thisInterval = (scale[i + 1] - scale[i]);
            }
            else
            {
                // for the last note...
                thisInterval = (scale[0]+12 - scale[i]);
            }

            // basically, we make big intervals matter
            // far more than small intervals.
            switch (thisInterval)
            {
                case 0:

                case 1:

                case 2:
                    break;
                default:
                    sum += Math.pow(thisInterval,WEIGHT);
                    break;
            }

        }
        return sum;
    }


}
