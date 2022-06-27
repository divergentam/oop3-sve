package rs.ac.ni.oop3.tamara333.vezbe_17_3.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class MyLoggerVezba4  {
    private final static Logger logger;
    static{
        final InputStream is = MyLoggerVezba4.class.getClassLoader()
                        .getResourceAsStream("logging.properties");

        if( is != null){
            try {
                LogManager.getLogManager().readConfiguration(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("Logging configuration not found in resources!");
        }

        logger = Logger.getLogger(MyLoggerVezba4.class.getName());

    }
    public static void main(String[] args) throws IOException {

        logger.severe("FILE: A big problem");
        logger.info("FILE: Some info");
        logger.fine("FILE: Fine message");
        logger.finest("FILE: The finest message");



        /*logger.setLevel(Level.ALL);

        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        Handler txtHandler = new FileHandler("log.txt",false);
        logger.setLevel(Level.ALL);
        txtHandler.setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                return record.getMessage().startsWith("FILE:" );
            }
        });

        Handler xmlHandler = new FileHandler("log.xml", false);
        logger.setLevel(Level.SEVERE);
        xmlHandler.setFormatter(new XMLFormatter());

        Handler htmlHandler = new FileHandler("log.html", false);
        logger.setLevel(Level.ALL);
        htmlHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return "";
            }
        });
*/
    }
}
