import java.util.*;
import java.util.concurrent.*;

public class PutIfAbsent {
    //PutIfAbsent è un metodo tutto Atomico implementato apposta nelle ConcurrentHashMap
    //Ci sono tnat altri metodi composti ma implementati atomicamente

    static Map<String, Object> theMap = new ConcurrentHashMap<>();

    public static void main(String [] args){ 
        //thread che svolge PutIfAbsent
        Thread t1 = new Thread (
            new Runnable() {public void run()
            {Object obj1 = new Object();
            System.out.println("T1: "+ theMap.putIfAbsent("5",obj1));};});
        t1.start();

        Thread t2 = new Thread (
            new Runnable() {public void run()
            {Object obj2 = new Object();
            System.out.println("T2: "+ theMap.putIfAbsent("5",obj2));};});
        t2.start();
    }
}