package event;

public abstract class Event implements Comparable<Event> {
    private int time;
    private int carId;
    private int capacity;
    private int carsInSystem;

    public int getCarsInSystem() {
        return carsInSystem;
    }

    public abstract String label();

    public int getTime() {
        return time;
    }

    public int getCarId() {
        return carId;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int compareTo(Event otherEvent) {
        return Integer.compare(this.time, otherEvent.time);
    }

    public Event(int time, int carId, int capacity, int carsInSystem) {
        this.time = time;
        this.carId = carId;
        this.capacity = capacity;
        this.carsInSystem = carsInSystem;
    }
}
