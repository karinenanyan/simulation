import event.Arrival;
import event.Departure;
import event.Event;
import event.Testing;

import java.util.*;

public class EventHandler {
    public EventHandler(CarFactory factory, int timeBudget) {
        this.clock = new Clock(timeBudget);
        this.factory = factory;
    }

    private Clock clock;
    private CarFactory factory;


    public Clock getClock() {
        return clock;
    }

    public List<Event> getEventList() {
        Collections.sort(eventList);
        return eventList;
    }

    private List<Event> eventList = new Vector<>();
    public int leavingCars = 0;

    public void processEvent(Car car) {
        switch (car.getState()) {
            case INIT -> {
                if (!this.clock.timeAvailable()) {
                    car.setState(State.LEAVING);
                    leavingCars++;
                    return;
                }
                car.setState(State.ARRIVING);
                int timestamp = new Random().nextInt(121) + 30;
                this.clock.pass(timestamp);
                eventList.add(
                        new Arrival(
                                this.clock.timePassed(),
                                car.getCarID(),
                                car.getCapacity(),
                                factory.numberOfCars() + 1 // using + 1 to include the car we are looking at
                        )
                );
            }
            case ARRIVING -> {
                car.setState(State.TESTING);
                int handTestNotification = new Random().nextInt(121) + 60;
                int testCovid = 240 * car.getCapacity();
                this.clock.pass(handTestNotification + testCovid);
                eventList.add(new Testing(this.clock.timePassed(), car.getCarID(), car.getCapacity(), factory.numberOfCars() + 1));
            }
            case TESTING -> {
                car.setState(State.LEAVING);
                eventList.add(new Departure(this.clock.timePassed(), car.getCarID(), car.getCapacity(), factory.numberOfCars() + 1));
            }
        }
    }
}
