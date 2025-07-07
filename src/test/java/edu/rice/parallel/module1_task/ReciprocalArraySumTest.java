package edu.rice.parallel.module1_task;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReciprocalArraySumTest {
    @Test
    public void testSeqArraySum() {
        double[] input = {1.0, 2.0, 4.0};
        double expected = 1.0 + 0.5 + 0.25;
        assertEquals(expected, ReciprocalArraySum.seqArraySum(input), 1e-6);
    }

    @Test
    public void testParArraySum() {
        double[] input = {1.0, 2.0, 4.0};
        double expected = 1.0 + 0.5 + 0.25;
        assertEquals(expected, ReciprocalArraySum.parArraySum(input), 1e-6);
    }
}