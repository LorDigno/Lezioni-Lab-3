public class BackAndForth {
    //genera race condition sulla shell come risorsa condivisa
    public static void main(String args[]){
        Thread ts= new Thread(new Forth());
        ts.start();
        Thread bk= new Thread(new Back());
        bk.start();
    }
}