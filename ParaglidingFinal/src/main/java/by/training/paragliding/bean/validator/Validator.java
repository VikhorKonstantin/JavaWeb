package by.training.paragliding.bean.validator;

import by.training.paragliding.bean.exception.BeanException;

import javax.servlet.http.HttpServletRequest;

public interface Validator<T> {
    String ECX_MSG = "Request invalid";
    T validate(HttpServletRequest newRequest) throws BeanException;
}
