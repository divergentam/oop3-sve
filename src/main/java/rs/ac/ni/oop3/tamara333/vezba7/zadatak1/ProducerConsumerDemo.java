package rs.ac.ni.oop3.tamara333.vezba7.zadatak1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        final BlockingQueue<Integer> storage = new ArrayBlockingQueue<>(2);

        new Thread(new Producer(storage, 10)).start();
        new Thread(new Consumer(storage)).start();
    }
}
