package service;

import java.util.Random;

public class Randomizer {
    public static final int MAX_FLOOR = getFloorsCount();
    private static int counter = 0;

    public static int getPassengersNumber(){
        return new Random().nextInt(10);
    }
    public static int getRequiredFloor(int currentLevel){
        int requiredFloor = currentLevel;
        while (currentLevel == requiredFloor){
            requiredFloor = new Random().nextInt(0, MAX_FLOOR);
        }
        return requiredFloor;
    }

    private static int getFloorsCount(){
        return new Random().nextInt(5,20);
    }

    public static int getUniqueId(){
        return ++counter;
    }
}
