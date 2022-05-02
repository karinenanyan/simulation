package event;

public class Testing extends Event {
    @Override
    public String label() {
        return "Testing";
    }
    public Testing(int time, int carId, int capacity, int numberOfCars) {
        super(time, carId, capacity, numberOfCars);
    }
}
