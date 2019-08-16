package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.dao.exception.DAOException;
import by.training.task3composite.dao.reader.FileStringReader;
import by.training.task3composite.service.exception.ServiceException;
import by.training.task3composite.service.parser.LexemeParser;
import by.training.task3composite.service.parser.ParagraphParser;
import by.training.task3composite.service.parser.SentenceParser;
import by.training.task3composite.service.parser.TextParser;
import by.training.task3composite.service.parser.WordParser;

import java.util.Optional;

public class ParseTextFromFileService {
    /**
     * Exception message.
     */
    private static final String EXC_MSG = "Invalid file name. ";

    /**
     * Parses text from file.
     * @param fileName name of file with text to parse
     * @return Text
     * @throws ServiceException if something goes wrong
     */
    public TextComposite parseTextFromFile(final String fileName)
            throws ServiceException {
        try {
            FileStringReader fileStringReader = new FileStringReader();
            String fileContent = fileStringReader.readFromFile(
                    Optional.ofNullable(fileName).orElseThrow(
                            () -> new ServiceException(EXC_MSG)));
            TextComposite text = new TextComposite(ComponentType.TEXT);
            TextParser textParser = new TextParser();
            ParagraphParser paragraphParser = new ParagraphParser();
            SentenceParser sentenceParser = new SentenceParser();
            LexemeParser lexemeParser = new LexemeParser();
            WordParser wordParser = new WordParser();
            textParser.setSuccessor(paragraphParser);
            paragraphParser.setSuccessor(sentenceParser);
            sentenceParser.setSuccessor(lexemeParser);
            lexemeParser.setSuccessor(wordParser);
            textParser.parse(text, fileContent);
            return text;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
