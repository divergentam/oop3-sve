package rs.ac.ni.oop3.tamara333.vezbe_31_3.zadatak1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.ac.ni.oop3.tamara333.vezbe_31_3.FileDownloader;

import java.io.IOException;
import java.util.List;

class FileDownloaderTest {
    @Test
    public void shouldComputerDo() throws IOException {
        final FileDownloader fileDownloader= new FileDownloader();

        final List<String> lines =
                fileDownloader.readLines("D:\\II god\\Objektno orjentisano programiranje 3\\03.03. - Figure\\src\\main\\resources\\log4j.properties");

        final List<String> trueResult = List.of("log4j.rootLogger=DEBUG,stdout",
                "log4j.appender.stdout=org.apache.log4j.ConsoleAppender",
                "log4j.appender.stdout.layout=org.apache.log4j.PatternLayout",
                "log4j.appender.stdout.layout.ConversionPattern=%p\\t%d{yyyy-MM-dd HH:mm:ss}\\t%r\\t%c\\t[%t]\\t%m%n");

        Assertions.assertEquals(lines, trueResult);

    }
}