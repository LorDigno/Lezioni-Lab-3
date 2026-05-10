class Server extends Thread{ 
    //solo se volatile funziona perché è sempre aggiornata in main memory
    //le non volatile possono avere valori diversi in cache diverse nei core
    volatile boolean stop = false; 
    int i;

    public void run(){ 
        while(! stop) {};
        System.out.println("Server is stopped....");
    }

    //versioni basate su blocchi synchronized funzionano senza volatile
    //monitor garantisce sia visibilità che sincronizzazione
    // public void run(){ 
    //     synchronized(stop) {}; 
    //     while(! stop){
    //          synchronized(stop) {}
    //     };
    //     System.out.println("Server is stopped....");
    //}
    // public synchronized void stopThread(){ stop = true; }

    //volatile si usa solo su tipi primitivi per evitare la conversione ad oggetto

    public void stopThread(){ 
       stop = true;
    }
}


public class StoppingAThread{ 
    public static void main(String args[]) throws InterruptedException{ 
        //creo un server
        Server myServer = new Server();
        myServer.start();

        //dormo e poi chiamo la fermata del server
        System.out.println(Thread.currentThread().getName() + " is stopping Server thread");
        Thread.sleep(1000);
        myServer.stopThread();

        //finisce davvero?
        System.out.println(Thread.currentThread().getName() + " is finished now"); 
        //senza il volatile c'è la modifica solo a livello della cache del thread main
        //con volatile questa modifica va in memoria condivisa e propagata al thread server

        //al flush dovuto alla volatile vengono anche messe in memoria condivisa tutte 
        //le modifiche a variabili non volatile visibili da quel thread

        //stessa cosa avviene con il refresh in lettura
    }
}