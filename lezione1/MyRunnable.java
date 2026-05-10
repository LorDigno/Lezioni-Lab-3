public class MyRunnable implements Runnable {
    //Con runnable definisci una task che poi un thread può eseguire
    //Così lo stesso task può essere eseguito da più thread
    @Override
    public void run() {
        System.out.println("MyRunnable running");
        System.out.println("MyRunnable finished");
    }
}