import java.io.*; 
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        String hostname = args.length > 0 ? args[0] : "time.nist.gov";
        Socket socket = null;

        try {
            //crea la socket
            socket = new Socket(hostname, 13);

            //una read sull'input stream ha timeout 15000 ms
            socket.setSoTimeout(15000);

            //prendo lo stream
            InputStream in = socket.getInputStream();

            //roba che serve per il parsing delle stringhe
            StringBuilder time = new StringBuilder();

            //wrappo l'inputStream in un INputStreamReader per usare metodi a 
            // livello più alto
            InputStreamReader reader = new InputStreamReader(in, "ASCII");

            //faccio un ciclo di read, -1 vuol dire stream finito
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c); 
            }
            System.out.println(time);

        } catch (IOException ex) { 
            //errore di connessione al server
            System.out.println("could not connect totime.nist.gov");
        } finally {
            if (socket != null) {
                //se il socket esiste lo chiude
                try {
                    socket.close();
                } catch (IOException ex) {}
            }
        }
    }
}
