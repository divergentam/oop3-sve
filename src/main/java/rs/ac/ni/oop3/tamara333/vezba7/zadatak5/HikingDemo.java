package rs.ac.ni.oop3.tamara333.vezba7.zadatak5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HikingDemo {
    public static void main(String[] args) {
        final List<HikingField> hikingFields = List.of(
                new HikingField(1),
                new HikingField(1),
                new HikingField(1),
                new HikingField(1),
                new HikingField(4),
                new HikingField(2),
                new HikingField(5),
                new HikingField(10)
        );

        final List<Hiker> hikers = List.of(
          new Hiker(hikingFields, "Pera", 5, 1000),
          new Hiker(hikingFields, "Zika", 3, 800),
          new Hiker(hikingFields, "Laza", 0, 200)
        );

        final ExecutorService executorService = Executors.newCachedThreadPool();
        hikers.forEach(executorService::submit);

        executorService.shutdown();
    }
}
