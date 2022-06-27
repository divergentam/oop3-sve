package rs.ac.ni.oop3.tamara333.vezba7.zadatak1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
@Slf4j
public class Consumer implements Runnable{
    final BlockingQueue<Integer> storage;

    @Override
    public void run() {
        try{
            log.info("CONSUMER: Waiting for next value...");
            int value = storage.take();
            while(value != -1){
                log.info("CONSUMER: Value {} obtained", value);
                processValue(value);

                value = storage.take(); // na kraju izmamo novu vrednost iz queue-a
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("CONSUMER: Finished");
    }

    private void processValue(final int value) {
        System.out.println("Processing value " + value);
        WaitUntil.sleep(500);
    }
}
