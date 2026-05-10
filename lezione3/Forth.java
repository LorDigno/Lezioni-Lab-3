class Forth implements Runnable {
    public void run(){
        while(true){
            try {
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException e) { 
                return; 
            }
            System.out.print("**********");
            System.out.flush();
        }
    }
}