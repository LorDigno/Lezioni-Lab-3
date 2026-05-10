import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Prop {
    public static void main (String args[]){ 
        //apro il file con le impostazioni di configurazione
        File configFile = new File("config.properties");

        //il file .properties è semplicemente un insieme di righe 
        // nome=valore

        try {
            //apro il file in un reader
            FileReader reader = new FileReader(configFile);

            //classe Properties ha vari metodi utili per il parsing
            Properties props = new Properties();

            //gli associ il reader
            props.load(reader);

            //ottiene la proprietà host, il suo valore
            String host = props.getProperty("host");
            System.out.print("Host name is: " + host+"\n");

            String port = props.getProperty("port");
            System.out.print("Port number is: " + port);

            //chiude
            reader.close();
        } catch (FileNotFoundException ex) {
        // file does not exist
        } catch (IOException ex) {
        // I/O error
        }
    }
}