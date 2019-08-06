package by.training.task2threads.service;

import by.training.task2threads.bean.entity.Matrix;

import java.util.Random;

public class MatrixGeneration {

    public static Matrix generateMatrix(int n, int m) {
        int[][] content= new int[n][m];
        Random random =new Random();
        for(int i=0; i < n ;i++) {
            for(int j=0; j < m; j++) {
                content[i][j] = random.nextInt(12);
            }
        }
        return new Matrix(n, m, content);
    }
}
