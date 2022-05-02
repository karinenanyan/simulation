public class Clock {
    private int limit = 0;
    private int counter = 0;

    public Clock(int limit) {
        this.counter = 0;
        this.limit = limit;
    }

    public int timePassed() {
        return this.counter;
    }

    public boolean timeAvailable() {
        return this.counter < this.limit;
    }

    public void pass(int time) {
        this.counter += time;
    }
}
