import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteBufferExample {
    public static void main(String[] args) {
        //payload
        String message = "Hello NIO!";

        //try with resouces del FileOutpuStream
        //il FileChannel lo ricavo dallo stream.getChannel()
        //così posso usare direttamente il channel come wrapper dello stream 
        try (FileOutputStream fos = new FileOutputStream("output.txt");
            FileChannel channel = fos.getChannel()){

            // 1. Creiamo un buffer da 64 byte
            ByteBuffer buffer = ByteBuffer.allocate(64);

            // 2. Scriviamo dati nel buffer
            buffer.put(message.getBytes());

            // 3. Prepariamo il buffer alla lettura (dal punto di vista del canale)
            buffer.flip();

            // 4. Il canale legge dal buffer e scrive nel file
            channel.write(buffer);

            System.out.println("Dati scritti su file con successo!");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

