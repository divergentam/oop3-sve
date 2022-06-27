package rs.ac.ni.oop3.tamara333.vezba7.zadatak2;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class ScramblerTask extends ConverterTask{

    public ScramblerTask(final CountDownLatch countDownLatch, final String value, final long timeout){
        super(countDownLatch, value, timeout);
    }

    @Override
    protected String getConverterName() {
        return "SCRAMBLER";
    }

    @Override
    protected String convert(String value) {
        final List<Character> chars = value.chars().mapToObj(c -> (char) c).toList();
        Collections.shuffle(chars);
        return chars.stream().map(String::valueOf).reduce("", (s, character) -> s + character);
    }
}
