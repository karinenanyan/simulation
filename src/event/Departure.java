package event;

public class Departure extends Event {
    @Override
    public String label() {
        return "Departure";
    }

    public Departure(int time, int carId, int capacity, int carsInSystem) {
        super(time, carId, capacity, carsInSystem);
    }
}
