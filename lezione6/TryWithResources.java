import java.io.*;

public class TryWithResources{ 
    public static void main (String args[])throws IOException {
        try(FileInputStream input = new FileInputStream(new File("immagine.jpg"));
            BufferedInputStream bufferedInput = new BufferedInputStream(input))
        {//sopra vengono costruiti gli stream

            int data = bufferedInput.read();
            while(data != -1){
                System.out.print((char) data);
                data = bufferedInput.read();
            }
        }//close automatica
        //se avviene un'eccezione nel costruttore viene propagata quella
        //se avviene nel body viene chiamata la close e poi si propaga
        //se avviene nella close si propaga quella
        //se c'è sia in body che in close vince quella del body
    }
}
