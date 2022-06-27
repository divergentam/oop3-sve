package rs.ac.ni.oop3.tamara333.vezba7.zadatak3.zadatak4;

import java.util.concurrent.*;

public class RestaurantDemo {
    public static void main(String[] args) throws InterruptedException {
        final int numOfGuests = 3;

        final ExecutorService guestExecutor = Executors.newCachedThreadPool();
        final ScheduledExecutorService waiterExecutor = Executors.newScheduledThreadPool(5);
        final CountDownLatch countDownLatch = new CountDownLatch(numOfGuests);
        final ExecutorService chiefExecutor = Executors.newSingleThreadScheduledExecutor();

        final CyclicBarrier barrier= new CyclicBarrier(numOfGuests);
        final Semaphore chiefSemaphore = new Semaphore(0);
        final Semaphore waiterSemaphore = new Semaphore(0);
        final Semaphore guestSemaphore = new Semaphore(0);

        guestExecutor.submit(new Guests(barrier, guestSemaphore, "Pera", countDownLatch, 1000, 1500, 500));
        guestExecutor.submit(new Guests(barrier, guestSemaphore, "Mika", countDownLatch, 1500, 2000, 800));
        guestExecutor.submit(new Guests(barrier, guestSemaphore, "Zika", countDownLatch, 800, 1500, 400));

        waiterExecutor.scheduleWithFixedDelay(new Waiter(barrier, numOfGuests / 2, chiefSemaphore, guestSemaphore, waiterSemaphore ), 1000, 1000, TimeUnit.MILLISECONDS);
        chiefExecutor.submit(new Chief(chiefSemaphore, waiterSemaphore, 5000));

        countDownLatch.await();
        guestExecutor.shutdown();
        waiterExecutor.shutdown();
        chiefExecutor.shutdown();
    }
}
