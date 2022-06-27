package rs.ac.ni.oop3.tamara333.vezbe_19_5.starvationLock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class starvationLock {
    public static void main(String[] args) throws InterruptedException {
        final SharedResource sharedResource = new SharedResource();

        final List<Thread> threads = new ArrayList<>();

        IntStream.range(0, 10)
                .forEach(i -> threads.add(new HighPriorityThread(sharedResource, "HighPriorityThread " + i)));

        IntStream.range(0, 10)
                .forEach(i -> threads.add(new LowPriorityThread(sharedResource, "LowPriorityThread " + i)));

        threads.forEach(Thread::start);

        for(Thread t : threads){
            t.join();
        }

    }
}
