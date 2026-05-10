import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDimensioning {
    public static void main (String [] args) {

        // get count of available cores
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println(coreCount);
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        // submit the tasks for execution
        for (int i=0; i< 100; i++) {
            service.execute(new Task(i));
        } 

        // service.shutdown();
        // if(!service.isTerminated()){
        //     try {
        //         service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        //     } catch (Exception e) {}
        // }
        // service.close();
    }
}