import event.Event;

import java.io.IOException;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;

public class Simulation {
    private CarFactory carFactory;
    private EventHandler eventHandler;

    public Simulation(int queueCapacity, int timeLimit) {
        this.carFactory = new CarFactory(queueCapacity);
        this.eventHandler = new EventHandler(this.carFactory, timeLimit);
    }

    public void run() {
        while(this.eventHandler.getClock().timeAvailable()) {
            // fill up queue with new cars
            this.carFactory.enqueueOneCar();
            // take one car from the queue
            Car car = this.carFactory.dequeueCar();
            this.eventHandler.processEvent(car);
            if (car.getState() != State.LEAVING) {
                this.carFactory.enqueueCar(car);
            }
        }
        while(!carFactory.getCars().isEmpty()) {
            Car car = this.carFactory.dequeueCar();
            this.eventHandler.processEvent(car);
            if (car.getState() != State.LEAVING) {
                this.carFactory.enqueueCar(car);
            }
        }
    }

   public String log() {
        StringBuilder builder = new StringBuilder();
        builder.append("Time stamp (sec), CAR ID, Event Type, # Cars in the System");
        for(Event event : this.eventHandler.getEventList()) {
            builder.append("\n");
            builder.append(event.getTime() + "," + event.getCarId() + "," + event.getCapacity() + "," + event.label() + "," + event.getCarsInSystem());
        }
        return builder.toString();
    }

    public double avgNrOfPeople() {
        Map<Integer, Integer> carMap = new HashMap<Integer, Integer>();
        int total = 0;
        for(Event departure : this.eventHandler.getEventList()) {
            carMap.put(departure.getCarId(), departure.getCapacity());
        }

        for (Map.Entry<Integer, Integer> capacity :
                carMap.entrySet()) {
            total = total + capacity.getValue();
        }

        double avgNrOfPeople = total * 1.0 / carMap.size();
        return avgNrOfPeople;
    }

    public double avgNrOfCars() {

        int total = 0;
        for(Event event : this.eventHandler.getEventList()) {
            total = total + event.getCarsInSystem();
        }

        double avgNrOfCars = total * 1.0 / this.eventHandler.getEventList().size();
        return avgNrOfCars;
    }

    public void NrOfCarsToCsv() {
        HashMap<Integer, Integer> nrOfCars = new HashMap<Integer, Integer>();
        for(Event event : this.eventHandler.getEventList()) {
            nrOfCars.put(event.getTime(), event.getCarsInSystem());
        }

        String eol = System.getProperty("line.separator");

        try (Writer writer = new FileWriter("/Users/kara/Desktop/results.csv")) {
            for (HashMap.Entry<Integer, Integer> entry : nrOfCars.entrySet()) {
                 writer.append(String.valueOf(entry.getKey()))
                        .append(',')
                        .append(String.valueOf(entry.getValue()))
                        .append(eol);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public int leavingCars() {
        return this.eventHandler.leavingCars;
    }


    public static void main(String[] args) {

        Simulation simulation = new Simulation(10, 7200);
        simulation.run();
        System.out.println(simulation.log());


       System.out.println();

        System.out.println("Question 1");
        System.out.println("The average number of people in one car is: " + simulation.avgNrOfPeople());
        System.out.println();

        System.out.println("Question 2");
        System.out.println("The average number of cars in the testing lane is: " + simulation.avgNrOfCars());
        System.out.println();


        System.out.println("Question 3");
        System.out.println("Number of cars which had to leave because the queue was already full: " + simulation.leavingCars());
        System.out.println();


        System.out.println("Question 4");

        /*int[] values = {10, 12, 14, 16};
        int[] rejectedCarsArray = new int[values.length];

        for(int i = 0; i < values.length; i++) {
            Simulation simulation = new Simulation(values[i], 7200);
            simulation.run();
            rejectedCarsArray[i] = simulation.carFactory.rejectedCars;
        }

        for(int i = 0; i < rejectedCarsArray.length; i++) {
            System.out.print(rejectedCarsArray[i] + " ");
        }*/

        simulation.NrOfCarsToCsv();
    }
}
