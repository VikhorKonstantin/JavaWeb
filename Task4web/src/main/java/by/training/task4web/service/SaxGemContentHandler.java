package by.training.task4web.service;

import by.training.task4web.bean.entity.FamousGem;
import by.training.task4web.bean.entity.Gem;
import by.training.task4web.bean.entity.GemPropertyEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SaxGemContentHandler extends DefaultHandler {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger();
    /**
     * Set of gems.
     */
    private Set<Gem> gems = new HashSet<>();
    /**
     * Tag name of famous gem.
     */
    private static final String FAMOUS_GEM = "famousGem";
    /**
     * Tag name of gem.
     */
    private static final String GEM = "gem";
    /**
     * Current gem.
     */
    private Gem currentGem;
    /**
     * Current property.
     */
    private GemPropertyEnum currentProperty;
    /**
     * Tags which represent fields of entity classes.
     */
    private EnumSet<GemPropertyEnum> contentTags = EnumSet.range(
            GemPropertyEnum.PRECIOUSNESS, GemPropertyEnum.NAME);

    SaxGemContentHandler() {
        super();
    }

    /**
     * Receive notification of the start of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes) {
        var attrMap = new HashMap<String, String>();
        var length = attributes.getLength();
        for (int i = 0; i < length; ++i) {
            attrMap.put(attributes.getLocalName(i), attributes.getValue(i));
        }
        var oId = Optional.ofNullable(
                attrMap.get(GemPropertyEnum.GEM_ID.getValue()));
        var oDate = Optional.ofNullable(
                attrMap.get(GemPropertyEnum.AUCTION_DATE.getValue()));
        switch (localName) {
            case GEM:
                currentGem = new Gem();
                oId.ifPresent(newS -> currentGem.setGemId(
                        Long.parseLong(newS)));
                oDate.ifPresent(newS -> currentGem.setAuctionDate(
                        LocalDate.parse(newS)));
                break;
            case FAMOUS_GEM:
                currentGem = new FamousGem();
                oId.ifPresent(newS -> currentGem.setGemId(
                        Long.parseLong(newS)));
                oDate.ifPresent(newS -> currentGem.setAuctionDate(
                        LocalDate.parse(newS)));
                break;
            default:
                try {
                    final String regex = "([a-z])([A-Z]+)";
                    final String replacement = "$1_$2";
                    var enumName = localName
                            .replaceAll(regex, replacement)
                            .toUpperCase();
                    var tag = GemPropertyEnum.valueOf(enumName);
                    if (contentTags.contains(tag)) {
                        currentProperty = tag;
                    }
                } catch (IllegalArgumentException e) {
                    logger.info("Unexpected value");
                }
        }


    }

    /**
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if (localName.equals(GEM) || localName.equals(FAMOUS_GEM)) {
            gems.add(currentGem);
        }
    }

    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    @Override
    public void characters(final char[] ch, final int start,
                           final int length) {
        String s = new String(ch, start, length).trim();
        if (currentProperty != null) {
            switch (currentProperty) {
                case NAME:
                    ((FamousGem) currentGem).setName(s);
                    break;
                case COLOR:
                    currentGem.getVisualParameters().setColor(s);
                    break;
                case VALUE:
                    currentGem.setValue(Float.parseFloat(s));
                    break;
                case ORIGIN:
                    currentGem.setOrigin(s);
                    break;
                case PRECIOUSNESS:
                    currentGem.setPreciousness(
                            Gem.Preciousness.valueOf(s));
                    break;
                case GEM_TYPE:
                    currentGem.setGemType(Gem.GemType.valueOf(s));
                    break;
                case TRANSPARENCY:
                    currentGem.getVisualParameters()
                            .setTransparency(Integer.parseInt(s));
                    break;
                case CUTTING_METHOD:
                    currentGem.getVisualParameters()
                            .setCuttingMethod(Integer.parseInt(s));
                    break;
                case FOUNDATION_DATE:
                    ((FamousGem) currentGem).setFoundationDate(s);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Returns gems.
     * @return gems
     */
    public Set<Gem> getGems() {
        return gems;
    }
}
