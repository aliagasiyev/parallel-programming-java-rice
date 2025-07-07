package edu.rice.parallel.module0_setup;

import static edu.rice.pcdp.PCDP.finish;
import static edu.rice.pcdp.PCDP.async;

/**
 * A simple class for testing compilation of an PCDP project.
 */
public final class Setup {

    /**
     * Default constructor.
     */
    private Setup() {
    }

    /**
     * A simple method for testing compilation of an PCDP project.
     * @param val Input value
     * @return Dummy value
     */
    public static int setup(final int val) {
        final int[] result = new int[1];
        setup(() -> {
            setup(() -> {
                result[0] = val;
            });
        });
        return result[0];
    }
}
