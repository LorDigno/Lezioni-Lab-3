import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiQuoteServer {

    // Classe interna per gestire i client
    private static class ClientHandler implements Runnable {
        //socket associato
        private Socket clientSocket;
        String[] quotes;

        public ClientHandler(Socket socket, String[] quotes) {
            this.clientSocket = socket;
            this.quotes = quotes;
        }

        //praticamente qua si ha il main della versione ST così che ogni thread 
        //  gestisca la propria connessione indipendentemente
        public void run() {
            //try with resources sull'outputStream
            try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){ 
                String quote = quotes[new Random().nextInt(quotes.length)];

                //manda la citazione nello stream
                out.println(quote);
                System.out.println(" Inviata a ✨" +clientSocket.getInetAddress() + ": "+quote);

            }catch (IOException e) {
                System.err.println(" Errore con il client: ❌" + e.getMessage());
            } finally {
                //finita l'esecuzione prova a chiudere la socket associata alla task
                try {
                    clientSocket.close();
                }catch (IOException e){
                    System.err.println(" Errore nella chiusura del client: ❌" + e.getMessage());
                }
            }

            try{
                Thread.sleep(1000);
            }catch(Exception e){}
        }
    }

    public static void main(String[] args) {
        int port = 2018; 
        // Alcune frasi di esempio
        String[ ] quotes = {
            "Posso resistere a tutto tranne che alla tentazione - Oscar Wilde",
            "L istruzione è ciò che resta dopo che si è dimenticato ciò che si è imparato a scuola - Albert Einstein",
            "Sii il cambiamento che vuoi vedere nel mondo - Mahatma Gandhi ",
            "Il successo non è definitivo, il fallimento non è fatale: ciò che conta è il coraggio di continuare - Winston Churchill",
            "Scegli un lavoro che ami, e non dovrai lavorare neppure un giorno in vita tua - Confucio"
        };

        //faccio try with resources per facilitare la close
        try (ServerSocket serverSocket = new ServerSocket(port);
          ExecutorService pool = Executors.newFixedThreadPool(20);){

            System.out.println(" Quote of the Day server attivo sulla porta 📜" + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                //ricevuta una connessione si manda un thread apposito
                pool.execute(new ClientHandler(clientSocket,quotes));
            }

        }catch (IOException e) {
            System.err.println(" Errore di IO: ❌" + e.getMessage());
        }
    }
}
