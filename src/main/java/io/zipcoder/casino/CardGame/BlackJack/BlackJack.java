package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.CardGame;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.CardGame.Face;
import io.zipcoder.casino.Casino_test;
import io.zipcoder.casino.Interfaces.Gamble;
import io.zipcoder.casino.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CardGame implements Gamble {

    private BlackJackPlayer blackJackPlayer;
    // Player player;
    private ArrayList<BlackJackPlayer> blackJackPlayers = new ArrayList<>();
    private final int minBet = 50;
    private Deck deck = new Deck();
    private boolean justDealt = false;
    private BlackJackPlayer dealer = new BlackJackPlayer(new Dealer());
    private BlackJackPlayer thePlayer;

    public BlackJack() {}

    public BlackJack(Player player) {
        this.blackJackPlayer = new BlackJackPlayer(player);
        blackJackPlayers.add(dealer);
        this.blackJackPlayers.add(blackJackPlayer);
        this.thePlayer = blackJackPlayers.get(1);
        deck.shuffle();
    }

    public void hit(BlackJackPlayer player) {
        setJustDealt(false);
        Card card = deck.draw();
        player.addToHand(card);
        countPlayerHand(player);

        Console_BlackJack.hitCard(player, card);
    }

    public void split(BlackJackPlayer player) {

        Card movingCard = player.getPlayerHand().get(1);

        if (justDealt && player.getPlayerHand().get(0).getFace() == player.getPlayerHand().get(1).getFace()) {
            ArrayList<Card> newHand = player.createNewHand();
            newHand.add(movingCard);
            player.getPlayerHand().remove(movingCard);
        }

        setJustDealt(false);
    }

    public void doubleDown(BlackJackPlayer blackJackPlayer) {
        if (getJustDealt()) {
            blackJackPlayer.addToBetPot(blackJackPlayer.getInitialBet());
        }
        setJustDealt(false);
    }

    public void stand() {
        setJustDealt(false);
    }

    public int calculate_AceIsOne(BlackJackPlayer player) {
        ArrayList<Card> playerHand = player.getPlayerHand();
        int aceIsOne = 0;

        for (int i = 0; i < player.getPlayerHand().size(); i++) {
            if (playerHand.get(i).getFace() == Face.ACE) {
                aceIsOne += playerHand.get(i).getFace().getPrimaryValue();
            } else {
                aceIsOne += playerHand.get(i).getFace().getSecondaryValue();
            }
        }

        return aceIsOne;
    }

    public int calculate_Standard(BlackJackPlayer player) {
        ArrayList<Card> playerHand = player.getPlayerHand();
        int aceIsEleven = 0;

        for (int i = 0; i < player.getPlayerHand().size(); i++) {
            aceIsEleven += playerHand.get(i).getFace().getSecondaryValue();
        }

        return aceIsEleven;
    }

    // REFACTOR THIS!!!!
    public ArrayList<Integer> countPlayerHand(BlackJackPlayer player) {
        ArrayList<Integer> handSum = new ArrayList<>();
        Integer aceIsOne;
        Integer aceIsEleven;
        Integer noAce;

        if (player.hasAce() && calculate_Standard(player) > 21) {
            player.setHandValue(calculate_AceIsOne(player));
            aceIsOne = player.getHandValue();
            handSum.add(aceIsOne);

        } else if (player.hasAce() && calculate_Standard(player) < 21) {
            player.setHandValue(calculate_AceIsOne(player));
            aceIsOne = player.getHandValue();
            handSum.add(aceIsOne);

            player.setHandValue(calculate_Standard(player));
            aceIsEleven = player.getHandValue();
            handSum.add(aceIsEleven);

        } else {
            player.setHandValue(calculate_Standard(player));
            noAce = player.getHandValue();
            handSum.add(noAce);
        }

        return handSum;
    }

    public void deal() {

        for (int i = 0; i < 2; i++) {
            for (BlackJackPlayer player : this.blackJackPlayers) {
                Card card = deck.draw();
                player.addToHand(card);
                countPlayerHand(player);
            }
        }

        setJustDealt(true);
    }

    public BlackJackPlayer getPlayer(int index) {
        return blackJackPlayers.get(index);
    }

    public BlackJackPlayer getDealer() {
        return this.dealer;
    }

    public int getMinBet() {
        return this.minBet;
    }

    public void start() {
//        BlackJackGameplay gamePlay = new BlackJackGameplay();
//        gamePlay.start(player);
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

    public ArrayList<BlackJackPlayer> getBlackJackPlayers() {
        return blackJackPlayers;
    }

    public int betAmount(int amount, BlackJackPlayer blackJackPlayer) {
        blackJackPlayer.addToBetPot(amount);
        return amount;
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

    public BlackJackPlayer getThePlayer() {
        return thePlayer;
    }
}
