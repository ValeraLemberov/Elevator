package model;

import lombok.Data;
import service.Randomizer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Data
public class Floor {
    private int number;
    private List<Passenger> passengers;

    public Floor(int number) {
        this.number = number;
        this.passengers = IntStream.range(0, Randomizer.getPassengersNumber())
                .mapToObj(i-> new Passenger(number)).collect(Collectors.toList());
    }

    public List<Passenger> getPassengerOnTheWay(boolean direction, int limit){
        List<Passenger> all = passengers;
        List<Passenger> result = direction
                ? all.stream().filter(Passenger::isUpButton).limit(limit).collect(Collectors.toList())
                : all.stream().filter(passenger -> !passenger.isUpButton()).limit(limit).collect(Collectors.toList());
        all.removeAll(result);
        passengers = all;
        return result;
    }

    public void addAllPassengers(List<Passenger> passengers){
        passengers.stream()
                .peek(passenger -> passenger.setRequiredFloor(number))
                .forEach(this.passengers::add);
    }
}
