package by.training.task4xml.service;

import by.training.task4xml.bean.entity.FamousGem;
import by.training.task4xml.bean.entity.Gem;
import by.training.task4xml.bean.entity.GemPropertyEnum;
import by.training.task4xml.service.exception.ServiceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class GemsDomBuilder extends BaseGemsBuilder {
    private DocumentBuilder docBuilder;
    private static final String FAMOUS_GEM = "famousGem";
    private static final String GEM = "gem";

    @Override
    public void buildGemsFromFile(final String xmlFileName,
                                  final String xsdFileName)
            throws ServiceException {
        initDocBuilder(xsdFileName);
        Document document = null;
        try {
            document = docBuilder.parse(xmlFileName);
            Element root = document.getDocumentElement();
            NodeList gemList = root.getElementsByTagName(GEM);
            var gemListSize = gemList.getLength();
            for (int i = 0; i < gemListSize; ++i) {
                Element gemNode = (Element) gemList.item(i);
                Gem gem = new Gem();
                buildGem(gem, gemNode);
                gems.add(gem);
            }
            NodeList famousGemList = root.getElementsByTagName(FAMOUS_GEM);
            var famousGemListSize = famousGemList.getLength();
            for (int i = 0; i < famousGemListSize; ++i) {
                Element gemNode = (Element) famousGemList.item(i);
                FamousGem famousGem = new FamousGem();
                buildGem(famousGem, gemNode);
                buildFamousGem(famousGem, gemNode);
                gems.add(famousGem);
            }
        } catch (SAXException | IOException newE) {
            throw new ServiceException(newE);
        }

    }

    private void buildGem(final Gem gem, final Element newElement) {
        var oIdStr = Optional.ofNullable(
                newElement.getAttribute(GemPropertyEnum.GEM_ID.getValue()));
        oIdStr.ifPresent(s -> gem.setGemId(Long.parseLong(s)));
        var oDateStr = Optional.ofNullable(newElement.getAttribute(
                GemPropertyEnum.AUCTION_DATE.getValue()));
        oDateStr.ifPresent(d -> {
            if(!d.isEmpty()){
                gem.setAuctionDate(LocalDate.parse(d));
            }
        });
        var gemTypeStr = newElement.getElementsByTagName(
                GemPropertyEnum.GEM_TYPE.getValue())
                .item(0).getTextContent();
        gem.setGemType(Gem.GemType.valueOf(gemTypeStr));
        var preciousnessStr = newElement.getElementsByTagName(
                GemPropertyEnum.PRECIOUSNESS.getValue())
                .item(0).getTextContent();
        gem.setPreciousness(Gem.Preciousness.valueOf(preciousnessStr));
        var origin = Optional.ofNullable(newElement.getElementsByTagName(
                GemPropertyEnum.ORIGIN.getValue())
                .item(0));
        origin.ifPresent(o -> gem.setOrigin(o.getTextContent()));
        buildVisualParameters(gem, newElement);
        var valueStr = newElement.getElementsByTagName(
                GemPropertyEnum.VALUE.getValue())
                .item(0).getTextContent();
        gem.setValue(Float.parseFloat(valueStr));
    }
    
    private void buildFamousGem(final FamousGem newFamousGem,
                                final Element newElement) {
        var nameStr = newElement.getElementsByTagName(
                GemPropertyEnum.NAME.getValue())
                .item(0).getTextContent();
        newFamousGem.setName(nameStr);
        var foundationDateStr = newElement.getElementsByTagName(
                GemPropertyEnum.NAME.getValue())
                .item(0).getTextContent();
        newFamousGem.setFoundationDate(foundationDateStr);
    }

    private void buildVisualParameters(final Gem newGem,
                                       final Element newElement) {
        var gemVP = newGem.getVisualParameters();
        var visualParametersNode = (Element )newElement
                .getElementsByTagName(GemPropertyEnum.VISUAL_PARAMS.getValue())
                .item(0);
        var colorStr = visualParametersNode
                .getElementsByTagName(GemPropertyEnum.COLOR.getValue())
                .item(0)
                .getTextContent();
        gemVP.setColor(colorStr);
        
        var transparencyStr = visualParametersNode
                .getElementsByTagName(GemPropertyEnum.TRANSPARENCY.getValue())
                .item(0)
                .getTextContent();
        gemVP.setTransparency(Integer.parseInt(transparencyStr));

        var cuttingMethodStr = Optional.ofNullable(visualParametersNode
                .getElementsByTagName(GemPropertyEnum.CUTTING_METHOD.getValue())
                .item(0));
        cuttingMethodStr.ifPresent(
                c -> gemVP.setCuttingMethod(
                        Integer.parseInt(c.getTextContent())));
    }
    

    private void initDocBuilder(final String xsdFileName)
            throws ServiceException {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newDefaultInstance();
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
            Schema schema = xsdFactory.newSchema(new File(xsdFileName));
            factory.setSchema(schema);
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException | SAXException e) {
            throw new ServiceException(e);
        }
    }
}
