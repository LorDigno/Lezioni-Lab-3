public class MyThread extends Thread {
    //Cambi il metodo run della superclasse
    //Invece di chiamare il run del runnable associato esegue questo codice
    //Va comunque fatto partire con start che alloca il thread e chiama run
    @Override
    public void run() {
        System.out.println("MyThread running");
        System.out.println("MyThread finished");
    }

//Definizione della classe Thread di run()    
// public void run( ){
//     if (runnable != null)
//          runnable.run( ); 
//}
}

