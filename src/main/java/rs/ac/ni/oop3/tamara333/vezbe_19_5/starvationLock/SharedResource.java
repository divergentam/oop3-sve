package rs.ac.ni.oop3.tamara333.vezbe_19_5.starvationLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharedResource {

    public synchronized void testMethod(final String threadType){
        log.info("{} calling testMethod()... ", threadType);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
