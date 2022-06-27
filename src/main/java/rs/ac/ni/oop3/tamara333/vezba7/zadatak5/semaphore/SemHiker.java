package rs.ac.ni.oop3.tamara333.vezba7.zadatak5.semaphore;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.List;

@Slf4j
public class SemHiker implements Runnable {
    private final List<HikingField2> fields;
    private int currentField;
    private final long timeout;
    private final long climbTimeout;
    private final String name;

    public SemHiker(List<HikingField2> fields, final String name, final int startingField, final long timeout, long climbTimeout) throws InterruptedException {
        this.fields = fields;
        this.name = name;
        this.currentField = startingField;
        this.timeout = timeout;
        this.climbTimeout = climbTimeout;


        log.info("Hiker {} is initial on field {}", name, currentField);
        fields.get(currentField).allowNextHiker();
    }

    @Override
    public void run() {
        while(currentField < fields.size() - 1){
            log.info("Hiker {} is currently waiting on field {}", name, currentField);
            WaitUntil.sleep(timeout);

            //free current field
            fields.get(currentField).removeHiker();
            currentField++;
            log.info("Hiker {}: is climbing to {} field (timeout: {})", name, currentField, timeout);
            WaitUntil.sleep(climbTimeout);
            try {
                fields.get(currentField).allowNextHiker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Hiker {}: is on {} field", name, currentField);
        }
        System.err.println("Hiker " + name +  ": has finished climbing!");
    }
}
