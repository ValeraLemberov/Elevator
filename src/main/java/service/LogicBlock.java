package service;

import model.Building;
import model.Elevator;
import model.Floor;
import model.Passenger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicBlock {
    private static final int MAX_PASSENGERS = 5;
    private Building building;
    private Elevator elevator;

    public LogicBlock(Building building, Elevator elevator) {
        this.building = building;
        this.elevator = elevator;
    }

    public void startMovement(int counter) {
        while (--counter != 0) {
            print(counter);
            Floor floor = building.getFloors().get(elevator.getCurrentFloor());
            List<Passenger> passengersFromElevator = elevator.peekPassengersFromElevator();
            int vacanciesNumber = MAX_PASSENGERS - elevator.getPassengers().size();
            List<Passenger> passengersFromFloor;

            if(elevator.getCurrentFloor() != elevator.getFinalFloor() & vacanciesNumber == 0){
                elevator.moving();
                continue;
            }

            if (elevator.getCurrentFloor() == elevator.getFinalFloor()) {
                if (floor.getPassengers().isEmpty()) {
                    floor.addAllPassengers(passengersFromElevator);
                    floor = building.getFloors().stream().max(Comparator.comparingInt(l -> l.getPassengers().size())).get();
                    elevator.setCurrentFloor(floor.getNumber());
                }
                long upNumber = floor.getPassengers().stream().filter(Passenger::isUpButton).count();
                elevator.setUp(floor.getPassengers().size() / 2 <= upNumber);
            }
            passengersFromFloor = floor.getPassengerOnTheWay(elevator.isUp(), vacanciesNumber);
            floor.addAllPassengers(passengersFromElevator);
            elevator.addPassengerToElevator(passengersFromFloor);
            elevator.moving();
        }
    }

    public void print(int counter){
        String elevatorMessage = "***Step " + counter + "***\n" +
                "The elevator on " + elevator.getCurrentFloor() + " floor.\n"
                + "Passenger on elevator " + elevator.getPassengers().size() + ".\n"
                + "Direction is " + (elevator.isUp() ? "up" : "down") + ".\n"
                + "The final floor is " + elevator.getFinalFloor();
        String buildingMessage = "***Passengers on the floors***\n" + IntStream.range(0, building.getMaxFloor())
                .boxed().sorted((i1, i2) -> -Integer.compare(i1, i2))
                .map(i -> "Passengers on " + i + " floor is " + building.getFloors().get(i).getPassengers().size() + ".\n")
                .collect(Collectors.joining());
        System.out.println(elevatorMessage);
        System.out.println();
        System.out.println(buildingMessage);
        System.out.println("------------------------------------");

    }
}