import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Adder {
    public static void main(String[] args) throws ExecutionException,InterruptedException{
        // Create thread pool using Executor Framework
        ExecutorService executor = Executors.newFixedThreadPool(5);

        //creo la lista in cui mettere i risultati
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        // Create new Calculator object in loop
        for(int i = 1; i < 101; i=i+2) { 
            Calculator c = new Calculator(i, i + 1);
            list.add(executor.submit(c));
        }

        int s=0;
        // Now collect the results
        for(Future<Integer> f: list){
            //Interfaccia future
            // public interface Future <V>{ 
            //     V get( ) throws...;
            //     V get (long timeout, TimeUnit) throws...;
            //     void cancel (boolean mayInterrupt);
            //     boolean isCancelled( );
            //     boolean isDone( ); 
            // }
            
            //get può lanciare InterruptException e ExecutionException
            //Infatti il main dichiara che le lancia
            s = s + f.get();
        }

        System.out.println("Risultato finale calcolato: " + s);

        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.MILLISECONDS);
        if(!executor.isTerminated()){
            executor.shutdownNow();
        }
    }
}