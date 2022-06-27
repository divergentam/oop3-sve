package rs.ac.ni.oop3.tamara333.vezba7.zadatak3;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RestaurantDemo {
    public static void main(String[] args) throws InterruptedException {
        final int numberOfGuests = 3;

        final ExecutorService executorService = Executors.newCachedThreadPool();
        final ScheduledExecutorService waiterService = Executors.newSingleThreadScheduledExecutor();
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        final List<GuestRunnable> guests = new ArrayList<>();
        final CyclicBarrier barrier = new CyclicBarrier(numberOfGuests);

        guests.add(new GuestRunnable(barrier, countDownLatch, "Mika", 1000, 1500, 1000));
        guests.add(new GuestRunnable(barrier, countDownLatch, "Pera", 3000, 1000, 500));
        guests.add(new GuestRunnable(barrier, countDownLatch,"Laza", 2000, 1000, 1000));

        waiterService.scheduleAtFixedRate(new WaiterRunnable(barrier, numberOfGuests / 2), 200, 200, TimeUnit.MILLISECONDS);

        for(GuestRunnable guest : guests){
            executorService.submit(guest);
        }

        countDownLatch.await();

        executorService.shutdown();
        waiterService.shutdown();
    }
}
