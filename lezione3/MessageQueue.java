//Produttore, Consumatore con Monitor
public class MessageQueue{
    int putptr, takeptr, count;
    final Object[] items;
    public MessageQueue(int size){
        items = new Object[size];
        count=0;putptr=0;takeptr=0;
    }

    //metodo sincronizzato da produttore
    public synchronized void produce(Object x){ 

        //aspetto se la coda è piena
        while (count == items.length){
            try {
                wait();
            }
            catch(Exception e) {}
        }
        
        // gestione puntatoricoda
        items[putptr] = x; 
        putptr++;
        ++count;

        if (putptr == items.length) putptr = 0;

        System.out.println("Message Produced"+x);

        //sveglio eventuali consumatori fermi alla coda vuota
        notifyAll();
    }

    //metodo sincronizzato da consumatore
    public synchronized Object consume() {

        //aspetto se la coda è vuota
        while (count == 0){
            try {
                wait();}
            catch(InterruptedException e) {}
        }

        // gestione puntatori coda
        Object data = items[takeptr]; 
        takeptr=takeptr+1; 
        --count;
        
        if (takeptr == items.length) {takeptr = 0;};

        //sveglio eventuali produttori fermi sulla coda piena
        notifyAll();

        System.out.println("Message Consumed"+data);
        return data;
    }
}