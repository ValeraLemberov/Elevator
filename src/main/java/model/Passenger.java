package model;

import lombok.Data;
import service.Randomizer;

@Data
public class Passenger {
    private int id;
    private int requiredFloor;
    private boolean isUpButton;

    public Passenger(int currentFloor) {
        this.requiredFloor = Randomizer.getRequiredFloor(currentFloor);
        this.isUpButton = requiredFloor > currentFloor;
        this.id = Randomizer.getUniqueId();
    }

    public void setRequiredFloor(int currentFloor){
        requiredFloor = Randomizer.getRequiredFloor(currentFloor);
        isUpButton = requiredFloor > currentFloor;
    }
}
