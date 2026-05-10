import java.io.*;
import java.net.*;

public class DayTimeUDPClient {
    // RFC-867
    private final static int PORT = 1300;
    private static String HOSTNAME = null;
    
    public static void main(String[] args) {
        HOSTNAME = args[0];

        //try with resources sul DatagramSocket
        try (DatagramSocket socket = new DatagramSocket(0)){
            //metto un timeout per sicurezza
            socket.setSoTimeout(15000);

            //faccio risoluzione dns del dominio
            InetAddress host = InetAddress.getByName(HOSTNAME);

            //creo un datagramma d'invio all'host in cui invio un solo byte
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host , PORT);

            //creo datagramma di ricezione con 1024 byte di buffer
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            //invio
            socket.send(request);

            //ricezione bloccante
            socket.receive(response);

            //stampa
            String daytime = new String(response.getData(),0,response.getLength(),"Us-ASCII");
            System.out.println(daytime);
        }
        catch (IOException ex) { ex.printStackTrace(); }
    }

}