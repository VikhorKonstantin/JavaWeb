package by.training.task4web.service;

import by.training.task4web.bean.entity.Gem;
import by.training.task4web.service.exception.ServiceException;

import java.util.Set;

public final class ParseDirector {
    private ParseDirector() {
    }

    /**
     * Creates Set of gems.
     * @param builder GemsBuilder
     * @param xmlFileName name of xml file
     * @param xsdFileName name of xsd file
     * @return Set<Gem>
     * @throws ServiceException if something goes wrong while parsing
     */
    public static Set<Gem> createGems(final BaseGemsBuilder builder,
                                      final String xmlFileName,
                                      final String xsdFileName)
            throws ServiceException {
        builder.buildGemsFromFile(xmlFileName, xsdFileName);
        return builder.getGems();
    }
}
