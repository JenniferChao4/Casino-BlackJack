package io.zipcoder.casino;

// BlackJack
// Solitaire
// Craps
// Roulette?
// Slot Machine?

// Jackpot option? Low chance but player wins $1mil
//
//import io.zipcoder.casino.CardGame.BlackJack.BlackJack;
//import io.zipcoder.casino.DiceGame.Craps.Craps;
//import io.zipcoder.casino.CardGame.Solitaire.Solitaire;
//import io.zipcoder.casino.Interfaces.Game;
//
//public class Casino {
//
//    private int money;
//    private String casinoName;
//    private Game game;
//    private Player player;
//    private String currentGame;
//
//    public void selectGame(int gameNum) {
//
//        switch (gameNum) {
//            case 1:
//                Game blackJack = new BlackJack(player);
//                break;
//            case 2:
//                Game solitaire = new Solitaire(player);
//                break;
//            case 3:
//                Game craps = new Craps(player);
//                break;
//            case 4:
//                leaveCasino();
//                break;
//            default:
//                System.out.println("Input unknown, please pick again.");
//                break;
//        }
//    }
//
//    public void leaveCasino() {
//    }
//
//}






import io.zipcoder.casino.CardGame.BlackJack.BlackJack;
import io.zipcoder.casino.CardGame.BlackJack.BlackJackGameplay;
import io.zipcoder.casino.CardGame.BlackJack.Console_BlackJack;
import io.zipcoder.casino.CardGame.Solitaire.Solitaire;
import io.zipcoder.casino.DiceGame.Craps.Craps;

public final class Casino {

    private final static Casino instance = new Casino();

    private Player player;
    private BlackJack blackJack;
    private BlackJackGameplay gamePlay;

    private String casinoName;

    public Casino(){}

    public Casino(Player player) {
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
                    Solitaire s = new Solitaire(new Player("Bill"));
                    s.start();
                    flag = false;
                    break;
                case "BLACKJACK":
                    Console_BlackJack.blackJackWelcome(player);
                    gamePlay.start(player);
                    flag = false;
                    break;
                case "CRAPS":
                    Craps craps = new Craps(player);
                    craps.gamePlay();
                    flag = false;
                    break;
                case "LEAVE":
                    Console_BlackJack.finalGoodbye(player);
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

    public static Casino getInstance() {return instance; }

}


