package rs.ac.ni.oop3.tamara333.vezba7.zadatak2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@RequiredArgsConstructor
@Slf4j
public abstract class ConverterTask implements Callable<String> {
    private final CountDownLatch countDownLatch;
    private final String value;
    private final long timeout;

    @Override
    public String call() throws Exception {
        log.info("Converting the value with converter {}  (timeout {})", getConverterName(), timeout);
        final String convertedValue = convert(value);
        try{
            Thread.sleep(timeout);
        }
        catch (InterruptedException e){
            log.warn("{} - Conversion abandoned", getConverterName());
            throw e;
        }
        log.info("{} - Countdown", getConverterName());
        countDownLatch.countDown();
        log.info("{} - Converted value: {}", getConverterName(), convertedValue);
        return convertedValue;
    }

    protected abstract String getConverterName();
    protected abstract String convert(final String value);
}
