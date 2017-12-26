package org.dido.algorithms.codility;

import java.util.*;
import java.util.stream.IntStream;

enum Direction {
    UP, DOWN
}

public class ElevatorSimulation {

    public static void main(String[] args) {
        // the floors will be from ground floor(0) to numberOfFloors
        final int numberOfFloors = 10;
        final int maxNumberOfPeople = 3;
        final int maxWeight = 145;

        Elevator elevator = new Elevator(numberOfFloors, maxNumberOfPeople, maxWeight);

        // Init empty waiting queues per floor starting from floor 0 to numberOfFloors
        final List<List<Passenger>> passengerListPerFloor = new ArrayList<>(numberOfFloors + 1);
        for (int i = 0; i < numberOfFloors + 1; i++) {
            passengerListPerFloor.add(new LinkedList<>());
        }

        List<Passenger> passengers = createPassengersList();
        addPassengersInQueues(passengerListPerFloor, passengers, elevator);

        // ready to move the elevator
        while (elevator.hasMoreJobs()) {
            // load passengers that are waiting for the elevator to the direction it goes
            Iterator<Passenger> passengerWaitingCurrentFloor = passengerListPerFloor.get(elevator.getCurrentFloor()).iterator();
            while (passengerWaitingCurrentFloor.hasNext()) {
                Passenger nextInTheQueue = passengerWaitingCurrentFloor.next();
                if (elevator.getDirection().equals(nextInTheQueue.getDirection())
                        // load() will return true only if there is a room for the passenger
                        && elevator.load(nextInTheQueue)) {
                    //there is a room for the person and put her/him in the elevator
                    passengerWaitingCurrentFloor.remove();
                    // passengers press the button for the desired floor inside the elevator
                    elevator.requestDestinationFloor(nextInTheQueue.getToFloor());
                }
            }

            if (passengerListPerFloor.get(elevator.getCurrentFloor()).stream()
                    .anyMatch(passenger -> passenger.getDirection().equals(elevator.getDirection()))) {
                // Not all passenger were able to go in. they press the button again.
                if (Direction.UP.equals(elevator.getDirection())) {
                    elevator.pushUpButton(elevator.getCurrentFloor());
                } else {
                    elevator.pushDownButton(elevator.getCurrentFloor());
                }
            }

            // move to the next requested floor
            elevator.move();

            // unload passenger to the current floor
            elevator.unload();

        }

    }

    private static void addPassengersInQueues(List<List<Passenger>> passengerListPerFloor, List<Passenger> passengers, Elevator elevator) {
        // Every passenger goes to the queue and pushes the elevator button up/down
        passengers.forEach(passenger -> {
            passengerListPerFloor.get(passenger.getFromFloor()).add(passenger);
            if (passenger.getFromFloor() > passenger.getToFloor()) {
                elevator.pushDownButton(passenger.getFromFloor());
            } else {
                elevator.pushUpButton(passenger.getFromFloor());
            }
        });
    }

    private static List<Passenger> createPassengersList() {
        // Init all the passenger and put them in the queues
        return Arrays.asList(
                new Passenger(3, 7, 83),
                new Passenger(5, 0, 65),
                new Passenger(4, 10, 73),
                new Passenger(9, 4, 48),
//                new Passenger(0, 5, 17),
//                new Passenger(3, 6, 111),
//                new Passenger(5, 7, 99),
//                new Passenger(3, 10, 35),
//                new Passenger(5, 7, 84),
//                new Passenger(0, 7, 65),
//                new Passenger(3, 1, 52),
//                new Passenger(3, 0, 45),
//                new Passenger(0, 4, 61),
//                new Passenger(9, 5, 77),
//                new Passenger(2, 7, 67),
                new Passenger(6, 8, 81)
        );
    }
}

class Elevator {

    // Up buttons on every floor
    private boolean[] pushedUpButtons;
    // Down buttons on every floor
    private boolean[] pushedDownButtons;
    // Buttons with number of floors inside
    private boolean[] pushedFloorButtons;
    private List<Passenger> passengers = new LinkedList<>();
    private int numberOfFloors;
    private int currentFloor;
    private Direction direction;
    private int maxNumberOfPeople;
    private int maxWeight;

    public Elevator(int numberOfFloors, int maxNumberOfPeople, int maxWeight) {
        if (numberOfFloors <= 0) {
            throw new IllegalArgumentException("numberOfFloors should be positive int");
        }
        if (maxNumberOfPeople <= 0) {
            throw new IllegalArgumentException("maxNumberOfPeople should be positive int");
        }
        if (maxWeight <= 0) {
            throw new IllegalArgumentException("maxWeight should be positive int");
        }
        this.numberOfFloors = numberOfFloors;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.maxWeight = maxWeight;
        currentFloor = 0;
        direction = Direction.UP;

        pushedUpButtons = new boolean[numberOfFloors + 1];
        pushedDownButtons = new boolean[numberOfFloors + 1];
        pushedFloorButtons = new boolean[numberOfFloors + 1];
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    void pushUpButton(int floor) {
        pushedUpButtons[floor] = true;
    }

    void pushDownButton(int floor) {
        pushedDownButtons[floor] = true;
    }

    void requestDestinationFloor(int floor) {
        pushedFloorButtons[floor] = true;
    }

    boolean load(Passenger passenger) {
        if (Direction.UP.equals(direction)) {
            pushedUpButtons[currentFloor] = false;
        } else {
            pushedDownButtons[currentFloor] = false;
        }

        int currentTotalWeight = passengers.stream().mapToInt(Passenger::getWeight).sum();
        if (currentTotalWeight + passenger.getWeight() <= maxWeight
                && passengers.size() < maxNumberOfPeople) {
            // there is a room for the passenger
            System.out.println("Passenger entered in the elevator on floor: " + currentFloor);
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    void unload() {
        Iterator<Passenger> passengersIterator = passengers.iterator();
        while (passengersIterator.hasNext()) {
            Passenger passenger = passengersIterator.next();
            if (passenger.getToFloor() == currentFloor) {
                // the passenger goes out.
                System.out.println("Passenger exit the elevator on floor: " + currentFloor);
                passengersIterator.remove();
            }
        }
    }

    void move() {
        boolean changeDirection = false;
        // Find the min of the push button same direction floor and pushed floor button
        if (Direction.UP.equals(direction)) {
            // the elevator was moving UP and will continue this direction if there is somebody who could use it
            int nextPushedFloorButton = IntStream.range(currentFloor + 1, pushedFloorButtons.length)
                    .filter(index -> pushedFloorButtons[index])
                    .findFirst()
                    .orElse(pushedFloorButtons.length + 1);

            int nextPushedUpButton = IntStream.range(currentFloor + 1, pushedFloorButtons.length)
                    .filter(index -> pushedUpButtons[index])
                    .findFirst()
                    .orElse(pushedFloorButtons.length + 1);

            if (nextPushedFloorButton > pushedFloorButtons.length && nextPushedUpButton > pushedFloorButtons.length) {
                // go to the highest requested if such and change direction
                int highestRequested = getHighestRequested();
                currentFloor = highestRequested >= 0 ? highestRequested : 0;
                direction = Direction.DOWN;
            } else {
                // continue UP
                currentFloor = Math.min(nextPushedFloorButton, nextPushedUpButton);
            }
        } else {
            // the elevator was moving DOWN and will continue this direction if there is somebody who could use it
            int nextPushedFloorButton = IntStream.range(0, currentFloor)
                    // reverse
                    .map(i -> currentFloor - 1 - i)
                    .filter(index -> pushedFloorButtons[index])
                    .findFirst()
                    .orElse(-1);

            int nextPushedDownButton = IntStream.range(0, currentFloor)
                    // reverse
                    .map(i -> currentFloor - 1 - i)
                    .filter(index -> pushedDownButtons[index])
                    .findFirst()
                    .orElse(-1);

            if (nextPushedFloorButton == -1 && nextPushedDownButton == -1) {
                int lowestRequested = getLowestRequested();
                currentFloor = lowestRequested >= 0 ? lowestRequested : 0;
                direction = Direction.UP;
            } else {
                currentFloor = Math.max(nextPushedFloorButton, nextPushedDownButton);
            }
        }

        // button to this floor in the cabin goes inactive
        pushedFloorButtons[currentFloor] = false;

        System.out.println("Elevator move to floor: " + currentFloor);

    }

    private int getLowestRequested() {
        for (int i = 0; i < numberOfFloors; i++) {
            if (pushedUpButtons[i] || pushedDownButtons[i] || pushedFloorButtons[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getHighestRequested() {
        for (int i = numberOfFloors; i >= 0; i--) {
            if (pushedUpButtons[i] || pushedDownButtons[i] || pushedFloorButtons[i]) {
                return i;
            }
        }
        return -1;
    }

    boolean hasMoreJobs() {
        for (int i = 0; i < numberOfFloors; i++) {
            if (pushedUpButtons[i] || pushedDownButtons[i] || pushedFloorButtons[i]) {
                return true;
            }
        }
        return false;
    }

}

class Passenger {

    private int fromFloor;
    private int toFloor;
    private int weight;

    Passenger(int fromFloor, int toFloor, int weight) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
        this.weight = weight;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    public int getToFloor() {
        return toFloor;
    }

    public int getWeight() {
        return weight;
    }

    Direction getDirection() {
        return fromFloor > toFloor ? Direction.DOWN : Direction.UP;
    }

}
