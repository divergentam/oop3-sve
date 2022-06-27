package rs.ac.ni.oop3.tamara333.vezbe_19_5.starvationLock;

public class LowPriorityThread extends Thread{
    private final SharedResource sharedResource;
    private final String name;

    public LowPriorityThread(SharedResource sharedResource, String name) {
        this.sharedResource = sharedResource;
        this.name = name;
        this.setPriority(Thread.MIN_PRIORITY);
    }

    @Override
    public void run() {
        sharedResource.testMethod("LOW");
    }
}
