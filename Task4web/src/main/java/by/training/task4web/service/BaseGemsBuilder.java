package by.training.task4web.service;

import by.training.task4web.bean.entity.Gem;
import by.training.task4web.service.exception.ServiceException;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseGemsBuilder {
    /**
     * Stores gems.
     */
    protected Set<Gem> gems = new HashSet<>();

    /**
     * Returns gems.
     * @return Set<Gem> gems
     */
    public Set<Gem> getGems() {
        return gems;
    }

    /**
     * Build Set of gems from xml file and validate xml file by schema.
     * @param xmlFileName name of xml file
     * @param xsdFileName name of XSD Schema file
     * @throws ServiceException if something goes wrong while parsing
     */
    public abstract void buildGemsFromFile(String xmlFileName,
                                           String xsdFileName)
            throws ServiceException;
}
