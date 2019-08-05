package by.training.task2threads.service;

import by.training.task2threads.bean.entity.Matrix;
import by.training.task2threads.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class MultiplyMatrixService {
    /**
     * Number of threads.
     */
    private static final int THREADS_NUMBER = 7;
    /**
     * First matrix.
     */
    private Matrix matrix1;
    /**
     * Second matrix.
     */
    private Matrix matrix2;
    /**
     * Number of rows in result matrix.
     */
    private int resultRowNumber;
    /**
     * Number of columns in result matrix.
     */
    private int resultColumnNumber;
    /**
     * Content of result matrix.
     */
    private int[][] resultContent;

    /**
     * Multiplication of matrix1 and matrix2.
     * @param newMatrix1 first matrix
     * @param newMatrix2 second matrix
     * @return new Matrix
     * @throws ServiceException if something goes wrong
     */
    public Matrix multiplyMatrices(final Matrix newMatrix1,
                                   final Matrix newMatrix2)
            throws ServiceException {
        this.matrix1 = newMatrix1;
        this.matrix2 = newMatrix2;
        if (matrix1.getColumnNumber() != matrix2.getRowNumber()) {
            throw new ServiceException("Invalid matrices sizes ");
        }
        resultRowNumber = matrix1.getRowNumber();
        resultColumnNumber = matrix2.getColumnNumber();
        resultContent = new int[resultRowNumber][resultColumnNumber];



        final int rangeSize = resultRowNumber / THREADS_NUMBER;
        int firstRowIndex = 0;
        int lastRowIndex = rangeSize - 1;
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i < THREADS_NUMBER; i++) {
            threads.add(new Thread(
                    new MatrixMultiplyThread(firstRowIndex, lastRowIndex)));
           firstRowIndex += rangeSize;
           lastRowIndex += rangeSize;
        }
        threads.add(new Thread(
                new MatrixMultiplyThread(firstRowIndex,
                        resultRowNumber - 1)));


        var start = System.nanoTime();
        threads.forEach(Thread::start);
        try {
            for (var thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new ServiceException(e);
        }
        var finish = System.nanoTime();
        System.out.println("Time: " + (finish - start));
        return new Matrix(resultRowNumber, resultColumnNumber, resultContent);
    }

    /**
     * Calculate row of result matrix.
     * @param newI index of row
     * @return result row
     */
    private int[] calculateResultRow(final int newI) {
        int[] resultRow = new int[resultColumnNumber];
        for (int i = 0; i < resultColumnNumber; i++) {
            resultRow[i] = calculateResultElem(newI, i);
        }
        return resultRow;
    }

    /**
     * Calculate element of result matrix.
     * @param i index of row
     * @param j index of column
     * @return element
     */
    private int calculateResultElem(final int i, final int j) {
        var resultElem = 0;
        for (int k = 0; k <  matrix1.getColumnNumber(); k++) {
            resultElem += matrix1.getElement(i, k)
                    * matrix2.getElement(k, j);
        }
        return resultElem;
    }

    /**
     * Calculating thread.
     */
    private class MatrixMultiplyThread implements Runnable {
        /**
         * index of first row to calculate.
         */
        private int firstRowIndex;
        /**
         * index of last row to calculate.
         */
        private int lastRowIndex;

        /**
         * Constructor with two params.
         * @param newFirstRowIndex new firstRowIndex
         * @param newLastRowIndex new lastRowIndex
         */
        MatrixMultiplyThread(final int newFirstRowIndex,
                                    final int newLastRowIndex) {
            firstRowIndex = newFirstRowIndex;
            lastRowIndex = newLastRowIndex;
        }

        /**
         * Thread calculation action.
         */
        @Override
        public void run() {
            for (int i = firstRowIndex; i <= lastRowIndex; i++) {
                resultContent[i] = calculateResultRow(i);
            }
        }
    }
}


