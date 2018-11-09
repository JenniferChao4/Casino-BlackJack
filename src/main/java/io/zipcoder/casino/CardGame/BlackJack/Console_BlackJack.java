package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;

public class Console_BlackJack {

    private static String tie = "\n\n~~~~~~~~~~~~~~~~~~~\n\nTie, no one wins";
    private static String win = "\n\n~~~~~~~~~~~~~~~~~~~\n\nYou win!";
    private static String lose = "\n\n~~~~~~~~~~~~~~~~~~~\n\nYou lose!";

    public static void printResult(BlackJackPlayer blackJackPlayer, BlackJackPlayer dealer, char result) {
        String playerResult = "";
        switch (result) {
            case 'T':
                playerResult = tie;
                break;
            case 'W':
                playerResult = win;
            case 'L':
                playerResult = lose;
        }
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYour Hand Value: " + blackJackPlayer.getHandValue() + "\n\nDealer's Hand Value: " + dealer.getHandValue() + playerResult);
    }

    public static void printMoney(BlackJackPlayer blackJackPlayer, char result) {
        String playerResult = "";

        switch (result) {
            case 'W':
                playerResult = "Won";
                break;
            case 'L':
                playerResult = "Lost";
                break;
        }
        System.out.println("\nYou " + playerResult + ": $" + blackJackPlayer.getBetPot() * 2 + "\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
    }
}

