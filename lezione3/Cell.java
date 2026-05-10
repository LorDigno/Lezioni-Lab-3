//Versione non TS
// class Cell {
//     private long value;
//     public Cell (long v){
//         this.value=v;
//     }

//     public void update(long delta) {
//         this.value += delta;
//     }

//     public long get(){
//         return value;
//     };
// }

class Cell {
    private long value;
    public Cell (long v){
        this.value=v;
    }

    //Usi la lock implicita su Cell con un metodo sychronized
    public synchronized void update(long delta) {
        this.value += delta;
    }

    public long get() {return value;};
}
