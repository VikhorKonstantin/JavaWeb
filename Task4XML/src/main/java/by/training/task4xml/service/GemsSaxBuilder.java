package by.training.task4xml.service;

import by.training.task4xml.bean.entity.Gem;
import by.training.task4xml.service.exception.ServiceException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class GemsSaxBuilder extends AbstractGemsBuilder {
    
    
    
    public Set<Gem> buildGemsFromFile(final String xmlFileName,
                                      final String xsdFileName)
            throws ServiceException {
        try{
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
            Schema schema = xsdFactory.newSchema(new File(xsdFileName));
            SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            factory.setSchema(schema);
            
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            SaxGemContentHandler contentHandler = new SaxGemContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(xmlFileName);
            return contentHandler.getGems();
        } catch (SAXException | IOException
                | ParserConfigurationException newE) {
            throw new ServiceException(newE);
        }
        
    }
}
