package rs.ac.ni.oop3.tamara333.vezbe_20_5.guava;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.oop3.tamara333.vezbe_19_5.executors.CallableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ListenableFutureDemo implements FutureCallback<Integer> {
    public static void main(String[] args) {
       // new ListenableFutureDemo().doDemo();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        final ExecutorService exitingExecutorService =
            MoreExecutors.getExitingExecutorService(executor, 50, TimeUnit.MILLISECONDS);

        for(int i =0; i<10; i++){
            exitingExecutorService.submit(new CallableTask(100 * i, i, 2 * i, i));
        }

        double sum = 0.0;

        log.info("Doing some complicated stuff...");
        for(int i = 0; i< 10000000; i++){
            if(i % 1000000 == 0){
                log.info("Still working...");
            }
            sum += Math.sqrt(Math.log(i));
        }

        log.info("Done!");

    }

    private void doDemo() {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final ListeningExecutorService listeningExecutorService =
                MoreExecutors.listeningDecorator(executorService);

        final ListenableFuture<Integer> listenableFuture =
                listeningExecutorService.submit(new CallableTask(100, 1, 5, 2));

        Futures.addCallback(listenableFuture, this, executorService);

        double sum = 0.0;

        log.info("Doing some complicated stuff...");
        for(int i = 0; i< 100000000; i++){
            if(i % 10000000 == 0){
                log.info("Still working...");
            }
            sum += Math.sqrt(Math.log(i));
        }

        log.info("Done!");

        listeningExecutorService.shutdown();
    }

    @Override
    public void onSuccess(final Integer result) {
        log.info("Result {} ", result);
    }

    @Override
    public void onFailure(final Throwable error) {
        log.error("Error {} ", error);
    }
}
