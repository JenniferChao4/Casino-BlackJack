package io.zipcoder.casino.DiceGame;

import io.zipcoder.casino.Interfaces.Game;

public abstract class DiceGame implements Game {

    private int numOfDice = 2;
    private int[] die = new int[numOfDice];

    public int rollDice(int numOfDice) {
        int sum = 0;

        for (int i = 0; i < numOfDice; i++) {
            die[i] = (int) Math.floor((Math.random() * 6) + 1);
            sum += die[i];
            System.out.println("Die #" + (i+1) + " = " + die[i]);
        }
        return sum;
    }

}
