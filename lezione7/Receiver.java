import java.net.*;

public class Receiver{
    public static void main(String args[]) throws Exception {
        //apro il socket udp sulla porta in Sender, la 40000
        DatagramSocket serverSock= new DatagramSocket(40000);

        //alloco un buffer di 100 byte per la ricezione        
        byte[] buffer = new byte[100];

        //creo il packet di ricezione
        DatagramPacket receivedPacket = new DatagramPacket(buffer,buffer.length);
        
        while (true) {
            //ricevo dal socket e riempio il packet (receive bloccante)
            serverSock.receive(receivedPacket);

            //converto il payload da bytes a stringa
            //questo approccio però mostra sempre l'intero buffer invece che il singolo
            //   payload di un pacchetto alla volta.
            // String byteToString = new String(receivedPacket.getData(),"US-ASCII");

            //così abbiamo offset 0 e una lenght pari a quella del solo datagramma corrente
            String byteToString = new String(receivedPacket.getData(),0,
                receivedPacket.getLength(),"US-ASCII");

            //prendo la lenght
            int l = byteToString.length();

            //stampe
            System.out.println(l);
            System.out.println("Length " + receivedPacket.getLength() +
            " data " + byteToString);
        }
    }
}