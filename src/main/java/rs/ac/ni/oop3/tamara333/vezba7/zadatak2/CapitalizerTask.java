package rs.ac.ni.oop3.tamara333.vezba7.zadatak2;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class CapitalizerTask extends ConverterTask{

    public CapitalizerTask(final CountDownLatch countDownLatch, final String value, final long timeout){
        super(countDownLatch, value, timeout);
    }

    @Override
    protected String getConverterName() {
        return "CAPITALIZER";
    }

    @Override
    protected String convert(String value) {
        return value.toUpperCase(Locale.ROOT);
    }
}
