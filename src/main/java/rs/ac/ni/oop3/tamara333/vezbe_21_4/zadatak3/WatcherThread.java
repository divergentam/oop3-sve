package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak3;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
@Slf4j
public class WatcherThread extends Thread{
    private final ResponsiveUI uiThread;
    private final PrintStream printStream;

    public WatcherThread(final ResponsiveUI ui, final PrintStream printStream){
        setDaemon(true);
        uiThread = ui;
        this.printStream = printStream;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                /*log.info("Thread has been interrupted!");
                Thread.currentThread().interrupt();
                return;*/
            }
            printStream.printf("Current value: %f%n", uiThread.getD());
        }
    }
}
