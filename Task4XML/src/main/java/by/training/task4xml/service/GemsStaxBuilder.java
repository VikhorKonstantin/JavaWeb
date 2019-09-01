package by.training.task4xml.service;

import by.training.task4xml.service.exception.ServiceException;

import javax.xml.stream.XMLInputFactory;

public class GemsStaxBuilder extends BaseGemsBuilder {
    private XMLInputFactory inputFactory;

    public GemsStaxBuilder() {
        inputFactory = XMLInputFactory.newDefaultFactory();
    }

    @Override
    public void buildGemsFromFile(final String xmlFileName,
                                  final String xsdFileName)
            throws ServiceException {
        
    }

    
}
