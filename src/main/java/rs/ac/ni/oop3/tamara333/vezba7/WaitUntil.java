package rs.ac.ni.oop3.tamara333.vezba7;

public class WaitUntil {
    public static void sleep(final long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
