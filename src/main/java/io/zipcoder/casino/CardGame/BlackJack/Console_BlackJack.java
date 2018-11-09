package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;

import java.util.ArrayList;

public class Console_BlackJack {

    public static void inputError() {
        System.out.println("That's not a valid option, please try again.");
    }

    public static void finalGoodbye() {
        System.out.println("Thanks for coming to Thunder Theta, good bye!");
    }

    public static void blackJackWelcome(Player player){
        System.out.println("\nHi " + player.getName() + "! Welcome to BlackJack!\n\nThe minimum bet is $50.");
    }

    public static String inGameMenu(BlackJack blackJack, BlackJackPlayer blackJackPlayer, BlackJackPlayer dealer) {
        String response = Console.getStringInput("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Current Hand: \n\nMYSTERY-CARD || " + formatHand(dealer.getDealerHand()) + "\n\nDealer's Hand Value: ??" +
                "\n\n~~~~~~~~~~~~~~~~~~~\n\nYour Current Hand: \n\n" + formatHand(blackJackPlayer.getPlayerHand()) + "\n\nYour Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)) +
                "\n\n~~~~~~~~~~~~~~~~~~~\n\nYOUR TURN" + "\n\n~~~~~~~~~~~~~~~~~~~\n\nWhat do you want to do?\n\n<< Hit - Stand - Double Down - Split - Quit >>").toUpperCase();
        return response;
    }

    public static void endGame(BlackJack blackJack, BlackJackPlayer blackJackPlayer, char result) {
        String name = blackJackPlayer.getPlayer().getName();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDETERMINING WINNER...");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJack.getDealer())));
        System.out.println("\nYour Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)));
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
                    break;}
    }

    public static void hitCard(BlackJackPlayer blackJackPlayer, Card card){
        if (blackJackPlayer.getPlayer().getName().equals("Dealer")) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer Hit: " + card.toString());
            System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDealer's Current Hand: \n\nMYSTERY-CARD || " + formatHand(blackJackPlayer.getDealerHand()) + "\n\nDealer's Hand Value: ??");
        } else {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYou Hit: " + card.toString());
            // System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYour Current Hand: \n\n" + formatHand(blackJackPlayer.getPlayerHand()) + "\n\nYour Hand Value: " + formatHandValue(blackJack.countPlayerHand(blackJackPlayer)));
        }
    }

    public static void standingStill() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nStanding still!");
    }

    public static void dealerTurn() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nDEALER'S TURN");
    }

    public static void initiateGame(BlackJackPlayer blackJackPlayer) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nYOUR BET: " + blackJackPlayer.getBetPot());
        System.out.println("\n~~~~~~~~~~~~~~~~~~~\n\nGAME START - DEALING CARDS");
    }

    public static String askPlayAgain() {
        return Console.getStringInput("\n~~~~~~~~~~~~~~~~~~~\n\nWould you like to play again?\n\n<< Yes - No >>").toUpperCase();
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

