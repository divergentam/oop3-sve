package rs.ac.ni.oop3.tamara333.vezbe_20_5.listeners;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MyListeners implements CallableTask.MyCallableListener{
    public static void main(String[] args) {
        new MyListeners().doDemo();
    }

    public void doDemo(){
       final ExecutorService executorService = Executors.newFixedThreadPool(2);
       executorService.submit(new CallableTask(100, 1, 4, 2, this));
       executorService.submit(new CallableTask(100, 2, 4, 0, this));
       executorService.shutdown();
    }

    @Override
    public void onTaskFinished(int taskId, double result) {
        log.info("Task {}: Result - {}", taskId, result);
    }

    @Override
    public void onTaskFailed(int taskId, Throwable error) {
        log.error("Task {}: Error {}", taskId, error.getMessage());
    }
}
