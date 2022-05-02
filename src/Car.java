public class Car {
    private int carID;
    private State state;
    private int capacity;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCarID() {
        return carID;
    }

    public int getCapacity() {
        return capacity;
    }

    public Car(int carId, int capacity) {
        this.carID = carId;
        this.capacity = capacity;
        this.state = State.INIT;
    }
}
