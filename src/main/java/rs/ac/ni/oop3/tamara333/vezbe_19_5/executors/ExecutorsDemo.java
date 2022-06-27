package rs.ac.ni.oop3.tamara333.vezbe_19_5.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class ExecutorsDemo {
    public static void main1(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor(); // executor koji sve izvrsava na jednom thr
        //executorService.submit()

      /*  RunnableTask task = new RunnableTask(10, 2);
        final Future<?> future = executorService.submit(task);
        executorService.submit(new RunnableTask(9, 2));
        executorService.submit(new RunnableTask(8, 2));
        executorService.submit(new RunnableTask(7, 2));

        executorService.shutdown(); - executor service ceka na nove taskove dok se ne napise ova linija
        koja zabranuje dodavanje novih taskova
*/
        // (Runnable command) i (Callable<> task) - vracaju Future objekat i to je nacin da se vrati
        // Runnable ne moze nista da vrati i nemamo gde da uhvatimo gresku ako baci
        // dok Callable moze da vrati nesto i baca gresku

        final  Future<Integer> future = executorService.submit(new CallableTask(50, 1, 4, 3));

        executorService.shutdown();

        log.info("All tasks submitted");

        Integer value = null;

        while (!future.isDone()) {
            log.info("Waiting for task to complete...");
            try {
                value = future.get(50, TimeUnit.MILLISECONDS); // blokirajci metod jer je thr zaglavljen dok se ne zavrsi task
            } catch (ExecutionException e) {
                System.err.println("Task failed " + e.getMessage());
                return;
            } catch (TimeoutException e) {
                log.error("Not done yet...");
            }
        }

        if(value == null){
            try {
                value = future.get();
            } catch (ExecutionException e) {
                System.err.println("Task failed " + e.getMessage());
            }
        }

        System.out.println("Result " + value);

/*
        try {
            System.out.println("Result " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getCause().getMessage());
        }*/

    }

    public static void main2(String[] args) throws InterruptedException {
        int coreNum = Runtime.getRuntime().availableProcessors();
        System.out.println("Core number : " + coreNum);
        final Random random = new Random();
        /*
        * Klasa Executors ima factory metode za kreiranje thread pool-ova:
        *   newFixedThreadPool() - napravi tacno odredjeni broj thredova i sve zad ce na njima da ih izvrsava
        *   newCachedThreadPool() - korisan ukoliko se izvrsava puno kratkih zadataka - on nema fixan broj thredova
        * nego u pocetku napravi jedan ili dva thread-a i zatim svaki put kad naidje zadatak za koji nemamo slobodan
        * thread onda se pravi novi thread
        *   newScheduledThreadPool()
        * */

        final ExecutorService service = Executors.newFixedThreadPool(coreNum > 1 ? coreNum-1 : 1);

        final List<Future<Integer>> futures = new ArrayList<>();

        for(int i = 0; i< 100; i++){
            final Future<Integer> future = service
                    .submit(new CallableTask(
                            100* random.nextInt(10 + 1),
                             i ,
                            random.nextInt(100),
                            random.nextInt(100)));
            futures.add(future);
        }

        service.shutdown();

        for(final Future<Integer> future : futures){
            try {
                double result = future.get();
                //System.out.println(result);
            }catch (ExecutionException e) {
                log.error(e.getCause().getMessage());
            }
        }

    }

    public static void main3(String[] args) {
        final Random random = new Random();
        final ExecutorService service = Executors.newCachedThreadPool();

        final List<Future<Integer>> futures = new ArrayList<>();

        for(int i = 0; i< 100; i++){
            sleep(100);
            final Future<Integer> future = service
                    .submit(new CallableTask(
                            100* random.nextInt(10 + 1),
                             i ,
                            random.nextInt(100),
                            random.nextInt(100)));
            futures.add(future);
        }


        service.shutdown();
    }

    public static void main4(String[] args) {
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//        executorService.scheduleAtFixedRate(1h) - da pocne na svakih sat vremena
//          ako ne zelimo da se poslovi preklope bolja opcija:
//        executorService.scheduleWithFixedDelay(n) - da se pokrecu sa odredjenim razmakom kad prvi zavrsi pokreni drugi
//          nakon n vremena
        log.info("Scheduling task");
        final ScheduledFuture<Integer> scheduledFuture =
                executorService.schedule(new CallableTask(100, 1, 3, 4),
                        3, TimeUnit.SECONDS);

        executorService.
                scheduleAtFixedRate(()->log.info("[{}] Checking...", Thread.currentThread().isDaemon()), 1, 1, TimeUnit.SECONDS);


        Integer result = null;
        try {
            result = scheduledFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        log.info("Result: {}", result);

        executorService.shutdown();

    }

    private static void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
