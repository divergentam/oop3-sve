package rs.ac.ni.oop3.tamara333.vezba7.zadatak5.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class HikingField2 {
    private final Semaphore semaphore;

    public HikingField2(final int capacity) {
        semaphore = new Semaphore(capacity);
    }

    public void allowNextHiker() throws InterruptedException {
        semaphore.acquire(); // od semaphora potrazi jednu dozvolu
    }

    public void removeHiker() {
        semaphore.release(); // povecava broj dozvola za dati semaphore;
    }

}
