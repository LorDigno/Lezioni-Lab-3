import java.io.*;
import java.net.*;

public class QuoteClient {
    public static void main(String argv[]){
        int port = Integer.parseInt(argv[0]);
        String ad = "localhost";
        
        try(Socket sock = new Socket(ad, port);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(
                    sock.getInputStream())))){
            
            System.out.println("Connessione stabilita! In attesa della citazione...\n");

            // Leggo la riga inviata dal server
            String quote = in.readLine();
            
            // Stampo a video quello che ho ricevuto
            if (quote != null) {
                System.out.println("Il server dice: " + quote);
            } else {
                System.out.println("Il server ha chiuso la connessione senza inviare nulla.");
            }
        } catch (UnknownHostException e) {
            System.err.println("Impossibile trovare l'host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore di I/O o connessione rifiutata: " + e.getMessage());
        }
    }
}
