public class ThreadRunnable {
    public static void main(String [] args) {
        //Crei il thread con in input la task runnable da eseguire
        Thread thread = new Thread (new MyRunnable());
        thread.start();
        System.out.println("Main running");
        //Per vedere che effettivamente sono esecuzioni diverse
        // try {
        //     Thread.sleep(10);
        // } catch (Exception e) {}
        System.out.println("Main finished");
    }
}