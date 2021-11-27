package it.unibo.oop.lab.workers02;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * This is an implementation using streams.
 * 
 */
public class MultiThreadedSumMatrix implements SumMatrix {

    private final int nthread;

    /**
     * 
     * @param nthread
     *            no. of thread performing the sum.
     */
    public MultiThreadedSumMatrix(final int nthread) {
        this.nthread = nthread;
    }

    private static class Worker extends Thread {
        private final double[][] matrix;
        private final int startrow;
        private final int nrows;
        private double res;

        /**
         * Build a new worker.
         * 
         * @param matrix
         *            the matrix to sum
         * @param startrow
         *            the initial position for this worker
         * @param nrows
         *            the no. of elems to sum up for this worker
         */
        Worker(final double[][] matrix, final int startrow, final int nrows) {
            super();
            this.matrix = Arrays.copyOf(matrix, matrix.length);
            this.startrow = startrow;
            this.nrows = nrows;
        }

        @Override
        public void run() {
            System.out.println("Working from row " + startrow + " to row " + (startrow + nrows - 1));
            for (int i = startrow; i < this.matrix.length && i < startrow + nrows; i++) {
                IntStream.range(0, this.matrix[i].length)
                         .forEach(c -> this.res += c);
            }
        }

        /**
         * Returns the result of summing up the double within the matrix.
         * 
         * @return the sum of every element in the matrix
         */
        public double getResult() {
            return this.res;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sum(final double[][] matrix) {
        final int nrows = matrix.length % nthread + matrix.length / nthread;
        /*
         * Build a stream of workers
         */
        return IntStream.iterate(0, start -> start + nrows)
                .limit(nthread)
                .mapToObj(start -> new Worker(matrix, start, nrows))
                // Start them
                .peek(Thread::start)
                // Join them
                .peek(MultiThreadedSumMatrix::joinUninterruptibly)
                 // Get their result and sum
                .mapToDouble(Worker::getResult)
                .sum();
    }

    private static void joinUninterruptibly(final Thread target) {
        var joined = false;
        while (!joined) {
            try {
                target.join();
                joined = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
