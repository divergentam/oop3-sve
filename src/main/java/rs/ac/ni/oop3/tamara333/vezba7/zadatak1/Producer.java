package rs.ac.ni.oop3.tamara333.vezba7.zadatak1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
public class Producer implements Runnable{
    private final BlockingQueue<Integer> storage;
    private final int limit;

    @Override
    public void run() {
        for(long count = limit; count > 0; count --){
            int product = createProduct();
            try {
                storage.put(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("PRODUCER: Production completed");
        try {
            storage.put(-1); // oznaka da nece da proizvodi vise nove elemente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private int createProduct() {
        final int timeout = ThreadLocalRandom.current().nextInt(10); // klasa koja omogucava da generisemo slucajan
        // broj ali u smislu da svaki thread ima svoj posebni rendom generator
        final int value = ThreadLocalRandom.current().nextInt(100) + 1;
        log.info("PRODUCER: Producing value {}", value);
        WaitUntil.sleep(timeout * 100); // izmedju 0 i 900 milliseconds

        log.info("PRODUCER: Value {} created", value);
        return value;
    }
}
