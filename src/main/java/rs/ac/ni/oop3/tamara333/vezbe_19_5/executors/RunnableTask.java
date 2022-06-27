package rs.ac.ni.oop3.tamara333.vezbe_19_5.executors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class RunnableTask implements Runnable{

    private final int a;
    private final int b;
    private double result;
    private Exception e;

    @Override
    public void run() {
        log.info("Computing {}/{}", a, b);
        try{
             result = (double)a/b;
        }catch(ArithmeticException e){
            this.e = e;
        }
    }
}
