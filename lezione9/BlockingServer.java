import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class BlockingServer{
    public static void main(String[] args) throws IOException {
        //apro manualmente un ServerSocketChannel
        ServerSocketChannel serverSocket = ServerSocketChannel.open();

        //gli associo la porta voluta
        serverSocket.bind(new InetSocketAddress(8080));

        while (true) {
            //accetto connessione direttamente come channel
            SocketChannel socket = serverSocket.accept();

            //apertura manuale fatta dal client
            // SocketChannel socketChannel = SocketChannel.open();
            // socketChannel.connect (new InetSocketAddress("www.google.it", 80));

            handleRequest(socket);

            //termino connessione
            socket.close();
        }
    }

    //rilancia alla socket lo stesso messaggio ricevuto
    private static void handleRequest(SocketChannel socket) {
        //alloco in kernel un buffer di 80 byte
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(80);

        try {
            while (socket.read(byteBuffer) != -1){
                //cambio il buffer in draining
                byteBuffer.flip();

                //toUpperCase(byteBuffer);
                Thread.sleep(3000);

                //finisco di copiare i byte buffer sulla socket
                while (byteBuffer.hasRemaining()){
                    socket.write(byteBuffer);
                }

                //torno in filling
                byteBuffer.clear();
            }
        }
        catch(Exception e){ 
            e.printStackTrace();
        }
    }
}