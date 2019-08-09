package by.training.task2threads.service;

import by.training.task2threads.bean.entity.Matrix;

import java.util.Random;

public class MatrixGenerationService {

    /**
     * Random bound for matrix content numbers.
     */
    private static final int RANDOM_BOUND = 20;
    private static final Random random = new Random();
    /**
     * Generate n*m matrix.
     * @param n number of rows in generated matrix.
     * @param m number of columns in generated matrix.
     * @return generated matrix.
     */
    public Matrix generateMatrix(final int n, final int m) {
        int[][] content = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                content[i][j] = random.nextInt(RANDOM_BOUND);
            }
        }
        return new Matrix(n, m, content);
    }
}
