package jdk.seminar6.hw;
/*
    Задание
    В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
    (Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса
    (запустить игру в цикле на 1000 и вывести итоговый счет).
    Необходимо:
    1. Создать свой Java Maven или Gradle проект;
    2. Самостоятельно реализовать прикладную задачу;
    3. Сохранить результат в HashMap<шаг теста, результат>
    4. Вывести на экран статистику по победам и поражениям

    Парадокс:
    Из тех, кто менял дверь, двое получили машину и один — козу.
    Из тех, кто не менял — наоборот.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static final int NUMBER_OF_GAMES = 1_000;    // количество игр

    public static void main(String[] args) {

        System.out.println("Статистика игр со сменой двери:");
        MontyHallGame game1 = new MontyHallGame();
        // контрольный вывод
//        int count = 1;
//        for (Door door : game1.doors) {
//            System.out.println("Door #" + count + ": " + door);
//            count++;
//        }
        Map<Integer, Integer> gamesWonWithDoorChange = getGamePoolResult(game1, true);
//        System.out.println("Выигравших по схеме 1: " + gamesWonWithDoorChange);
        System.out.println(getGameStat(gamesWonWithDoorChange));
        System.out.println("--------------------------------");
        System.out.println("Статистика игр без смены двери:");
        MontyHallGame game2 = new MontyHallGame();
        // контрольный вывод
//        count = 1;
//        for (Door door : game2.doors) {
//            System.out.println("Door #" + count + ": " + door);
//            count++;
//        }
        Map<Integer, Integer> gamesWonWithOutDoorChange = getGamePoolResult(game2, false);
//        System.out.println("Выигравших по схеме 2: " + gamesWonWithOutDoorChange);
        System.out.println(getGameStat(gamesWonWithOutDoorChange));

    }

    /**
     * Результат игр
     *
     * @param game
     * @param isDoorChange
     * @return
     */
    static Map<Integer, Integer> getGamePoolResult(MontyHallGame game, boolean isDoorChange) {
        Map<Integer, Integer> gamesWins = new HashMap<>();
        int countWins = 0;
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {

            // результат игры:
            boolean winnerResult = (isDoorChange) ? gamingWithChangeDoor(game) : gamingWithOutChangeDoor(game);
            if (winnerResult) {
                countWins++;
            }
        }
        gamesWins.put(NUMBER_OF_GAMES, countWins);
        return gamesWins;
    }

    static String getGameStat(Map<Integer, Integer> map) {
        StringBuilder sb = new StringBuilder("Количество игр: ");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(", выигрышей: ");
            sb.append(entry.getValue());
            sb.append(", процент побед: ");
            sb.append((double) entry.getValue() / entry.getKey() * 100);
            sb.append("%");
        }
        return sb.toString();
    }

    /**
     * Игра со сменой выбранной двери в 1-й раз
     *
     * @param game
     * @return
     */
    static boolean gamingWithChangeDoor(MontyHallGame game) {
        boolean isWinner = false;
        int winnerDoor = game.getWinnerDoor();  // выигрышная дверь
//        System.out.println("winnerDoor = " + winnerDoor);
        Random random = new Random();           // для 1-го выбора
        int doorSelected = random.nextInt(MontyHallGame.DOORS_NUMBER);  // выбор двери
//        System.out.println("doorSelected = " + doorSelected);
        int nextDoor = random.nextInt(MontyHallGame.DOORS_NUMBER);
        while (true) {
            if (nextDoor == doorSelected) {
                nextDoor = random.nextInt(MontyHallGame.DOORS_NUMBER);
            } else {
//                System.out.println("nextDoor = " + nextDoor);
                break;
            }
        }

        if (nextDoor == winnerDoor) {
            isWinner = true;
        }
        return isWinner;
    }

    /**
     * Игра без смены выбранной двери
     *
     * @param game
     * @return
     */
    static boolean gamingWithOutChangeDoor(MontyHallGame game) {
        boolean isWinner = false;
        int winnerDoor = game.getWinnerDoor();  // выигрышная дверь
//        System.out.println("winnerDoor = " + winnerDoor);
        Random random = new Random();           // для 1-го выбора
        int doorSelected = random.nextInt(MontyHallGame.DOORS_NUMBER);  // выбор двери
//        System.out.println("doorSelected = " + doorSelected);
        int nextDoor = doorSelected;

        if (nextDoor == winnerDoor) {
            isWinner = true;
        }
        return isWinner;
    }
}
