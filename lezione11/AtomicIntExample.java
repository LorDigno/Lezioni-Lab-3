import java.util.concurrent.*; 
import java.util.concurrent.atomic.*;

public class AtomicIntExample {
    public static void main(String[] args) {
        //creo un ExecutorService da 2 thread
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //intero atomico con op apposite per l'atomicità
        AtomicInteger atomicInt = new AtomicInteger();

        for(int i = 0; i < 10; i++){
            //tutti e 10 i thread puntano allo stesso int
            CounterRunnable runnableTask = new CounterRunnable(atomicInt);
            executor.submit(runnableTask);
        }
        executor.shutdown(); 
    }

    static class CounterRunnable implements Runnable {
        //intero di riferimento
        AtomicInteger atomicInt;

        CounterRunnable(AtomicInteger atomicInt){
            this.atomicInt = atomicInt;
        }

        @Override
        public void run() {
            System.out.println("Counter- " + atomicInt.incrementAndGet());
        }
    }
}
