package by.training.task4xml.service;

import by.training.task4xml.bean.entity.Gem;
import by.training.task4xml.service.exception.ServiceException;

import java.util.Set;

public final class ParseDirector {
    public static Set<Gem> createUser(final BaseGemsBuilder builder,
                                      final String xmlFileName,
                                      final String xsdFileName)
            throws ServiceException {
        builder.buildGemsFromFile(xmlFileName, xsdFileName);
        return builder.getGems();
    }
}