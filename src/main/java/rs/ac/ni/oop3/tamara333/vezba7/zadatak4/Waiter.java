package rs.ac.ni.oop3.tamara333.vezba7.zadatak3.zadatak4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

@Slf4j
@RequiredArgsConstructor
public class Waiter implements Runnable{
    final CyclicBarrier barrier;
    final int limit;
    final Semaphore notifyChief;
    final Semaphore guestNotify;
    final Semaphore waiterSemaphore;


    @Override
    public void run() {
        log.info("Waiter: I'm checking if enough guests have finished the meal ({})", barrier.getNumberWaiting());
        if(barrier.getNumberWaiting() >= limit){
            log.debug("Waiter: I'M NOTIFYING CHIEF TO PREPARE NEXT MEAL");
            notifyChief.release();
            try {
                log.info("Waiter: Waiting for chief to prepare new meals");
                waiterSemaphore.acquire();
                log.info("Waiter: serve meals");
                guestNotify.release(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
