package rs.ac.ni.oop3.tamara333.vezbe_17_3.slf4j;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j

public class Slf4jDemo {

    public static void main(String[] args) {
        log.error("Erorr");
        log.warn("Warn");
        log.info("Info");
        log.debug("Debug");
        log.trace("Trace");
    }
}
