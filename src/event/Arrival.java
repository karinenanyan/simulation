package event;

public class Arrival extends Event {
    @Override
    public String label() {
        return "Arrival";
    }

    public Arrival(int time, int carId, int capacity, int carsInSystem) {
        super(time, carId, capacity, carsInSystem);
    }
}
