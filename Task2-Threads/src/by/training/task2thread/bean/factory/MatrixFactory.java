package by.training.task2thread.bean.factory;

import by.training.task2thread.bean.entity.Matrix;
import by.training.task2thread.bean.exception.BeanException;
import by.training.task2thread.bean.validator.MatrixValidator;
import by.training.task2thread.service.parser.StringParser;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class which provides matrix creations method.
 */
public final class MatrixFactory {
    /**
     * Exception message.
     */
    private static final String EXC_MSG = "Invalid matrix params";
    /**
     * Instance.
     */
    private static MatrixFactory instance = null;
    /**
     * Lock for synchronization.
     */
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * Makes object creation impossible.
     */
    private MatrixFactory() {
        //Makes object creation impossible.
    }

    /**
     * returns MatrixFactory instance.
     * @return INSTANCE
     */
    public static MatrixFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new MatrixFactory();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    /**
     * Create matrix.
     * @param matrixParams params of matrix to create.
     * @return new Matrix
     * @throws BeanException if something goes wrong.
     */
    public Matrix createMatrix(final List<String> matrixParams)
            throws BeanException {
        MatrixValidator matrixValidator = new MatrixValidator();
        if (matrixValidator.isMatrixDataValid(matrixParams)) {
            var listIterator = matrixParams.listIterator();
            final int rowNumberIndex = 0;
            final int columnNumberIndex = 1;
            var sizeLine = StringParser.parseString(listIterator.next().trim());
            final int rowNumber = Integer.parseInt(
                    sizeLine.get(rowNumberIndex));
            final int columnNumber = Integer.parseInt(
                    sizeLine.get(columnNumberIndex));
            var matrix = new int[rowNumber][columnNumber];
            for (int i = 0; i < rowNumber; i++) {
                var digits = StringParser.parseString(
                        listIterator.next().trim());
                for (int j = 0; j < columnNumber; j++) {
                    matrix[i][j] = Integer.parseInt(digits.get(j));
                }
            }
            return new Matrix(rowNumber, columnNumber, matrix);
        } else {
            throw new BeanException(EXC_MSG);
        }
    }
}
