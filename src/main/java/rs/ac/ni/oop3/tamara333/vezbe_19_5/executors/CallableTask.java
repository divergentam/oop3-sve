package rs.ac.ni.oop3.tamara333.vezbe_19_5.executors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
public class CallableTask implements Callable<Integer> {
    private final long timeout;
    private final int taskId;
    private final int a;
    private final int b;

    @Override
    public Integer call() throws Exception {
        log.info("[{}] Task {} --- Computing {}/{} (timeout: {}) ",Thread.currentThread().isDaemon(), taskId, a, b, timeout);
        Thread.sleep(timeout);
        return a / b;
    }
}
