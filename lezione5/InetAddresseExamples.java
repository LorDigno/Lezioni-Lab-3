import java.net.*;

public class InetAddresseExamples {
    //InetAddress permette di incapsulare sia IP che DomainName
    //Si può creare solo con la factory nella classe InetAddress

    //per stabilire quanto devono stare in cache le risoluzioni dns
    //java.security.Security.setProperty("networkaddress.cache.ttl","0");
    public static void main (String[] args) {

        //prima factory, getByName trova l'IP di un dominio
        try {
            InetAddress address = InetAddress.getByName("www.unipi.it");
            System.out.println(address);
        } catch (UnknownHostException ex){
            //se la risoluzione dns ha problemi
            System.out.println("Could not find www.unipi.it");
        }

        //seconda factory, getLocalHost rende il proprio IP e nome locale
        //non contatta il dns
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException ex) {
            System.out.println("Could not find this computer's address"); 
        }

        //terza factory, getAllByName rende tutti gli IP sotto un dominio
        try { 
            InetAddress [] addresses = InetAddress.getAllByName("www.repubblica.it");
            for(InetAddress address:addresses){ 
                System.out.println(address); 
            }
        } catch (UnknownHostException ex){
            System.out.println("Could not find www.repubblica.it");
        }

        //quarta factory, getLoopBackAddress (autoesplicativo)
        //non contatta il dns
        // try { Non può lanciare eccezioni
        {
            InetAddress address = InetAddress.getLoopbackAddress();
            System.out.println(address);
        }
        // } catch (UnknownHostException ex){
        //     //se la risoluzione dns ha problemi
        //     System.out.println("Could not find www.unipi.it");
        // }

        //ci sono poi vari altri metodi come getByAddress(byte[] IP) e tanti altri
        System.out.println("\n---\tAdesso Esempi di metodi d'istanza su google.com \n");
        try {
            InetAddress ia1 = InetAddress.getByName("www.google.com");

            //questo li rende come signed bytes
            byte [] address = ia1.getAddress();
            for (int i=0; i<address.length; i++){
                System.out.println(address[i]);
            }

            //questo rende il vero e proprio IP
            System.out.println(ia1.getHostAddress());
            
            System.out.println(ia1.getHostName());
            System.out.println(ia1.isReachable(10000));
            System.out.println(ia1.isLoopbackAddress());
            System.out.println(ia1.isMulticastAddress());

        } catch (Exception e) {}
        
    }
}
