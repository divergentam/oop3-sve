package rs.ac.ni.oop3.tamara333.vezba7.zadatak3.zadatak4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.concurrent.Semaphore;

@Slf4j
@RequiredArgsConstructor
public class Chief implements Runnable{
    private final Semaphore chiefSemaphore;
    private final Semaphore waiterSemaphore;
    private final long mealTimeout;

    @Override
    public void run() {
        int counter = 1;
        while(counter <= 2) {
            try {
                chiefSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WaitUntil.sleep(mealTimeout);
            log.info("Chief: I'm notifying waiter that meals are made");
            waiterSemaphore.release();
            counter++;
        }
    }
}
