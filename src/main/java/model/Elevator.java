package model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Elevator {
    private int maxFloor;
    private int currentFloor;
    private List<Passenger> passengers;
    private int finalFloor;
    private boolean isUp;

    public Elevator(int maxFloor) {
        this.currentFloor = 0;
        this.passengers = new ArrayList<>();
        this.isUp = true;
        this.finalFloor = -1;
        this.maxFloor = maxFloor;
    }

    public void addPassengerToElevator(List<Passenger> passengers) {
        this.passengers.addAll(passengers);
        finalFloor = isUp
                ? this.passengers.stream().mapToInt(Passenger::getRequiredFloor).max().orElse(-1)
                : this.passengers.stream().mapToInt(Passenger::getRequiredFloor).min().orElse(-1);

    }

    public List<Passenger> peekPassengersFromElevator() {
        List<Passenger> all = passengers;
        List<Passenger> result = all.stream()
                .filter(p-> p.getRequiredFloor() == currentFloor)
                .collect(Collectors.toList());
        all.removeAll(result);
        passengers = all;
        return result;
    }

    public void moving(){
        if(currentFloor == 0){
            currentFloor++;
            isUp = true;
        }else if(currentFloor == maxFloor-1){
            currentFloor--;
            isUp = false;
        }else{
           currentFloor += isUp ? +1 : -1;
        }
    }
}
