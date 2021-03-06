package io.zipcoder.casino;

import io.zipcoder.casino.BlackJack.BlackJack;
import io.zipcoder.casino.BlackJack.BlackJackPlayer;
import io.zipcoder.casino.CardGame.Card;
import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;

public class Console {

    public static void inputError() {
        System.out.println("\nThat's not a valid option, please try again.");
    }

    public static void threadSleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("\nThread sleep error.");
        }
    }

    public static void finalGoodbye(Player player) {
        if (player.getWallet() < 0) {
            System.out.println("\nHey!! You still owe us $" + (player.getWallet() * -1) + "!!");
        } else {
            System.out.println("\nThanks for coming to Thunder Theta! Your New Balance: $" + player.getWallet());
        }
    }

    public static void forceBlackJack(String input) {
        System.out.println("\nI know you said " + input + ", but you actually want to play BlackJack, right? Let's play BlackJack!!");
    }

    public static void blackJackWelcome() {
        System.out.println("\nHi! Welcome to BlackJack!\n\nThe minimum bet is $50.");
    }

    public static String inGameMenu(BlackJack blackJack, BlackJackPlayer blackJackPlayer, BlackJackPlayer dealer) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Current Hand: \n\nMYSTERY-CARD || " + formatHand(dealer.getDealerHand()) + "\n\nDealer's Hand Value: ??" +
                "\n\n~~~~~~~~~~~~~~~~~~~\n\nYour Current Hand: \n\n" + formatHand(blackJackPlayer.getPlayerHand()) + "\n\nYour Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)));
        threadSleep();
        String response = InputOutput.getStringInput("\n\n~~~~~~~~~~~~~~~~~~~\n\nYOUR TURN" + "\n\n~~~~~~~~~~~~~~~~~~~\n\nWhat do you want to do?\n\n<< Hit - Stand - Double Down - Quit >>").toUpperCase();
        return response;
    }

    public static void endGame(BlackJack blackJack, BlackJackPlayer blackJackPlayer, char result) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDETERMINING WINNER...");
        threadSleep();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Hand Value: " + blackJack.getDealer().getHandValue());
        System.out.println("\nYour Hand Value: " + blackJackPlayer.getHandValue());
        winOrLose(blackJackPlayer, result);
    }

    public static void winOrLose(BlackJackPlayer blackJackPlayer, char result) {
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

    public static void doubleDownBet(BlackJackPlayer blackJackPlayer) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nNEW BET: " + blackJackPlayer.getBetPot());
    }

    public static void hitCard(BlackJackPlayer blackJackPlayer, Card card) {
        if (blackJackPlayer.getPlayer().getName().equals("Dealer")) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer Hit: " + card.toString());
            threadSleep();
            System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Current Hand: \n\nMYSTERY-CARD || " + formatHand(blackJackPlayer.getDealerHand()) + "\n\nDealer's Hand Value: ??");
        } else {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYou Hit: " + card.toString());
            threadSleep();
            // System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYour Current Hand: \n\n" + formatHand(blackJackPlayer.getPlayerHand()) + "\n\nYour Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)));
        }
    }

    public static void standingStill() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nStanding still!");
        threadSleep();
    }

    public static void dealerTurn() {
        threadSleep();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDEALER'S TURN");
    }

    public static void initiateGame(BlackJackPlayer blackJackPlayer) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYOUR BET: $" + blackJackPlayer.getBetPot());
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nGAME START - DEALING CARDS");
        threadSleep();
    }

    public static String askPlayAgain() {
        return InputOutput.getStringInput("\n~~~~~~~~~~~~~~~~~~~\n\nWould you like to play again?\n\n<< Yes - No >>").toUpperCase();
    }

    public static int getPlayerBet() {
        return InputOutput.getIntInput("\n~~~~~~~~~~~~~~~~~~~\n\nHow much would you like to bet?");
    }

    public static void minBetNotMet() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nThe minimum bet is $50. Please try again.");
    }

    public static void goodByeMessage() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nThanks for playing! Now returning to the Casino Lobby.");
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

