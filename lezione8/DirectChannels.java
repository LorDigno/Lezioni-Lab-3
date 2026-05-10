import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class DirectChannels {
    public static void main(){
        try{
            //ricavo un channel dal corrispettivo file
            RandomAccessFile fromFile = new RandomAccessFile("in.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("out.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            //così trasferisco in to tutto il contenuto da 0 a count di From
            toChannel.transferFrom(fromChannel, position, count);
        }catch(Exception e){}
    }
}
