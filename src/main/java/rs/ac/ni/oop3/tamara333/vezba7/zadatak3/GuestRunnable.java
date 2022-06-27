package rs.ac.ni.oop3.tamara333.vezba7.zadatak3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;
import rs.ac.ni.oop3.tamara333.vezbe_19_5.wait.WaitNotifyDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@Slf4j
@RequiredArgsConstructor
public class GuestRunnable implements Runnable{
    private final CyclicBarrier barrier;
    private final CountDownLatch countDownLatch;
    private final String name;
    private final long appetizerTimeout;
    private final long mainCourseTimeout;
    private final long desertTimeout;

    @Override
    public void run() {
        log.info("Guest {} starting the appetizer", name);
        WaitUntil.sleep(appetizerTimeout);
        log.info("Guest {} finished appetizer", name);

        try {
            log.info("Guest {} is waiting for others to finish appetizer", name);
            int toWait = barrier.await();
            if (toWait == 0){
                barrier.reset();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        log.info("Guest {} starting the main course", name);
        WaitUntil.sleep(mainCourseTimeout);
        log.info("Guest {} finished main course", name);

        try {
            log.info("Guest {} is waiting for others to finish main course", name);
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        log.info("Guest {} starting the desert", name);
        WaitUntil.sleep(desertTimeout);
        log.info("Guest {} finished desert", name);

        countDownLatch.countDown();
    }
}
