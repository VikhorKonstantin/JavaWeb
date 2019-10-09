package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.exception.BeanException;

import java.sql.ResultSet;

public interface Builder<T> {
    String EXC_MSG = "Can't build object with given resultSet" ;
    /**
     *
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new T object.
     * @throws BeanException if some exceptions where thrown while object building.
     */
    T buildFromResultSet(ResultSet newResultSet) throws BeanException;
}
