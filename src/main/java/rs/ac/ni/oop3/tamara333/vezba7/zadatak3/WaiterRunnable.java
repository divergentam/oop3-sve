package rs.ac.ni.oop3.tamara333.vezba7.zadatak3;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

@Slf4j
@RequiredArgsConstructor
public class WaiterRunnable implements Runnable{
    private final CyclicBarrier barrier;
    private final int limit;

    @Override
    public void run() {
        log.info("Waiter is checking if enough guests have finished the meal");
        if(barrier.getNumberWaiting() >= limit){
            System.err.println("Informing the cook to prepare the next meal");
        }
    }
}
