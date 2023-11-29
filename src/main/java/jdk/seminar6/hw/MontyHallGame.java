package jdk.seminar6.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MontyHallGame {
    static final int DOORS_NUMBER = 3;
    List<Door> doors;
    private int winnerDoor;

    MontyHallGame() {
        Random random = new Random();
        doors = new ArrayList<>();
        this.winnerDoor = random.nextInt(DOORS_NUMBER);
        for (int i = 0; i < DOORS_NUMBER; i++) {
            if (i == winnerDoor) {
                doors.add(new Door(true));
            } else {
                doors.add(new Door(false));
            }
        }
    }

    public int getWinnerDoor() {
        return winnerDoor;
    }

    @Override
    public String toString() {
        return "MontyHallGame{" +
                "doors=" + doors +
                '}';
    }
}
