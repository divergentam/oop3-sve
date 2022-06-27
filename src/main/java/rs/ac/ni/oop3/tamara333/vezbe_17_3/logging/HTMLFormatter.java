package rs.ac.ni.oop3.tamara333.vezbe_17_3.logging;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HTMLFormatter extends Formatter {
    @Override
    public String getHead(Handler h) {
        return "<table border='1'>" +
                    "<tr>" +
                        "<th>LEVEL</th>" +
                        "<th>DATE/TIME</th>" +
                        "<th>MESSAGE</th>" +
                    "</tr>";
    }

    @Override
    public String format(LogRecord record) {
        final String level = record.getLevel().getName();
        final String message = record.getMessage();
        final LocalDateTime time = LocalDateTime.ofInstant(record.getInstant(), ZoneId.of("UTC"));

        return String.format(
                "<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                level,
                time.format(DateTimeFormatter.ISO_DATE),
                message);

    }

    @Override
    public String getTail(Handler h) {
        return "</table>";
    }
}
