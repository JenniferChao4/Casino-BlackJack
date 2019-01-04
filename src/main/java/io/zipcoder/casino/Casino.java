package io.zipcoder.casino;

import io.zipcoder.casino.BlackJack.BlackJack;
import io.zipcoder.casino.BlackJack.BlackJackGameplay;

public final class Casino {

    private final static Casino instance = new Casino();

    private static Player player = new Player();

    private String casinoName;

    public Casino() {
    }

    public Casino(Player player) {
        this.casinoName = "Thunder Theta";
        this.player = player;
    }

    public void chooseGame() {
        Player player = Casino.getPlayer();
        String userInput = InputOutput.getStringInput("\nHi "+ player.getName() + "! What game would you like to play?\n\n<< BlackJack - Solitaire - Craps - Slots - Leave >>");
        String input = userInput.toUpperCase();

        boolean flag = true;

        while (flag) {
            switch (input) {
                case "SOLITAIRE":
                    Console.forceBlackJack(input);
                    BlackJack blackJack = new BlackJack(player);
                    BlackJackGameplay blackJackGameplay = new BlackJackGameplay(blackJack);
                    Console.blackJackWelcome();
                    blackJackGameplay.start();
                    flag = false;
                    break;
                case "BLACKJACK":
                    blackJack = new BlackJack(player);
                    blackJackGameplay = new BlackJackGameplay(blackJack);
                    Console.blackJackWelcome();
                    blackJackGameplay.start();
                    flag = false;
                    break;
                case "CRAPS":
                    Console.forceBlackJack(input);
                    blackJack = new BlackJack(player);
                    blackJackGameplay = new BlackJackGameplay(blackJack);
                    Console.blackJackWelcome();
                    blackJackGameplay.start();
                    flag = false;
                    break;
                case "SLOTS":
                    Console.forceBlackJack(input);
                    blackJack = new BlackJack(player);
                    blackJackGameplay = new BlackJackGameplay(blackJack);
                    Console.blackJackWelcome();
                    blackJackGameplay.start();
                    flag = false;
                    break;
                case "LEAVE":
                    Console.finalGoodbye(player);
                    flag = false;
                    break;
                default:
                    Console.inputError();
                    this.chooseGame();
                    break;
            }
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public String getCasinoName() {
        return this.casinoName;
    }

    public static Casino getInstance() {
        return instance;
    }

}


