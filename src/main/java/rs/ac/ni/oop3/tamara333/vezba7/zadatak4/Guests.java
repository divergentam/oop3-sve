package rs.ac.ni.oop3.tamara333.vezba7.zadatak3.zadatak4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

@Slf4j
@RequiredArgsConstructor
public class Guests implements Runnable{
    private final CyclicBarrier barrier;
    private final Semaphore waiterSemaphore;
    private final String name;
    final CountDownLatch countDownLatch;
    private final long appetizerTimeout;
    private final long mainCourseTimeout;
    private final long desertTimeout;

    @Override
    public void run() {
        log.info("Guest {}: starting the appetizer", name);
        WaitUntil.sleep(appetizerTimeout);
        log.info("Guest {}: finished appetizer", name);

        try {
            int toWait = barrier.await();
            if(toWait == 0){
                barrier.reset();
                log.info("Parties: {}", barrier.getParties());
            }
            waiterSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        log.info("Guest {}: starting the mainCourse", name);
        WaitUntil.sleep(mainCourseTimeout);
        log.info("Guest {}: finished mainCourse", name);

        try {
            log.info("Guest {} is waiting on second barrier", name);
            barrier.await();
            log.info("Guest {} is waiting for second meal", name);
            waiterSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        log.info("Guest {}: starting the desert", name);
        WaitUntil.sleep(desertTimeout);
        log.info("Guest {}: finished desert", name);

        countDownLatch.countDown();
    }
}
