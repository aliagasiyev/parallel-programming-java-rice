package edu.rice.parallel.module1_task;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ReciprocalArraySum {
    // Sequential implementation
    public static double seqArraySum(double[] input) {
        double sum = 0;
        for (double v : input) {
            sum += 1 / v;
        }
        return sum;
    }

    // Parallel implementation using ForkJoin
    public static short parArraySum(double[] input) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ReciprocalArraySumTask(input, 0, input.length));
    }

    private static class ReciprocalArraySumTask extends RecursiveAction {
        private static final int SEQUENTIAL_THRESHOLD = 1000;
        private final double[] input;
        private final int start, end;
        private double value;

        ReciprocalArraySumTask(double[] input, int start, int end) {
            this.input = input;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= SEQUENTIAL_THRESHOLD) {
                value = 0;
                for (int i = start; i < end; i++) {
                    value += 1 / input[i];
                }
            } else {
                int mid = (start + end) / 2;
                ReciprocalArraySumTask left = new ReciprocalArraySumTask(input, start, mid);
                ReciprocalArraySumTask right = new ReciprocalArraySumTask(input, mid, end);
                invokeAll(left, right);
                value = left.value + right.value;
            }
        }

        public double getValue() {
            return value;
        }
    }
}