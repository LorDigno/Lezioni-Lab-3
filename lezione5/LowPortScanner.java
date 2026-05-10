import java.io.*;
import java.net.*;

public class LowPortScanner {
    //Provo a vedere quali porte sono occupate
    public static void main(String[] args) {

        //se farlo su localhost o args[0] passato in input
        String host = args.length > 0 ? args[0] : "localhost";

        //scorro le 1024 porte basse
        for (int i = 1; i < 1024; i++) {
            try {
                //costruttore con nomehost, porta
                Socket s = new Socket(host, i);
                System.out.println("There is a server on port " + i + " of " + host);
                s.close();
            } catch (UnknownHostException ex) {
                //dato che gli do l'hostname può non riuscire la risoluzione dns
                System.err.println(ex);
                break;
            } catch (IOException ex) {
                //la porta ha rifiutato la connessione
                // must not be a server on this port
            }
        }
    }
}