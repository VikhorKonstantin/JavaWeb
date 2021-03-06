package by.training.task4web.service;

import by.training.task4web.bean.entity.FamousGem;
import by.training.task4web.bean.entity.Gem;
import by.training.task4web.bean.entity.GemPropertyEnum;
import by.training.task4web.service.exception.ServiceException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class GemsStaxBuilder extends BaseGemsBuilder {
    /**
     * Reader factory.
     */
    private XMLInputFactory inputFactory;
    /**
     * Tag name of famous gem.
     */
    private static final String FAMOUS_GEM = "famousGem";
    /**
     * Tag name of gem.
     */
    private static final String GEM = "gem";
    /**
     * Connects tag-names with parse actions.
     */
    private final Map<String, BiConsumer<String, Gem>>
            gemSetters = new HashMap<>();

    /**
     * Initialisation of factory and gemSetters.
     */
    public GemsStaxBuilder() {
        inputFactory = XMLInputFactory.newDefaultFactory();
        gemSetters.put(GemPropertyEnum.PRECIOUSNESS.getValue(),
                (s, g) -> g.setPreciousness(Gem.Preciousness.valueOf(s)));
        gemSetters.put(GemPropertyEnum.GEM_TYPE.getValue(),
                (s, g) -> g.setGemType(Gem.GemType.valueOf(s)));
        gemSetters.put(GemPropertyEnum.ORIGIN.getValue(),
                (s, g) -> g.setOrigin(s));
        gemSetters.put(GemPropertyEnum.COLOR.getValue(),
                (s, g) -> g.getVisualParameters().setColor(s));
        gemSetters.put(GemPropertyEnum.TRANSPARENCY.getValue(),
                (s, g) -> g.getVisualParameters()
                        .setTransparency(Integer.parseInt(s)));
        gemSetters.put(GemPropertyEnum.CUTTING_METHOD.getValue(),
                (s, g) -> g.getVisualParameters()
                        .setCuttingMethod(Integer.parseInt(s)));
        gemSetters.put(GemPropertyEnum.VALUE.getValue(),
                (s, g) -> g.setValue(Float.parseFloat(s)));

        gemSetters.put(GemPropertyEnum.NAME.getValue(),
                (s, g) -> ((FamousGem) g).setName(s));
        gemSetters.put(GemPropertyEnum.FOUNDATION_DATE.getValue(),
                (s, g) -> ((FamousGem) g).setFoundationDate(s));

    }
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
        String name;
        try (FileInputStream inputStream =
                     new FileInputStream(new File(xmlFileName))
        ) {
//            SchemaFactory factory = SchemaFactory.newInstance(
//            XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = factory.newSchema(new File(xsdFileName));
//            Validator validator = schema.newValidator();
            XMLStreamReader reader = inputFactory
                    .createXMLStreamReader(inputStream);
//            validator.validate(new StAXSource(reader));
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(GEM)) {
                        Gem gem = new Gem();
                        buildGem(gem, reader);
                        gems.add(gem);
                    } else if (name.equals(FAMOUS_GEM)) {
                        FamousGem famousGem = new FamousGem();
                        buildGem(famousGem, reader);
                        gems.add(famousGem);
                    }
                }
            }
        } catch (XMLStreamException | NumberFormatException ex) {
            final String msg = "StAX parsing error! ";
            throw new ServiceException(msg, ex);
        } catch (IOException ex) {
            throw new ServiceException(ex);
        }

    }

    private void buildGem(final Gem gem, final XMLStreamReader newReader)
            throws XMLStreamException {
        var oId = Optional.ofNullable(newReader.getAttributeValue(
                null, GemPropertyEnum.GEM_ID.getValue()));
        var oData = Optional.ofNullable(newReader.getAttributeValue(
                null, GemPropertyEnum.AUCTION_DATE.getValue()));
        oId.ifPresent(i -> gem.setGemId(Long.parseLong(i)));
        oData.ifPresent(d -> gem.setAuctionDate(LocalDate.parse(d)));
        String name;
        while (newReader.hasNext()) {
            final int type = newReader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = newReader.getLocalName();
                if (gemSetters.containsKey(name)) {
                    gemSetters.get(name).accept(newReader.getElementText(),
                            gem);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = newReader.getLocalName();
                if (name.equals(GEM) || name.equals(FAMOUS_GEM)) {
                    return;
                }
            }
        }
    }
}
