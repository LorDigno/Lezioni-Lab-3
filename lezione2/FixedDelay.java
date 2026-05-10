import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class FixedDelay{
    private static void periodicShot_FixedDelay (){
        ScheduledExecutorService stse = Executors.newSingleThreadScheduledExecutor();
        System.out.println("ScheduledExecutor starts at"+ LocalDateTime.now());
        Runnable ru = () ->{
            System.out.println(Thread.currentThread().getName() + " start: " + Instant.now());
            Random r = new Random();
            int dur = 2 + 2*r.nextInt(2); // durata del thread da 2 a 4 secondi
            System.out.println(Thread.currentThread().getName() + " duration: " + dur);
            try
            { TimeUnit.SECONDS.sleep(dur);}
            catch(InterruptedException ex){}
            System.out.println(Thread.currentThread().getName() + "end:" + Instant.now() +"\n");
        };

        // inizia dopo 2 secondi, pausa tra i thread di 3 secondi
        //stse.scheduleWithFixedDelay(ru, 2, 3, TimeUnit.SECONDS);
        // main must wait because shutDown() exits immediately.

        //versione in cui ne aggiunge uno in coda ogni 2 secondi
        stse.scheduleAtFixedRate(ru, 2, 3, TimeUnit.SECONDS);


        try{
            stse.awaitTermination(30, TimeUnit.SECONDS );
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("shutdown");
        stse.shutdown();
        stse.close();
    }
    public static void main (String args[]){
        periodicShot_FixedDelay();
    }
}