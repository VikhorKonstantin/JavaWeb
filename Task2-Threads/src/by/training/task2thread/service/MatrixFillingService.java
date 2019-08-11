package by.training.task2thread.service;

import by.training.task2thread.bean.entity.Matrix;
import by.training.task2thread.bean.entity.FillerThread;
import by.training.task2thread.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MatrixFillingService {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * Matrix to fill.
     */
    private Matrix matrix;
    /**
     * Exception message.
     */
    private static final String EXC_MSG = "Something went wrong while filling";
    /**
     * Invalid matrix message.
     */
    private static final String INVALID_ARGS_MSG =
            "Invalid arguments. ";
    /**
     * Fills matrix diagonal.
     * @param numberOfThreads number of threads using for computing.
     * @param newMatrix matrix to fill.
     * @throws ServiceException if something goes wrong
     * @return filled matrix.
     */
    public Matrix fillDiagonal(final Matrix newMatrix,
                               final int numberOfThreads)
            throws ServiceException {
        matrix = Optional.ofNullable(newMatrix).orElseThrow(
                () -> new ServiceException(INVALID_ARGS_MSG)
        );
        if (matrix.getRowNumber() != matrix.getColumnNumber()
                || numberOfThreads < 1) {
            throw new ServiceException(INVALID_ARGS_MSG);
        }
        final int dioLength = matrix.getColumnNumber();
        ThreadFactory threadFactory = FillerThread::new;
        ExecutorService executorService =
                Executors.newFixedThreadPool(numberOfThreads, threadFactory);
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < dioLength; i++) {
            indexes.add(i);
        }
        List<Future<Boolean>> futureList = new ArrayList<>();
        while (!indexes.isEmpty()) {
            indexes.forEach(
                    index -> futureList.add(
                            executorService.submit(new FillTask(index))));
            for (int i = 0; i < futureList.size(); i++) {
                try {
                    final long timeout = 5;
                    var state = futureList.get(i)
                            .get(timeout, TimeUnit.SECONDS);
                    if (state) {
                        indexes.remove((Integer) i);
                    }
                } catch (InterruptedException | ExecutionException
                        | TimeoutException newE) {
                    Thread.currentThread().interrupt();
                    throw new ServiceException(EXC_MSG, newE);
                }
            }
            futureList.clear();
        }
        executorService.shutdown();
        try {
            final long timeout = 1;
            executorService.awaitTermination(timeout, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException(EXC_MSG, e);
        }
        return matrix;
    }

    private class FillTask implements Callable<Boolean> {
        /**
         * Index of row to fill.
         */
        private int index;

        FillTask(final int newIndex) {
            index = newIndex;
        }

        @Override
        public Boolean call() {
            var curThread = Thread.currentThread();
            if (curThread instanceof FillerThread) {
                var threadFiller = (FillerThread) curThread;
                matrix.writeElement(index, index, threadFiller.getNumber());
                try {
                    final long timeout = 1;
                    TimeUnit.SECONDS.sleep(timeout);
                } catch (InterruptedException newE) {
                    Thread.currentThread().interrupt();
                    logger.error(EXC_MSG, newE);
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
    }


}
