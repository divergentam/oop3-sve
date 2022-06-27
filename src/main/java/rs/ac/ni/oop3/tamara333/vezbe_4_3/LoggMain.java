package rs.ac.ni.oop3.tamara333.vezbe_4_3;


import java.io.IOException;
import java.util.logging.*;

public class LoggMain {

    private static Logger logger = Logger.getLogger(LoggMain.class.getName());

    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.ALL);

        Handler xmlhandler = new FileHandler("log.xml", false);
        xmlhandler.setLevel(Level.FINE);
        xmlhandler.setFormatter(new XMLFormatter());

        Handler consoleh = new ConsoleHandler();
        consoleh.setLevel(Level.ALL);

        logger.setUseParentHandlers(false);
        logger.addHandler(xmlhandler);
        logger.addHandler(consoleh);

        logger.log(Level.INFO, "Hello from logger"); // logger.info("Hello from logger");
        logger.severe("Hello from logger");
        logger.warning("Hello from logger");
        logger.config("Hello from logger");
        logger.fine("Hello from logger");
        logger.finer("Hello from logger");
        logger.finest("Hello from logger");
    }
}
