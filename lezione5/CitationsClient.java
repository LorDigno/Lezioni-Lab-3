import java.io.*;
import java.net.Socket;

public class CitationsClient {
    //Versione a citazione singola
    // public static void main(String[] args) {
    //     String host = "djxmmx.net";
    //     int port = 17;
    //     Socket socket=null;

    //     try{
    //         //provo ad inizializzare il socket 
    //         socket = new Socket(host, port);

    //         //wrappo l'input stream per vderelo come BufferedReader
    //         BufferedReader in = new BufferedReader(
    //             new InputStreamReader(socket.getInputStream()));


    //         System.out.println("Connesso a djxmmx.net! Ecco la tua citazione del giorno:\n");

    //         String line;
    //         while ((line = in.readLine()) != null){
    //             //leggo finchè lo stream ha linee
    //             System.out.println(line); 
    //         }

    //         System.out.println("\nFine della citazione. Connessione chiusa dal server.");
    //     } catch (IOException e) {
    //         e.printStackTrace(); 
    //     }
    //     try{ 
    //         socket.close();
    //     } catch (IOException e){
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {
        String host = "djxmmx.net";
        int port = 17; // QOTD standard TCP
        int repeat = 10; // quante citazioni vuoi ricevere
        int delay = 3000; // millisecondi tra le citazioni (3 secondi)
        Socket socket=null;

        for (int i = 1; i <= repeat; i++) {
            try{
                socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

                System.out.println("\nCitazione #" + i + ":");

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) { e.printStackTrace(); }

            //aspetto prima di passare alla prossima citazione
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }

            System.out.println("\nFine delle citazione");
            try {
                socket.close();
            } catch (Exception e) {}
        }   
    }
}