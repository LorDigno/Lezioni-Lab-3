import java.io.*;
import java.net.*;
import java.util.Random;

public class QuoteServer {
    public static void main(String[] args) {
        int port = 2017; // la porta "classica" del servizio QOTD (Quote of the Day)
        // Alcune frasi di esempio
        String[ ] quotes = {
            "Posso resistere a tutto tranne che alla tentazione - Oscar Wilde",
            "L istruzione è ciò che resta dopo che si è dimenticato ciò che si è imparato a scuola - Albert Einstein",
            "Sii il cambiamento che vuoi vedere nel mondo - Mahatma Gandhi ",
            "Il successo non è definitivo, il fallimento non è fatale: ciò che conta è il coraggio di continuare - Winston Churchill",
            "Scegli un lavoro che ami, e non dovrai lavorare neppure un giorno in vita tua - Confucio"
        };

        //faccio try with resources per facilitare la close
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println(" Quote of the Day server attivo sulla porta 📜" + port);

            //avanti all'infinito
            while (true) {

                //classica versione single threaded
                //try with resources sull'accept della connessione
                try (Socket clientSocket = serverSocket.accept()){

                    System.out.println(" Connessione da: 🔗" + clientSocket.getInetAddress());

                    //try with resources sull'output stream wrappato in printWriter
                    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){
                        // ultimo parametro a TRUE = Autoflush

                        String quote = quotes[new Random().nextInt(quotes.length)];

                        out.println(quote);
                        System.out.println(" Inviata: ✨" + quote);

                    } catch (IOException e) {
                        System.err.println(" Errore nella scrittura: ❌" + e.getMessage()); 
                    }
                    
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){}
                    
                }
                catch (IOException e) {
                    System.err.println(" Errore con un client: ❌" + e.getMessage()); 
                }
            }
        }catch (IOException e) {
            System.err.println(" Errore nell'avvio del server: ❌" + e.getMessage());
        } 
    }
}