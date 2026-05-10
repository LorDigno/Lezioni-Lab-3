//Classe per generare la tabellina di  un numero
public class Calculator implements Runnable {
    private final int number;
    public Calculator(int number) {
        this.number=number; 
    }
    @Override
    public void run() {
        for (int i=1; i<=10; i++){
            System.out.printf("%s: %d * %d = %d\n",
                Thread.currentThread().getName(),number,i,i*number);
        }
    }
}

//Versione con Extends thread, comunque richiede start per l'avvio.
// public class Calculator extends Thread {
//     private int number;
//         public void run() {
//         for (int i=1; i<=10; i++){
//             System.out.printf("%s: %d * %d = %d\n",
//                 Thread.currentThread().getName(),number,i,i*number);
//         }
//     }
// }
