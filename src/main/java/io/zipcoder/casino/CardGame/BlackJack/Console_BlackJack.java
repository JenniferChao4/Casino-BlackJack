package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;

import java.util.ArrayList;

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


    public static String inGameMenu(BlackJack blackJack, BlackJackPlayer blackJackPlayer, BlackJackPlayer dealer) {
        String response = Console.getStringInput("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Current Hand: \n\nMYSTERY-CARD || " + formatHand(dealer.getDealerHand()) + "\n\nDealer's Hand Value: ??" +
                "\n\n~~~~~~~~~~~~~~~~~~~\n\nYour Current Hand: \n\n" + formatHand(blackJackPlayer.getPlayerHand()) + "\n\nYour Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)) +
                "\n\n~~~~~~~~~~~~~~~~~~~\n\nYOUR TURN" + "\n\n~~~~~~~~~~~~~~~~~~~\n\nWhat do you want to do?\n<< Hit - Stand - Double Down - Split - Quit >>").toUpperCase();
        return response;
    }

    public static void handVSHand(BlackJack blackJack, BlackJackPlayer blackJackPlayer) {
        String name = blackJackPlayer.getPlayer().getName();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Current Hand: \n\nMYSTERY-CARD || " + formatHand(blackJack.getDealer().getDealerHand()) + "\n\nDealer's Hand Value: ??");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\n" + name + "'s Current Hand: \n\n" + formatHand(blackJackPlayer.getPlayerHand()) + "\n\n" + name + "'s Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)));
    }

    public static void endOfGame(BlackJack blackJack, BlackJackPlayer blackJackPlayer, char result) {
        String name = blackJackPlayer.getPlayer().getName();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDETERMINING WINNER...");
        System.out.println("\nDealer's Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJack.getDealer())));
        System.out.println("\n" + name + "'s Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)));
        switch (result) {
            case '+':
                System.out.println("\nYou Won: $" + blackJackPlayer.getBetPot() + "\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
                break;
            case '-':
                System.out.println("\nYou Lost: $" + blackJackPlayer.getBetPot() + "\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
                break;
            default:
                System.out.println("\nTie Game.\n\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
                break;
        }
    }

    public static void winMoney(BlackJack blackJack, BlackJackPlayer blackJackPlayer) {

        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDETERMINING WINNER...");
        System.out.println("\nYou Won: $" + blackJackPlayer.getBetPot() + "\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
    }

    public static void loseMoney(BlackJack blackJack, BlackJackPlayer blackJackPlayer) {
        handVSHand(blackJack, blackJackPlayer);
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDETERMINING WINNER...");
        System.out.println("\nYou Lost: $" + blackJackPlayer.getBetPot() + "\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
    }

    public static void tieGame(BlackJack blackJack, BlackJackPlayer blackJackPlayer) {
        handVSHand(blackJack, blackJackPlayer);
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDETERMINING WINNER...");
        System.out.println("\nTie Game.\n\nCurrent Wallet: $" + blackJackPlayer.getPlayer().getWallet());
    }

    public static String formatHand(ArrayList<Card> array) {
        String stringHand = "";

        String uglyArray = array.toString();

        for (int i = 0; i < uglyArray.length(); i++) {
            if (uglyArray.charAt(i) != ' ' && uglyArray.charAt(i) != '[' && uglyArray.charAt(i) != ']' && uglyArray.charAt(i) != ',') {
                stringHand += uglyArray.charAt(i);
            } else if (uglyArray.charAt(i) == ' ') {
                stringHand += " || ";
            }
        }
        return stringHand;
    }


    public static String formatHandValue(ArrayList<Integer> array) {
        String stringHandValue = "";

        String uglyArray = array.toString();

        for (int i = 0; i < uglyArray.length(); i++) {
            if (uglyArray.charAt(i) != ' ' && uglyArray.charAt(i) != '[' && uglyArray.charAt(i) != ']' && uglyArray.charAt(i) != ',') {
                stringHandValue += uglyArray.charAt(i);
            } else if (uglyArray.charAt(i) == ' ') {
                stringHandValue += " or ";
            }
        }

        return stringHandValue;
    }
}

