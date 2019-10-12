package by.training.paragliding.bean.validator;

import by.training.paragliding.bean.exception.BeanException;

import javax.servlet.http.HttpServletRequest;

public interface Validator<T> {
    String EXC_MSG = "Request invalid";
    T validate(HttpServletRequest newRequest) throws BeanException;
}
