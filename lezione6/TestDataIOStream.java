import java.io.*;

public class TestDataIOStream {
    public static void main(String[] args) throws FileNotFoundException{
        String filename = args[0];

        
        DataInputStream in =
            new DataInputStream( //permette di leggere dati primitivi
                new BufferedInputStream( //bufferizza per efficienza
                    new FileInputStream(filename))); //legge da file
        try{
            //cambia solo come vengonno interpretati i byte letti
            System.out.println("byte: " + in.readByte());
            System.out.println("short: " + in.readShort());
            System.out.println("int: " + in.readInt());
            System.out.println("long: " + in.readLong());
            System.out.println("float: " + in.readFloat());
            System.out.println("double: " + in.readDouble());
            System.out.println("boolean: " + in.readBoolean());
        }
        catch (Exception e){}
    }
}