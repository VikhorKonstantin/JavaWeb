package by.training.task4xml.service;

import by.training.task4xml.bean.entity.Gem;
import by.training.task4xml.service.exception.ServiceException;

import java.util.Set;

public abstract class AbstractGemsBuilder {
    
    public abstract Set<Gem> buildGemsFromFile(final String xmlFileName,
                                               final String xsdFileName)
            throws ServiceException;
}
