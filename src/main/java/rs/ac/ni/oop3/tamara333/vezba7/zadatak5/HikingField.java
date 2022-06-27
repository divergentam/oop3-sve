package rs.ac.ni.oop3.tamara333.vezba7.zadatak5;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HikingField {
    private final BlockingQueue<String> queue;

    public HikingField(final int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public BlockingQueue<String> getQueue(){
        return queue;
    }
}
