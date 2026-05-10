import java.net.*;

public class Sender{ 
    public static void main (String args[]) {
        try{
            //creo il socket udp
            DatagramSocket clientsocket = new DatagramSocket();

            //payload del messaggio in bytes
            byte[] buffer="1234567890abcdefghijklmnopqrstuvwxyz".getBytes("US-ASCII");

            //indirizzo del ricevente. (localhost)
            InetAddress address = InetAddress.getByName("localhost");

            for (int i = buffer.length; i >0; i--){
                //creo un pacchetto con payload il nostro buffer
                //indirizzo quello di localhost e porta 40000
                //i determina quanto prendo del payload
                DatagramPacket mypacket = new DatagramPacket(buffer,i,address,40000);

                //invio il pacchetto
                clientsocket.send(mypacket);

                //sleep per sport
                Thread.sleep(200); 
            }

            clientsocket.close();
            System.exit(0);
        }
        catch (Exception e) {e.printStackTrace();}
    }
}