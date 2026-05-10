import java.util.concurrent.Callable;

//l'interfaccia callable esegue un task asincrono e rende un future
public class Calculator implements Callable <Integer> {
    private int a;
    private int b;
    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep((long)(Math.random() * 15000));
        return a + b;
    }
}