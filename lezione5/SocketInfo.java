import java.io.*;
import java.net.*;

public class SocketInfo {
    public static void main(String [] args){
        //prende i domini a cui connettersi da linea di comando 
        for (String host: args) {
            try {
                //apre un socket http
                Socket theSocket = new Socket (host, 80);

                //print di varie info sul socket
                System.out.println(
                    "Connected to "+theSocket.getInetAddress()
                    +" on port"+ theSocket.getPort()+ " from port "
                    + theSocket.getLocalPort() + " of"
                    + theSocket.getLocalAddress());

            } catch(UnknownHostException ex) {
                System.out.println("I cannot find"+host);
            }
            catch(SocketException ex) {
            System.out.println("Could not connect to"+host);
            }
            catch(IOException ex) { 
                System.out.println(ex);
            }
        }
    }
}