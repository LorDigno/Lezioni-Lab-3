import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MojibakeclientClient {
    public static void main(String[] args) {
        String host = "tcpbin.com"; // public echo server
        int port = 4242; // echo port

        //mettendo la socket nel try poi viene chiusa automaticamente all'uscita dal blocco
        try (Socket socket = new Socket(host, port)){
            System.out.println("Connected to " + host + ":" + port);

            // Reader using UTF-8 encoding
            //Faccio un wrapper BufferedReader del wrapper INputStreamReader 
            //  dell'originale InputStream della socket
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.ISO_8859_1));

            // Send a UTF-8 encoded message
            String message = "Buongiorno! Caffè e fiori rendono la giornata migliore.☕🌸";

            writer.write(message + "\n");
            writer.flush();
            System.out.println("Sent: " + message);

            // Read the echo from the server
            String response = reader.readLine();
            System.out.println("Received: " + response);
            
        } catch (IOException e) {
            System.err.println("Error communicating with server:");
            e.printStackTrace();
        }
    }
}