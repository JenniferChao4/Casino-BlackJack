package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.CardGame;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.Player;

import java.util.Scanner;
import java.util.Stack;

import static io.zipcoder.casino.CardGame.Card.toCard;
import static io.zipcoder.casino.CardGame.Solitaire.Foundation.allFoundsFull;
import static io.zipcoder.casino.CardGame.Solitaire.Foundation.whichSuit;

public class Solitaire extends CardGame {

    public static void main(String[] args){
        Solitaire s = new Solitaire(new Player("Bill"));
        s.start();
    }

    Scanner in = new Scanner(System.in);
    //clean up.

    //create setting for 3 card draw. which will affect the draw method.
    private Player player;
    public Tableau tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    public Stack<Card> wastePile;
    public Tableau[] arrayTabs;
    public static Stack<Card> tempStack = new Stack<>();

    public Solitaire(Player player) {
        this.player = player;
        wastePile = new Stack<>();
        tab1 = new Tableau();
        tab2 = new Tableau();
        tab3 = new Tableau();
        tab4 = new Tableau();
        tab5 = new Tableau();
        tab6 = new Tableau();
        tab7 = new Tableau();
        arrayTabs = new Tableau[]{tab1, tab2, tab3, tab4, tab5, tab6, tab7};
        start();
    }

    public static Deck solitaireDeck = new Deck();

    public void deal() {
        solitaireDeck.shuffle();
        for (int i = 0; i < arrayTabs.length; i++) {
            for (int j = 0; j < arrayTabs.length; j++) {
                if (j >= i) arrayTabs[j].add(draw());
                if (j != i) arrayTabs[j].stack.peek().setCovered(true);
            }
        }
        for(Tableau tab : arrayTabs) tab.stack.peek().setCovered(false);
    }

    public void drawCard(){
        wastePile.push(solitaireDeck.draw());
    }

    public Stack<Card> pickUp(){
        tempStack.push(wastePile.pop());
        return tempStack;
    }

    public void dropToTab(char key){
        switch (key){
            case '1':
                tab1.place();
                break;
            case '2':
                tab2.place();
                break;
            case '3':
                tab3.place();
                break;
            case '4':
                tab4.place();
                break;
            case '5':
                tab5.place();
                break;
            case '6':
                tab6.place();
                break;
            case '7':
                tab7.place();
                break;
            case '8':
                whichSuit(tempStack);
                break;
            case '9':
                whichSuit(tempStack);
                break;
            case '0':
                whichSuit(tempStack);
                break;
            case '-':
                whichSuit(tempStack);
                break;
            case 'E': //develop way to replace original stack on pile. don't change coverage until placed. same with pulled from stack.
                break;
            default:
                System.out.println("Not a valid entry. Try again or press \'E\'");
                dropToTab(in.next().charAt(0));
        }
    }

    public Stack<Card> pull(String cardCode){
        char f = cardCode.charAt(0);
        char s = cardCode.charAt(1);
        Card c = toCard(f,s);
        return findTab(c).pull(c);
    }

    public Tableau findTab(Card c){
        for (Tableau tab : arrayTabs)
            if (tab.stack.contains(c))
                return tab;
        return null;
    }

    public void start(){
        System.out.println("Welcome");
        resetDeck();
        wastePile.removeAllElements();
        deal();
        print();
        takeATurn();
    }

    public void end() {
        System.out.println("Congratulations!");
        System.out.println("Enter \'N\' to play again or press \'Q\' to quit");
        String command = in.next().toUpperCase();
        while (!command.equals("Q") || !command.equals("N")){
            if (command.equals("Q")) end();
            else if (command.equals("N")) start();
            else System.out.println("Invalid. Enter \'N\' to play again or press \'Q\' to quit");
        }
    }

    public String getInput(){
        return in.next();
    }

    public void takeATurn() {
        System.out.println("\nLet's play. Enter \'DRAW\' to draw a card\n");
        while (!getInput().equals("QUIT") || !allFoundsFull()) {
            System.out.println("Great. next?");
            System.out.println("in while - loop");
            if (getInput().equals("DRAW")) {
                drawCard();
                print();
                continue;
            } else if (getInput().equals("P")) {
                pickUp();
                dropToTab(getInput().charAt(0));
                print();
            } else if (getInput().length() == 2) {
                pull(String.valueOf(getInput()));
                dropToTab(getInput().charAt(0));
                print();
            }
        }

        if (allFoundsFull()) end();
    }

    public void addPlayer(Player player) {

    }

    public void removePlayer(Player player) {

    }

    public void moveable() {
    }

    public void receivable() {
    }

    public Card draw() {
        return solitaireDeck.draw();
    }

    public void resetDeck(){
        solitaireDeck = new Deck();
    }

    public void print(){
        int i = 0;
        for (Tableau tab : arrayTabs) {
            System.out.println("tab " + i); i++;
            tab.stack.forEach(e -> System.out.println(e + " " + e.isCovered()));
        }
        if (wastePile.size() > 0) System.out.println("Top of Pile " + wastePile.peek());
    }


}
