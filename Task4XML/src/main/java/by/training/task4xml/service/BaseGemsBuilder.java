package by.training.task4xml.service;

import by.training.task4xml.bean.entity.Gem;
import by.training.task4xml.service.exception.ServiceException;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseGemsBuilder {
    protected Set<Gem> gems = new HashSet<>();

    public Set<Gem> getGems() {
        return gems;
    }

    public abstract void buildGemsFromFile(final String xmlFileName,
                                           final String xsdFileName)
            throws ServiceException;
}
