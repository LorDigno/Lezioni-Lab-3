import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleCached{
    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i =0; i<100; i++) {
            service.execute(new Task(i)); 
            //Con questo sleep ogni task sarà fatto dallo stesso thread
            //sleep(1000); 
            //Altrimenti vengono creati un botto di thread per i task
        }
        System.out.println("ThreadName:"+Thread.currentThread().getName());
    }

    private static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch(InterruptedException e) {}
    }
}
