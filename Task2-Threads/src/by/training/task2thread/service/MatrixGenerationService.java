package by.training.task2thread.service;

import by.training.task2thread.bean.entity.Matrix;
import by.training.task2thread.service.exception.ServiceException;

import java.util.Random;

public class MatrixGenerationService {
    /**
     * Random bound for matrix content numbers.
     */
    private static final int RANDOM_BOUND = 20;
    /**
     * Random.
     */
    private static final Random RANDOM = new Random();
    /**
     * Exception msg.
     */
    private static final String INVALID_ARGS_MSG =
            "Invalid arguments. ";
    /**
     * Generate n*m matrix.
     * @param n number of rows in generated matrix.
     * @param m number of columns in generated matrix.
     * @return generated matrix.
     * @throws ServiceException if arguments invalid.
     */
    public Matrix generateMatrix(final int n, final int m)
            throws ServiceException {
        int[][] content = new int[n][m];
        if (n < 1 || m < 1) {
            throw new ServiceException(INVALID_ARGS_MSG);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                content[i][j] = RANDOM.nextInt(RANDOM_BOUND);
            }
        }
        return new Matrix(n, m, content);
    }
}
