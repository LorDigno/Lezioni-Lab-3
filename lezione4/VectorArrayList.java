import java.util.ArrayList; //Non TS
import java.util.Collections;
import java.util.List; //Versione TS
import java.util.Vector;

public class VectorArrayList {

    //task da far eseguire ad entrambe le implementazioni
    public static void addElements(List<Integer> list){
        for (int i=0; i< 1000000; i++){
            list.add(i);
        } 
    }
    public static void main (String args[]){  
        //misuro il tempo impegato dal vector    
        final long start1 =System.nanoTime();
        addElements(new Vector<Integer>());
        final long end1=System.nanoTime();

        //misuro il tempo impegato dall'ArrayList
        final long start2 =System.nanoTime();
        addElements(new ArrayList<Integer>());
        final long end2=System.nanoTime();

        //version ArrayList con wrapper synchronized
        final long start3 =System.nanoTime();
        //wrapper via collections come factory
        addElements(Collections.synchronizedList(new ArrayList<Integer>()));
        final long end3=System.nanoTime();

        //stampa
        System.out.println("Vector time "+ (end1-start1));
        System.out.println("ArrayList time "+ (end2-start2)); 
        System.out.println("Sync ArrayList time "+ (end3-start3));
        //dato che ArrayList non è TS sarà più veloce
        //quella col wrapper ha una lock su tutta la struttura, andrà piano
    }
}


