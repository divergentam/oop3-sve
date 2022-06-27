package rs.ac.ni.oop3.tamara333.vezba7.zadatak5;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezba7.WaitUntil;

import java.util.List;

@Slf4j
public class Hiker implements Runnable {
    private final List<HikingField> fields;
    private int currentField;
    private final long timeout;
    private final String name;

    public Hiker(List<HikingField> fields, final String name, final int startingField, final long timeout) {
        this.fields = fields;
        this.name = name;
        this.currentField = startingField;
        this.timeout = timeout;

        fields.get(currentField).getQueue().add(name);
    }

    @Override
    public void run() {
        while(currentField < fields.size() - 1){
            //free current field
            fields.get(currentField).getQueue().remove();
            currentField++;
            log.info("Hiker {}: is climbing to {} field (timeout: {})", name, currentField, timeout);
            WaitUntil.sleep(timeout);
            try {
                fields.get(currentField).getQueue().put(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Hiker {}: is on {} field", name, currentField);
        }
        log.info("Hiker {}: has finished climbing!", name);
    }
}
