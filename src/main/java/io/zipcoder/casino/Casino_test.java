package io.zipcoder.casino;

import io.zipcoder.casino.CardGame.BlackJack.BlackJack;
import io.zipcoder.casino.CardGame.BlackJack.BlackJackGameplay;
import io.zipcoder.casino.CardGame.BlackJack.Console_BlackJack;
import io.zipcoder.casino.DiceGame.Craps.Craps;

public final class Casino_test {

//    private final static Casino_test instance = new Casino_test();

    private Player player;
    private BlackJack blackJack;
    private BlackJackGameplay gamePlay;

    private String casinoName;

    public Casino_test(Player player) {
        this.casinoName = "Thunder Theta";
        this.player = player;
        this.blackJack = new BlackJack(player);
        this.gamePlay = new BlackJackGameplay(blackJack);
    }

    public void chooseGame() {
        String userInput = Console.getStringInput("\nWhat game would you like to play?\n\n<< BlackJack - Solitaire - Craps - Leave >>");
        String input = userInput.toUpperCase();

        boolean flag = true;

        while (flag) {
            switch (input) {
                case "SOLITAIRE":
                    break;
                case "BLACKJACK":
                    Console_BlackJack.blackJackWelcome(player);
                    gamePlay.start(player);
                    flag = false;
                    break;
                case "CRAPS":
                    Craps craps = new Craps(player);
                    flag = false;
                    craps.gamePlay();
                    break;
                case "LEAVE":
                    Console_BlackJack.finalGoodbye();
                    flag = false;
                    break;
                default:
                    Console_BlackJack.inputError();
                    this.chooseGame();
                    break;
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getCasinoName() {
        return this.casinoName;
    }
}

