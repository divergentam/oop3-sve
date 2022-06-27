package rs.ac.ni.oop3.tamara333.vezba6.zadatak5;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class AlphabetParallel {

    public static void main(String[] args) throws InterruptedException {
        final String word = "Otorinolarngfnfndngdngdhtrhyrjtingolog";

        final int T = 5;
        final int GROUP_SIZE = word.length() / T;
        final int EXCESS = word.length() % T;


        List<AlphabetWorker> workerList = new ArrayList<>();
        IntStream.range(0, T-1)
                .forEach(i -> workerList.add(new AlphabetWorker(word.substring(i * GROUP_SIZE, GROUP_SIZE * (i + 1)))));

        IntStream.range(T -1, T)
                .forEach(i -> workerList.add(new AlphabetWorker(word.substring(i * GROUP_SIZE, GROUP_SIZE * (i + 1) + EXCESS))));

        workerList.forEach(Thread::start);
        for(AlphabetWorker worker : workerList){
            worker.join();
        }

        Map<Character, Integer> result = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();

        for(AlphabetWorker worker : workerList){
            map = worker.getResult();
            map.forEach((i,j) -> result.put(i,0));
        }
        for(AlphabetWorker worker : workerList){
            map = worker.getResult();
            map.forEach((i,j) -> result.put(i, result.get(i) + j));
        }

        result.forEach((i,j) -> System.out.println("key: " + i + "\tvalue: " + j));
    }

}
