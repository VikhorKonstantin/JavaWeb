package by.training.task2threads.view;

import by.training.task2threads.bean.exception.BeanException;
import by.training.task2threads.bean.factory.MatrixFactory;
import by.training.task2threads.dao.exception.DAOException;
import by.training.task2threads.dao.reader.FileStringReader;
import by.training.task2threads.service.MultiplyMatrixService;
import by.training.task2threads.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Runner {
    /**
     * Makes Runner creation impossible.
     */
    private Runner() {
    }

    /**
     * Runs program.
     * @param args command line args
     */
    public static void main(final String[] args) {
        final Logger logger = LogManager.getLogger();
        FileStringReader reader = new FileStringReader();
        try {
            var matrixParams1 =
                    reader.readFromFile("input/matrix1.txt");
            var matrixParams2 =
                    reader.readFromFile("input/matrix2.txt");
            var matrix1 =
                    MatrixFactory.getINSTANCE().createMatrix(matrixParams1);
            var matrix2 =
                    MatrixFactory.getINSTANCE().createMatrix(matrixParams2);
            System.out.println(matrix1);
            System.out.println(matrix2);
            MultiplyMatrixService service = new MultiplyMatrixService();
            System.out.println("Result: ");
            System.out.println(service.multiplyMatrices(matrix1, matrix2));
        } catch (DAOException | BeanException
                | ServiceException newE) {
            logger.error(newE);
        }
    }
}
