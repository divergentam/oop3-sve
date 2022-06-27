package rs.ac.ni.oop3.tamara333.vezbe_17_3.logging;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CustomFilter implements Filter {

    private static final String PREFIX = "FILE:";

    @Override
    public boolean isLoggable(LogRecord record) {
        final String message = record.getMessage();

        if(message == null){
            return false;
        }
    return message.startsWith(PREFIX);
    }
}
