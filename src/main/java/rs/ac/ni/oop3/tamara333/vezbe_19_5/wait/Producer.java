package rs.ac.ni.oop3.tamara333.vezbe_19_5.wait;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Producer extends Thread {
    private final int value;
    private final SharedStorage storage;

    @Override
    public void run() {
        storage.set("Message " + value);
    }
}
