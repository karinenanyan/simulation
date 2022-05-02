import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarFactory {
    private Queue<Car> cars = new LinkedList<>();
    private int queueCapacity;
    private int carId = 1;
    public int rejectedCars = 0;

    public Queue<Car> getCars() {
        return cars;
    }

    public CarFactory(int capacity) {
        this.queueCapacity = capacity;
    }

    public int numberOfCars() {
        return this.cars.size();
    }

    /**
     * fill up queye with cars
     */
    public void enqueueOneCar() {
        if(this.cars.size() < this.queueCapacity) {
            Car car = new Car(carId ++, new Random().nextInt(5) + 1);
            this.cars.add(car);
        } else {
            rejectedCars++;
        }
    }

    public void enqueueCar(Car car) {
        this.cars.add(car);
    }

    public Car dequeueCar() {
        if (this.cars.size() > 0)
            return cars.poll();
        return null;
    }
}
