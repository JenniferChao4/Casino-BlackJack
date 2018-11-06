package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.CardGame;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.Interfaces.Gamble;
import io.zipcoder.casino.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CardGame implements Gamble {

    private ArrayList<BlackJackPlayer> blackJackPlayers;
    private int minBet;
    private ArrayList<Card> wastepile;
    private ArrayList<Card> dealerHand;
    private Deck deck;
    private boolean justDealt;
    private int numOfTurns;

    public BlackJack(Player player) {
        BlackJackPlayer blackJackPlayer = new BlackJackPlayer(player);
        this.blackJackPlayers = new ArrayList<BlackJackPlayer>();
        this.blackJackPlayers.add(blackJackPlayer);
        this.minBet = 100;
        this.wastepile = new ArrayList<Card>();
        this.dealerHand = new ArrayList<Card>();
        this.deck = new Deck();
        this.justDealt = false;
        this.numOfTurns = 0;
    }

    // basically draw
    public void hit(BlackJackPlayer blackJackPlayer) {
        setJustDealt(false);
        Card playerCard1 = deck.draw();
        blackJackPlayer.addToHand(playerCard1);
        System.out.println(blackJackPlayer.getPlayerHand().toString());
    }

    public void flip() {
        // shoulder technically be card.setCovered(false); or something like that
        boolean covered = false;
    }

    public void split() {
        setJustDealt(false);
        // must be the same card value
    }

    public void doubleDown(BlackJackPlayer blackJackPlayer) {
        if (getJustDealt() == true) {
            blackJackPlayer.addToBetPot(blackJackPlayer.getInitialBet());
        }

        setJustDealt(false);
        // must be right after deal, and you can only get one more card
    }

    public void stand() {
        endTurn();
        // (end turn?) what does "take a turn" actually initiate
    }

    public int countHand(BlackJackPlayer blackJackPlayer) {
        int handSum = 0;

        for (int i = 0; i < blackJackPlayer.getPlayerHand().size(); i++) {
            handSum += (blackJackPlayer.getPlayerHand().get(i).getFace().getSecondaryValue());
        }
        return handSum;
    }

    public void deal(BlackJackPlayer blackJackPlayer) {
        // BlackJackPlayer player1 = blackJackPlayers.get(playerIndex);
        // blackJackPlayers.add(blackJackPlayer);
        // should we move this to start instead? we would also need to deal for each player if multiple
        deck.shuffle();
        Card playerCard1 = deck.draw();
        blackJackPlayer.addToHand(playerCard1);

        Card dealerCard1 = deck.draw();
        dealerHand.add(dealerCard1);
        System.out.println("Mystery Card");

        Card playerCard2 = deck.draw();
        blackJackPlayer.addToHand(playerCard2);

        Card dealerCard2 = deck.draw();
        dealerHand.add(dealerCard2);
        System.out.println(dealerCard2);

        setJustDealt(true);
    }

    public BlackJackPlayer getPlayer(int index) {
        return blackJackPlayers.get(index);
    }

    public void start() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter an initial bet: ");
        int initialBet = reader.nextInt();

        start(initialBet);
    }

    public void start(int initialBet) {
        // upon starting a new game, every player places a bet? make a loop???
        BlackJackPlayer blackJackPlayer = blackJackPlayers.get(0);
        if (initialBet < minBet) {
            System.out.println("TOo low");
        } else {
            blackJackPlayer.setInitialBet(betAmount(initialBet, blackJackPlayers.get(0)));
        }
    }

    public void end() {

    }

    public void takeATurn() {

    }

    // add to game interface?
    public void endTurn() {
    }

    public void addPlayer(Player player) {
        BlackJackPlayer blackJackPlayer = new BlackJackPlayer(player);
        this.blackJackPlayers.add(blackJackPlayer);
    }

    public void removePlayer(Player player) {
        for (BlackJackPlayer blackJackPlayer : blackJackPlayers) {
            if (blackJackPlayer.getPlayer() == player) {
                this.blackJackPlayers.remove(blackJackPlayer);
                break;
            }
        }
    }

    public int betAmount(int amount, BlackJackPlayer blackJackPlayer) {
        blackJackPlayer.addToBetPot(amount);
        return betAmount(amount, blackJackPlayer.getPlayer());
    }

    public int betAmount(int amount, Player player) {
        return amount;
    }

    public void distributePot(int amount, Player player) {

    }

    public boolean getJustDealt() {
        return this.justDealt;
    }

    public void setJustDealt(boolean justDealt) {
        this.justDealt = justDealt;
    }
}
