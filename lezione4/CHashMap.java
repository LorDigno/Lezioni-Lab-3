import java.util.*;
import java.util.concurrent.*;

public class CHashMap {
    //si basa su una ConcurrentHashMap
    private Map<String, Object> theMap = new ConcurrentHashMap<>();

    public Object getOrCreate(String key){
        Object value = theMap.get(key);
        try { 
            Thread.sleep(5000);
        } catch(Exception e){}

        //se il valore non esiste più ne crea uno nuovo
        if (value == null) {
            value = new Object();
            theMap.put(key, value); 
        }
        return value.hashCode();
    }
    //nonostante le singole operazioni su ConcurrentHashMap siano atomiche
    //l'intero metodo GetOrCreate non lo è e si ha race condition

    public static void main(String [] args){
        CHashMap ex = new CHashMap();

        //inizializzo un thread con la task da fare
        Thread t1 = new Thread (new Runnable()
            {public void run()
            {System.out.println
            ("T1: " + ex.getOrCreate("5"));};});
            //fa get o create sulla key "5"
        t1.start();

        //altro thread che fa la stessa cosa
        Thread t2 = new Thread (new Runnable()
            {public void run()
            {System.out.println
            ("T2: " + ex.getOrCreate("5"));};});
        t2.start();

        //dato che GetOrCreate non è atomico i 2 thread leggono valori diversi
        }
}