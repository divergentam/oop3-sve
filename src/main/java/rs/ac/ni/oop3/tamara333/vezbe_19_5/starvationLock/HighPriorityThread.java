package rs.ac.ni.oop3.tamara333.vezbe_19_5.starvationLock;

import lombok.RequiredArgsConstructor;


public class HighPriorityThread extends Thread{
    private final SharedResource sharedResource;
    private final String name;

    public HighPriorityThread(SharedResource sharedResource, String name) {
        this.sharedResource = sharedResource;
        this.name = name;

        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        sharedResource.testMethod("HIGH");
    }
}
