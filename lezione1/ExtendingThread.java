public class ExtendingThread {
    public static void main (String [] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("Main running");
        //Per vedere che effettivamente sono esecuzioni diverse
        // try {
        //     Thread.sleep(10);
        // } catch (Exception e) {}
        System.out.println("Main finished");
    }
}
