public class CellMain {
    public static void main(String[] args) {
        int MAX_TICKS=1000000;
        //Ho un'unica Cell inizializzata a 0
        Cell cell=new Cell(0);

        //Creo i due task runnable, uno con +1, uno con -1
        Counter up = new Counter(cell, 1, MAX_TICKS);
        Counter down = new Counter(cell, -1, MAX_TICKS);

        //Mandiamo in concorrenza i 2 thread sulla stessa Cell
        Thread upWorker = new Thread(up);
        Thread downWorker = new Thread(down);
        upWorker.start(); 
        downWorker.start();

        try { //join può lanciare InterruptedEsception
            upWorker.join(); 
            downWorker.join();
        }
        catch(Exception e) {};

        //L'output cambierà per via della race condition su Cell (versione non TS)
        System.out.println("");
        System.out.printf("Cell value: %d\n", cell.get());
        System.out.flush();
    }
}
