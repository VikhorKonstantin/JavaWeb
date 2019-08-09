package by.training.task2threads.service;

import by.training.task2threads.bean.entity.Matrix;
import by.training.task2threads.bean.exception.BeanException;
import by.training.task2threads.bean.factory.MatrixFactory;
import by.training.task2threads.dao.exception.DAOException;
import by.training.task2threads.dao.reader.FileStringReader;
import by.training.task2threads.service.exception.ServiceException;

public class CreateMatrixFromFileService {
    /**
     * Creates matrix. Uses data from file with fileName.
     * @param fileName name of file
     * @return Matrix
     * @throws ServiceException if something goes wrong.
     */
    public Matrix createMatrixFromFile(final String fileName)
            throws ServiceException {
        FileStringReader reader = new FileStringReader();
        try {
            var matrixParams =
                    reader.readFromFile(fileName);
            return MatrixFactory.getInstance().createMatrix(matrixParams);
        } catch (DAOException | BeanException newE) {
            throw new ServiceException(newE);
        }
    }
}
