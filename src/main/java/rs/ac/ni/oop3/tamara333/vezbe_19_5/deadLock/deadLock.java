package rs.ac.ni.oop3.tamara333.vezbe_19_5.deadLock;



public class deadLock {
    public static void main(String[] args) throws InterruptedException {
        final String resource1 = "Resource 1";
        final String resource2 = "Resource 2";

        final Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("Starting t1");
                System.out.println("T1: Trying to lock " + resource1);

                synchronized (resource1) {
                    System.out.println("T1: " + resource1 + " locked" );
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("T1: Trying to lock " + resource2);

                    synchronized (resource2) {
                        System.out.println("T1: Both resources locked. Processing...");
                        System.out.println(resource1 + ":" + resource2);
                    }
                }
            }
        };

        final Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("Starting t2");
                System.out.println("T2: Trying to lock " + resource1);

                synchronized (resource2) {
                    System.out.println("T2: " + resource2 + " locked" );
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("T2: Trying to lock " + resource1);

                    synchronized (resource1) {
                        System.out.println("T1: Both resources locked. Processing...");
                        System.out.println(resource1 + ":" + resource2);
                    }
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();


    }
}
