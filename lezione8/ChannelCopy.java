import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelCopy{ 
    public static void main (String [] argv) throws IOException{ 
        //usi dei metodi factory della classe Channels per Readable/Writable ByteChannels
        ReadableByteChannel source = Channels.newChannel(new FileInputStream("in.txt"));
        WritableByteChannel dest = Channels.newChannel (new FileOutputStream("out.txt"));

        channelCopy1(source, dest);

        //i Channel aperti con la open vanno poi chiusi
        source.close();
        dest.close();
    }

    //metodo che fa effettivamentge la copia
    // prende i due channel, uno di input e uno di output
    private static void channelCopy1 (ReadableByteChannel src, WritableByteChannel dest) throws IOException{
        //creo un buffer in kernel
        ByteBuffer buffer = ByteBuffer.allocateDirect (16 * 1024);
        //attualmente in filling

        //per le stampe
        int pos = buffer.position();

        //se la read è -1 ho letto tutto
        while (src.read(buffer) != -1) {
            System.out.println("Letti dal file " + (buffer.position() - pos) + " bytes");

            // prepararsi a leggere i byte che sono stati inseriti nel buffer
            buffer.flip();
            pos = buffer.position();
            //ora buffer in draining

            // scrittura nel file destinazione; può essere bloccante
            dest.write(buffer);
            System.out.println("Scritti sul file " + (buffer.position() - pos) + " bytes");

            // non è detto che tutti i byte siano trasferiti, dipende da quanti
            // bytes la write ha scaricato sul file di output
            //non si può controllare

            // compatta i bytes rimanenti all'inizio del buffer
            // se il buffer è stato completamente scaricato,si comporta come clear()
            buffer.compact();
            pos = buffer.position(); 
        }

        // quando si raggiunge l'EOF,è possibile che alcuni byte debbano essere ancora
        // scritti nel file di output
        //perchè è stata fatta la compact
        buffer.flip();

        pos = buffer.position();
        //garantisco la conclusione della scrittura col loop remaining
        while (buffer.hasRemaining()){ 
            dest.write (buffer); 
            System.out.println("Scritti sul file " + (buffer.position() - pos) + " bytes");
            pos = buffer.position();
        }
    }

    //seconda versione col clear
    private static void channelCopy2 (ReadableByteChannel src, WritableByteChannel dest) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocateDirect (16 * 1024);

        while (src.read (buffer) != -1) {
            // prepararsi a leggere i byte inseriti nel buffer dalla lettura
            // del file
            buffer.flip();

            // riflettere sul perchè del while
            // una singola lettura potrebbe non aver scaricato tutti i dati
            while (buffer.hasRemaining()) {
                dest.write (buffer); 
            }

            // a questo punto tutti i dati sono stati letti e scaricati sul file
            // preparare il buffer all'inserimento dei dati provenienti
            // dal file
            buffer.clear();
        }
    }
}
