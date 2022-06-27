package rs.ac.ni.oop3.tamara333.vezba7.zadatak2;

import java.util.concurrent.CountDownLatch;

public class ReverserTask extends ConverterTask{
    public ReverserTask(final CountDownLatch countDownLatch, final String value, final long timeout){
        super(countDownLatch, value, timeout);
    }

    @Override
    protected String getConverterName() {
        return "REVERSER";
    }

    @Override
    protected String convert(String value) {
        return new StringBuilder(value).reverse().toString();
    }
}
