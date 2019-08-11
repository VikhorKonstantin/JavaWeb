package by.training.task2thread.bean.entity;

import java.util.Arrays;
import java.util.Objects;

/**
 * Matrix entity class.
 */
public class Matrix {

    /**
     * Number of rows in matrix.
     */
    private int rowNumber;
    /**
     * Number of columns in matrix.
     */
    private  int columnNumber;
    /**
     * Array which represents matrix.
     */
    private int[][]  matrixContent;

    /**
     * Create empty [newRowNumber * newColumnNumber] matrix.
     * @param newRowNumber new rowNumber
     * @param newColumnNumber new columnNumber
     */
    public Matrix(final int newRowNumber, final int newColumnNumber) {
        rowNumber = newRowNumber;
        columnNumber = newColumnNumber;
        matrixContent = new int[rowNumber][columnNumber];
    }
    /**
     * Create [newRowNumber * newColumnNumber] matrix with newMatrixContent
     * as a content.
     * @param newRowNumber new rowNumber
     * @param newColumnNumber new columnNumber
     * @param newMatrixContent new matrixContent
     */
    public Matrix(final int newRowNumber, final int newColumnNumber,
                  final int[][] newMatrixContent) {
        rowNumber = newRowNumber;
        columnNumber = newColumnNumber;
        matrixContent = newMatrixContent;
    }

    /**
     * returns number of rows.
     * @return rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * returns number of columns.
     * @return columnNumber
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * returns [i][j] element of matrix.
     * @param i row number
     * @param j column number
     * @return [i][j] element of matrix.
     */
    public int readElement(final int i, final int j) {
        return matrixContent[i][j];
    }

    /**
     * Sets [i][j] element of matrix to newElem.
     * @param i row number
     * @param j column number
     * @param newElem new value.
     */
    public void writeElement(final int i, final int j, final int newElem) {
        matrixContent[i][j] = newElem;
    }

    /**
     * Check if two matrices are equals.
     * @param newO matrix to compare.
     * @return true(false) if matrices are equals(not)
     */
    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (!(newO instanceof Matrix)) {
            return false;
        }
        Matrix matrix = (Matrix) newO;
        return rowNumber == matrix.rowNumber
                && columnNumber == matrix.columnNumber
                && Arrays.deepEquals(matrixContent, matrix.matrixContent);
    }

    /**
     * Returns hash code of matrix.
     * @return hash code
     */
    @Override
    public int hashCode() {
        final int hashCodeHelpNumber = 31;
        int result = Objects.hash(rowNumber, columnNumber);
        result = hashCodeHelpNumber * result + Arrays.hashCode(matrixContent);
        return result;
    }

    /**
     * Returns string representation of matrix.
     * @return string representation of matrix
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                stringBuilder.append(matrixContent[i][j]).append('\t');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
