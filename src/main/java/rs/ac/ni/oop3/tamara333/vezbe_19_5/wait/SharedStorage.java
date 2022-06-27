package rs.ac.ni.oop3.tamara333.vezbe_19_5.wait;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharedStorage {
    private String message;
    boolean empty = true;

    public synchronized String get() {
        log.info("Waiting to get message");
        while(empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Got the message");
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void set(final String message){
        log.info("Thread is waiting to set the message");
        while(!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Setting the message");
        empty = false;
        this.message = message;
        notifyAll();
    }
}
