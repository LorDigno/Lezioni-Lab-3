import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class PollingServer {
    public static void main(String[] args) {
        //ottengo un ServerSocketChannel con la factory di classe
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //ottieni il socket relativo al Channel col getter
        ServerSocket socket = serverSocketChannel.socket();

        //gli associo la porta del servizio
        socket.bind(new InetSocketAddress(9999));

        //metto tutto come non blocking
        serverSocketChannel.configureBlocking(false);

        //faccio polling perché l'accept non aspetta quindi viene chiamata tante volte
        //  finchè non c'è una connessione in arrivo
        while(true){
            //accetto e poi gestisco la connessione
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){
                //do something with socketChannel...
            }
            else{}
        }
    }
}
