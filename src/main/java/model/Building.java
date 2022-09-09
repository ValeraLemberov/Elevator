package model;

import lombok.Data;
import service.Randomizer;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class Building {
    private int maxFloor;
    private List<Floor> floors;

    public Building() {
        this.maxFloor = Randomizer.MAX_FLOOR;
        this.floors = IntStream.range(0, maxFloor).mapToObj(Floor::new).toList();
    }
}
