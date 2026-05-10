class Back implements Runnable {
    public void run(){
        while(true){
            try {
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException e){ 
                return; 
            }
            System.out.print("\b\b\b\b\b\b\b\b\b\b");
            System.out.print("-----------");
            System.out.flush();
        }
    }
}