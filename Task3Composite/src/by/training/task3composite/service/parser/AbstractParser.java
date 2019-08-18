package by.training.task3composite.service.parser;

import by.training.task3composite.bean.entity.TextComponent;

public abstract class AbstractParser {
    /**
     * Successor. Next part of chain of responsibility.
     */
    private AbstractParser successor;

    /**
     * Returns successor.
     *
     * @return successor
     */
    AbstractParser getSuccessor() {
        return successor;
    }

    /**
     * Sets this.successor into newSuccessor-value.
     *
     * @param newSuccessor new successor
     */
    public void setSuccessor(final AbstractParser newSuccessor) {
        successor = newSuccessor;
    }

    /**
     * Parse stringToParse so we can newComponent as a result.
     *
     * @param newComponent  component which should hold data of parsed string
     * @param stringToParse string that should be parsed
     */
    public abstract void parse(TextComponent newComponent,
                               String stringToParse);
}
