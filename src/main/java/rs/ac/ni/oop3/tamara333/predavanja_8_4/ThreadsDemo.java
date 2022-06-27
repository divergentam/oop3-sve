package rs.ac.ni.oop3.tamara333.predavanja_8_4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadsDemo {
    // postoje situacije kada je lose da se posao paralelizije jer uspori rad
    // Thread omogucava - rezervisanje resursa za novi proces koji ce biti naprtavljen
    //- pokretanje Threada i rad sa njim

    //Imamp glavni Thread
    public static void main(String[] args) throws InterruptedException {
    /*  Ime Thread-a

        log.info("Hello from main thread!");
        final String threadName = Thread.currentThread().getName();
        System.out.println(threadName);*/

/*      print(5);
        print(5);*/

        // Za paralelno izvrsavanje mozemo
        // 1. napravim klasu i da nasledim klasu Thread - nemamo mogucnost da nam se nasledi nesto drugo osim Thread

        // 2. da Thread napravi instancu obj Thread kome saljemo specifican objekat - NE MORA DA SE NASLEDJUJE KLASU THREAD
        // dakle u javi nemamo mogucnost visestrukog nasledjivanja kako je ovde pitanju interfejs Runnable koga implementira
        // WorkerRunnable onda imamo mogucnosti da u buducnosti nasledimo klasu - mozemo da koristimo lambda izraze jer je
        // Runnable interfejs funkcionalni interfejs

/*        final Thread thread = new WorkerThread(5);*/
        // u ovom trenutku Thread jos uvek nije pokrenut on se pokrece sa
/*
        thread.start();
*/
        // metod koji kreira novi Thread i na tom Thread-u poziva metod run(5)

/*
        thread.run(); // ovo poziva metod run na main Thread-u - ne pravi se novi Thread
*/

/*
        new WorkerThread(6).start();

        final Thread thread2 = new Thread(new WorkerRunnable(50));
        thread2.start();
*/

        // Thread-ovi se nikad ne zaustavljaju na silu vec im se signalizira
/*
        Thread.sleep(1000);
*/
        // kad Thread zna da je trazen prekid onda ce on da stane sa radom opnda kada je to moguce
/*
        thread2.interrupt(); // je za thread2 postavio interrupted indikator
*/
        // ja treba da proverim da li je interrupted postavljen i kada jeste da prekinem izvrsemnje
        // treba da vidim gde treba da prekinem sa radom

        final Thread thread3 = new Thread(ThreadsDemo::longCal);
        log.info("Starting a longCal()...");
        thread3.start();
        log.info("Working...");
        Thread.sleep(200);
        log.info("Enough waiting! Interrupt the work!");
        thread3.interrupt();
        thread3.join(); // dok se ne zavrsi Thread nas main Thread je u potpunosti blokiran dok Thread ne zavrsi sa radom
        log.info("Work finished!");
    }

    public static void longCal(){
        long start = System.currentTimeMillis();
        double sum = 0.0;

        for(long i = 0; i< 5000000000L; i++){
            sum += Math.sqrt(i);
            System.out.println("sum(" + i + "): " + sum);
            //proveramo da li se desio interrupted
            //1 . Thread.interrupted(); ako je na true - postavice ga na false
            //2 . Da na trenutnom thread-u izvrsimo metod isInterrupted() ali ga ne menja
            if(Thread.interrupted()){
                log.info("Computation interupted at i = {}. Sum so far: {}", i, sum);
                Thread.currentThread().interrupt();
                break;
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Time: " + elapsed + " sec");
    }


    public static void print(int n){
        for(int i = 0; i< 5; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.info("Print interrupted");
                Thread.currentThread().interrupt();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    log.info("Interrupted again");
                    return;
                }
            }
        }
    }


}
