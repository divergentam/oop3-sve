package rs.ac.ni.oop3.tamara333.vezbe_19_5.wait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        final SharedStorage sharedStorage = new SharedStorage();

        final List<Producer> producers = new ArrayList<>();
        final List<Consumer> consumers = new ArrayList<>();

        IntStream.range(0, 3)
                        .forEach(i -> producers.add(new Producer(i, sharedStorage)));

        IntStream.range(0, 3)
                .forEach(i -> consumers.add(new Consumer(sharedStorage)));

        producers.forEach(Thread::start);
        consumers.forEach(Thread::start);

        for(Producer producer : producers){
            producer.join();
        }
        for(Consumer consumer : consumers)
        {
            consumer.join();
        }

    }
}
