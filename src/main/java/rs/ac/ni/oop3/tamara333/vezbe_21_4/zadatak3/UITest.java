package rs.ac.ni.oop3.tamara333.vezbe_21_4.zadatak3;

import java.io.IOException;

public class UITest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // final UnresponsiveUI ui = new UnresponsiveUI();
        // final ResponsiveUIDamonThread thread = new ResponsiveUIDeamonThread();
        final ResponsiveUI ui = new ResponsiveUI();
        ui.start();
        final WatcherThread watcherThread = new WatcherThread(ui, System.err);
        watcherThread.start();

      /*  for(int i= 0; i< 10; i++){

            Thread.sleep(300);
            System.out.println("d = " + ui.getD());
        }*/

        System.out.println("Press enter to finish");
        System.in.read();
        ui.interrupt();
        watcherThread.interrupt();
        ui.join();

        System.out.println("Finally d = " + ui.getD());


        // Greska je da se neko izracunavanje radi na istom threadu na kome se radi iscrtavanje interfejsa
    }
}
