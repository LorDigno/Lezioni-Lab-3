import java.net.*;

public class LocalPortScanner {

    public static void main(String args[]){
        for (int port= 1; port<= 1030; port++){
            try {
                //controlla quali porte basse non sono gia utilizzate in ricezione
                ServerSocket server = new ServerSocket(port);
                System.out.println(port + " libera");
            }
            catch (BindException ex){
                //System.out.println(port + "occupata");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
            //dato che tutte le porte basse sono note saranno tutte occupate
            //solo dalla 1024 in poi sono libere
        }
    } 
}