public class Tabelline {
    public static void main(String[] args) {
        for (int i=1; i<=10; i++){
            Calculator calculator=new Calculator(i);
            Thread thread=new Thread(calculator);
            thread.start();
        }
        System.out.println("Avviato Calcolo Tabelline"); 
    } 
}
