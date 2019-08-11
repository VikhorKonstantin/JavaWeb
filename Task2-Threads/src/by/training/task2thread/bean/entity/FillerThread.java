package by.training.task2thread.bean.entity;

import java.util.Random;

public class FillerThread extends Thread {
    /**
     * Bound for random.
     */
    private static final int RND_BOUND = 20;
    /**
     * Positive integer. Using to avoid 0-value of number.
     */
    private static final int POSITIVE_INT = 5;
    /**
     * Number .
     */
    private int number;

    /**
     * Create FillerThread.
     * @param newRunnable Runnable action.
     */
    public FillerThread(final Runnable newRunnable) {
        super(newRunnable);
        number = new Random().nextInt(RND_BOUND) + POSITIVE_INT;
    }

    /**
     * Returns number which matrix diagonal elem will be filled with.
     * @return number
     */
    public int getNumber() {
        return number;
    }
}
