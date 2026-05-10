import java.nio.*;
public class Buffers {
    public static void main (String args[]){
        //creo un buffer con 10 byte di capacità
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);

        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]

        //aggiungo un char al buffer, dim = 2 byte
        byteBuffer1.putChar('a');

        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=2 lim=10 cap=10]

        //aggiungo un int, dim = 4 byte
        byteBuffer1.putInt(1);

        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=6 lim=10 cap=10]

        //passo in modalità di draining (lettura)
        byteBuffer1.flip();

        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=0 lim=6 cap=10]
        //oltre 6 non leggo perché è trash data, io ho inserito solo 6 byte

        //prendo un char dal buffer
        System.out.println(byteBuffer1.getChar());
        System.out.println(byteBuffer1);
        // a
        // java.nio.HeapByteBuffer[pos=2 lim=6 cap=10]

        //ricompatto i dati non letti e switcho a filling (scrittura)
        byteBuffer1.compact();

        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=4 lim=10 cap=10]
        //puoi riempire solo da 4 perché l'int inserito prima ancora non è stato letto

        //aggiungo un int, dim = 4        
        byteBuffer1.putInt(2);

        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=8 lim=10 cap=10]

        //trono in modalità draining (lettura)
        byteBuffer1.flip();
        // java.nio.HeapByteBuffer[pos=0 lim=8 cap=10]
        //siamo in pos = 0 per via del compact

        System.out.println(byteBuffer1.getInt());
        System.out.println(byteBuffer1.getInt()); 
        System.out.println(byteBuffer1);
        // 1
        // 2
        // java.nio.HeapByteBuffer[pos=8 lim=8 cap=10]
        //ho letto tutto quello che era nel buffer

        byteBuffer1.rewind();
        //rewind prepara a rileggere i dati che sono nel buffer, ovvero resetta
        //position a 0 e non modifica limit
        //java.nio.HeapByteBuffer[pos=0 lim=8 cap=10]
        //buffer come prima della lettura

        System.out.println(byteBuffer1.getInt());
        // 1

        //posiziono il mark dopo il primo int ovvero mark = 4
        byteBuffer1.mark();

        System.out.println(byteBuffer1.getInt());
        // 2

        System.out.println(byteBuffer1);
        //position:8;limit:8;capacity:10
        //di nuovo ho letto tutto

        byteBuffer1.reset();
        //position = mark quindi posso rileggere il secondo int

        System.out.println(byteBuffer1);
        //position:4;limit:8;capacity:10

        byteBuffer1.clear();
        System.out.println(byteBuffer1);
        //position:0;limit:10;capacity:10]]
        //riscrivo da 0 perché clear non mantiene i dati da leggere come compact
    }
}
