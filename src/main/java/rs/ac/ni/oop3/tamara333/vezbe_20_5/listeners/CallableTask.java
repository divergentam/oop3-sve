package rs.ac.ni.oop3.tamara333.vezbe_20_5.listeners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
public class CallableTask implements Callable<Integer> {
    private final long timeout;
    @Getter
    private final int taskId;
    private final int a;
    private final int b;
    private int result;

    public interface MyCallableListener{
        public void onTaskFinished(int taskId, double result);
        public void onTaskFailed(int taskId, Throwable error);
    }

    private final MyCallableListener listener;

    @Override
    public Integer call() throws Exception {
        try {
            log.info("Task {}: Computing {}/{} (timeout: {})", taskId, a, b, timeout);
            Thread.sleep(timeout);
            result = a / b;
            // obavestavamo da je nas task zavrsen
            listener.onTaskFinished(getTaskId(), result);
            return result;
        } catch(Exception e){
            listener.onTaskFailed(getTaskId(), e);
            throw e;
        }

    }
}
