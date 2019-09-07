package by.training.task4web.service;

import by.training.task4web.service.exception.ServiceException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class GemsSaxBuilder extends BaseGemsBuilder {
    /**
     * Build Set of gems from xml file and validate xml file by schema.
     * @param xmlFileName name of xml file
     * @param xsdFileName name of XSD Schema file
     * @throws ServiceException if something goes wrong while parsing
     */
    @Override
    public void buildGemsFromFile(final String xmlFileName,
                                      final String xsdFileName)
            throws ServiceException {
        try {
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
            gems = contentHandler.getGems();
        } catch (SAXException | IOException
                | ParserConfigurationException newE) {
            throw new ServiceException(newE);
        }
    }
}
