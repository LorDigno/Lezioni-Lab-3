class Counter implements Runnable {
    public int ticks ;
    private Cell cell;
    private int delta;
    private int maxTicks;
    Counter(Cell cell, int delta, int maxTicks) {
        this.cell = cell;
        this.delta = delta;
        this.maxTicks = maxTicks;
    }

    public void run() {
        ticks = 0;
        while (ticks < maxTicks) {
            cell.update(delta);
            ++ticks;
        }
    }
}
