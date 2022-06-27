package rs.ac.ni.oop3.tamara333.vezbe_19_5.wait;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Consumer extends Thread{
    private final SharedStorage storage;

    @Override
    public void run() {
        log.info(storage.get());
    }
}
