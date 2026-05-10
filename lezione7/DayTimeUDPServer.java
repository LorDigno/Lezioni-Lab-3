import java.net.*; 
import java.util.Date; 
import java.io.*;

public class DayTimeUDPServer {
    private final static int PORT = 1300;

    public static void main(String[] args) {
        //try with resouces sul DatagramSocket
        try (DatagramSocket socket = new DatagramSocket(PORT)){
            //all'infinito
            while (true) {
                try {
                    //creo il packet di ricezione
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);

                    //ricezione bloccante
                    socket.receive(request);
                    System.out.println("ricevuto un pacchetto da"+request.getAddress()+""+request.getPort());

                    //creo il payload
                    String daytime = new Date().toString();
                    byte[] data = daytime.getBytes("US-ASCII");

                    //creo il packet da inviare
                    //indirizzo e porta li prendo dalla richiesta
                    DatagramPacket response = new DatagramPacket(data, data.length,
                        request.getAddress(), request.getPort());

                    //invio
                    socket.send(response);
                } catch (IOException | RuntimeException ex) {ex.printStackTrace();}
            } 
        }
        catch (IOException ex) { ex.printStackTrace();}
    }
}
