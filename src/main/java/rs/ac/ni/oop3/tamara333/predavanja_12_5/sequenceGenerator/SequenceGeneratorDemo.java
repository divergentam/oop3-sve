package rs.ac.ni.oop3.tamara333.predavanja_12_5.sequenceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SequenceGeneratorDemo {
    /*private final SequenceGenerator _sequenceGenerator = new UnsafeSequenceGenerator();*/
    private final SequenceGenerator _sequenceGenerator = new SafeSequenceGenerator();


    private void generateNumbers() throws InterruptedException {
       /* for(int i =0; i<10; i++){
            int value = _sequenceGenerator.getAndIncrease();
            System.out.println(value);
        }*/
        final List<SequenceWorker> workers = new ArrayList<>();
        IntStream.range(0, 10)
                .forEach(i -> workers.add(new SequenceWorker(_sequenceGenerator, "Worker_" + i, 10)));

        workers.forEach(Thread::start);
        for(SequenceWorker worker : workers){
            worker.join();
        }

        System.out.println("Done");

    }

    public static void main(String[] args) throws InterruptedException {
        new SequenceGeneratorDemo().generateNumbers();
    }

}
