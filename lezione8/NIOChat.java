import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class NIOChat {
    public static void main(String[] args) {
        // Creiamo un buffer per scambiare i messaggi in chat
        //indiretto di 128 byte.
        ByteBuffer chatBuffer = ByteBuffer.allocate(128);

        // Alice scrive un messaggio nel buffer
        String messageFromAlice = "Hi Bob, how are you?";
        System.out.println(" Alice scrive: 💬" + messageFromAlice);
        chatBuffer.put(messageFromAlice.getBytes(StandardCharsets.UTF_8));

        // Passaggio di stato: ora Bob deve leggere → flip()
        chatBuffer.flip();

        // Bob legge il messaggio dal buffer
        byte[] receivedBytes = new byte[chatBuffer.remaining()];
        //remaining si può usare per capire quanto spazio allocare per la ricezione

        //ricezione
        chatBuffer.get(receivedBytes);
        String messageForBob = new String(receivedBytes, StandardCharsets.UTF_8);
        System.out.println(" Bob riceve: 📩" + messageForBob);

        // Bob risponde → clear() e scrive di nuovo
        chatBuffer.clear();

        //scrittura di Bob
        String replyFromBob = "Hey Alice! I'm fine, thanks!";
        System.out.println(" Bob scrive: 💬" + replyFromBob);
        chatBuffer.put(replyFromBob.getBytes(StandardCharsets.UTF_8));

        //flip per permettere la lettura ad alice
        chatBuffer.flip();

        // Alice legge la risposta
        byte[] responseBytes = new byte[chatBuffer.remaining()];
        chatBuffer.get(responseBytes);

        //elaborazione messaggio ricevuto
        String messageForAlice = new String(responseBytes, StandardCharsets.UTF_8);
        System.out.println(" Alice riceve: 📩" + messageForAlice);
    }
}

