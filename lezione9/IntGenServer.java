import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class IntGenServer {
    public static int DEFAULT_PORT = 1919;
    public static void main(String[] args) {
        //ricavo la porta da linea di comando
        int port;
        try {
            port = Integer.parseInt(args[0]);
        }
        catch (RuntimeException ex){
            port = DEFAULT_PORT; 
        }
        
        System.out.println("Listening for connections on port " + port);

        //creo ServerSocketChannel e Selector
        ServerSocketChannel serverChannel;
        Selector selector;

        try {
            //apro il ServerSocket e ci associo la porta 
            serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);

            //lo rendo non bloccante
            serverChannel.configureBlocking(false);

            //creo il selector e ci registro il ServerSocketChannel su accept
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        //loop di select del server
        while (true) {
            //provo a fare la select bloccante
            try {
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }

            //ottengo il SelectedKeySet e il suo iteratore
            Set <SelectionKey> readyKeys = selector.selectedKeys();
            Iterator <SelectionKey> iterator = readyKeys.iterator();

            //scorro il SelectedKeySet
            while (iterator.hasNext()) {
                //prendo la chiave corrente e la rimuovo
                SelectionKey key = iterator.next();
                iterator.remove();
                // rimuove la chiave dal Selected Set, ma non dal Registered Set

                try {
                    //se è l'accettazione di una connessione
                    if (key.isAcceptable()) {
                        //ricavo il ServerSocketChannel dalla chiave
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();

                        //accetto la connessione
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);

                        //client non blocking e lo registro nel selector in write
                        client.configureBlocking(false);
                        SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);

                        
                        ByteBuffer output = ByteBuffer.allocate(4);
                        //io il server invio il primo numero
                        output.putInt(0);
                        //preparo il buffer alla ricezione di nuovi messaggi
                        output.flip();
                        //il buffer relativo al channel client lo metto nell'attach della key
                        key2.attach(output); 
                    }

                    //sio server posso mandare nuovi dati al client
                    else if (key.isWritable()){
                        //prendo il channel e il buffer tramite la chiave 
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();

                        System.out.println("Scrivo a " + client);

                        //per fare si che vengano inviati tutti e 4 i byte prima
                        //  di generare un nuovo numero
                        if (!output.hasRemaining()){
                            //leggi dall'inizio
                            output.rewind();
                            //prendo l'int inviato precedentemente
                            int value = output.getInt();
                            //passi in filling
                            output.clear();
                            //invii l'intero successivo nella sequenza
                            output.putInt(value + 1);
                            //ti riprepari a leggere
                            output.flip();
                        }

                        //invii
                        client.write(output);
                    }
                }catch (IOException ex) { 
                    //qual cosa non va su quel channel, lo eliminiamo
                    key.cancel();
                    try { 
                        //chiudo il channel
                        key.channel().close(); 
                    }catch (IOException cex) {}
                }
            }
        }   
    }
}