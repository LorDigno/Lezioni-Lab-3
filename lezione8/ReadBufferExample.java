import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadBufferExample {
    public static void main(String[] args) {
        //di nuovo try with resouces per Stream e Channel wrapper
        try (FileInputStream fis = new FileInputStream("output.txt");
            FileChannel channel = fis.getChannel()){

            // 1. Creiamo un buffer da 64 byte
            ByteBuffer buffer = ByteBuffer.allocate(64);

            // 2. Leggiamo dati dal file (il canale scrive nel buffer)
            int bytesRead = channel.read(buffer);

            //se bytesread = -1 sono alla fine del channel
            while (bytesRead != -1) {
                System.out.println("Letti " + bytesRead + " byte dal file");

                // 3. Prepariamo il buffer per la lettura
                buffer.flip();

                // 4. Leggiamo i dati dal buffer
                //con un ciclo basato su remaining posso continuare a prendere char fino a 
                //  svotare il buffer.
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }

                // 5. Ripuliamo il buffer per il prossimo ciclo
                buffer.clear();

                // 6. Leggiamo altri dati (se il file è più lungo)
                bytesRead = channel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}