import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.*;

public class Caching {
    //da cambiare a piacimento per fare le prove
    //public static String CACHINGTIME="1000";
    public static String CACHINGTIME="1000";
    //il cachingtime viene settato statico alla prima risoluzione dns
    //e non può cambiare

    public static void main(String [] args) throws InterruptedException{
        //pone cachingtime come TTL in cache
        Security.setProperty("networkaddress.cache.ttl",CACHINGTIME);

        //misuro il tempo di esecuzione di tante risoluzioni dns
        long time1 = System.currentTimeMillis();
        for (int i=0; i<1000; i++){
            try {
                InetAddress.getByName("www.cnn.com").getHostAddress();
            }
            catch (UnknownHostException uhe){ 
                System.out.println("UHE");
            } 
        }
        long time2 = System.currentTimeMillis();
        long diff=time2-time1; 
        System.out.println("Tempo trascorso e' "+diff);

        //Quello col caching ci mette meno, di base è 10 secondi
    }
}