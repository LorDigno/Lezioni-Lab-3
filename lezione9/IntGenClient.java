import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class IntGenClient {
    public static int DEFAULT_PORT = 1919;
    public static int DEFAULT_LIMIT = 42;

    public static void main(String[] args) {
        //prendo il numero che voglio da linea di comando
        int limit;
        try {
            limit = Integer.parseInt(args[0]);
        }catch(Exception e){
            limit = DEFAULT_LIMIT;
        }

        try(SocketChannel sock = SocketChannel.open();){
            //associo la porta giusta e apro la connessione
            sock.connect(new InetSocketAddress("localhost", DEFAULT_PORT));

            //buffer per ricevere gli interi
            ByteBuffer buf = ByteBuffer.allocate(4);


            int letto = -1;
            while(letto < limit){
                //scrivo nel buffer i dati
                int n = sock.read(buf);
                if (n == -1) {
                    System.err.println("Il server ha chiuso la connessione prematuramente.");
                    System.exit(1);
                }

                //passo in draining
                buf.flip();

                letto = buf.getInt();
                System.out.println("Ricevuto l'intero: " + letto);

                //preparo per la prossima scrittura
                buf.clear();
            } 
        } catch (Exception e) {
            System.err.println("errore di boh");
            System.exit(2);
        }
    }
}
