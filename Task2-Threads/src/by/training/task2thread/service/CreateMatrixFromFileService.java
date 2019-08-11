package by.training.task2thread.service;

import by.training.task2thread.bean.entity.Matrix;
import by.training.task2thread.bean.exception.BeanException;
import by.training.task2thread.bean.factory.MatrixFactory;
import by.training.task2thread.dao.exception.DAOException;
import by.training.task2thread.dao.reader.FileStringReader;
import by.training.task2thread.service.exception.ServiceException;

import java.util.Optional;

public class CreateMatrixFromFileService {
    /**
     * Exception message.
     */
    private static final String EXC_MSG = "Invalid file name. ";
    /**
     * Creates matrix. Uses data from file with fileName.
     * @param fileName name of file
     * @return Matrix
     * @throws ServiceException if something goes wrong.
     */
    public Matrix createMatrixFromFile(final String fileName)
            throws ServiceException {
        FileStringReader reader = new FileStringReader();
        var oFileName = Optional.ofNullable(fileName);
        try {
            var matrixParams =
                    reader.readFromFile(oFileName.orElseThrow(
                            () -> new ServiceException(EXC_MSG)
                    ));
            return MatrixFactory.getInstance().createMatrix(matrixParams);
        } catch (DAOException | BeanException newE) {
            throw new ServiceException(newE);
        }
    }
}
