package io.zipcoder.casino;

public class Solitaire extends CardGame {

    private Player player;
    private Tableau tableau;
    private Foundation foundation;
    private java.util.Stack wastepile;


    public Solitaire(Player player) {
        this.player = player;
    }

    public void moveable() {
    }

    public void receivable() {
    }

    public Card draw() {
        return null;
    }

    public void flip() {
        // shoulder technically be card.setCovered(false); or something like that
        boolean covered = false;
    }

}