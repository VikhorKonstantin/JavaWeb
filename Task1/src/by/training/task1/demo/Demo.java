package by.training.task1.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Class that demonstrate the work with Log4J2.
* */
public final class Demo {
    private Demo() {

    }
    /**
     * Main method that just print Hello world.
     * @param args an array of arguments
     * */
    public static  void main(final String[] args) {
        Logger logger = LogManager.getLogger("MyLogger");
        logger.info("Hello World");
    }
}
