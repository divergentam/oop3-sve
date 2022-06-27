package rs.ac.ni.oop3.tamara333.vezba7.zadatak5.semaphore;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HikingDemo {
    public static void main(String[] args) throws InterruptedException {
        final List<HikingField2> hikingFields = List.of(
                new HikingField2(1),
                new HikingField2(2),
                new HikingField2(2),
                new HikingField2(2),
                new HikingField2(10)
        );

        final List<SemHiker> hikers = List.of(
                new SemHiker(hikingFields, "Pera", 4, 1000, 100),
                new SemHiker(hikingFields, "Zika", 3, 800, 500),
                new SemHiker(hikingFields, "Laza", 0, 200, 100)
        );

        final ExecutorService executorService = Executors.newCachedThreadPool();
        hikers.forEach(executorService::submit);

        executorService.shutdown();
    }
}
