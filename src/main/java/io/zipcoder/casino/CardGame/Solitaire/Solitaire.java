package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.CardGame;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

import static io.zipcoder.casino.CardGame.Card.toCard;
import static io.zipcoder.casino.CardGame.Solitaire.Foundation.allFoundsFull;
import static io.zipcoder.casino.CardGame.Solitaire.Foundation.cheatFoundations;
import static io.zipcoder.casino.CardGame.Solitaire.Foundation.whichSuit;

public class Solitaire extends CardGame {

    public static void main(String[] args){
        Solitaire s = new Solitaire(new Player("Bill"));
        s.start();
    }
    Console console = new Console(System.in, System.out);

    Scanner in = new Scanner(System.in);
    //clean up.

    //create setting for 3 card draw. which will affect the draw method.
    private Player player;
    public Tableau tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    public Stack<Card> wastePile;
    public Tableau[] arrayTabs;
    public static Stack<Card> tempStack = new Stack<>();
    public static Tableau lastTab = null;

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
    }

    public static Deck solitaireDeck = new Deck();


    public void start(){
        System.out.println("Welcome");
        resetDeck();
        wastePile.removeAllElements();
        tempStack.removeAllElements();
        shuffle();
        deal();
        print();
        takeATurn();
    }

    public void shuffle(){
        solitaireDeck.shuffle();
    }

    public void deal() {
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
            default:
                System.out.println("Not a valid entry. Try again or press \'E\'");
                dropToTab(in.next().charAt(0));
        }
    }

    public void pull(String cardCode){
        char f = cardCode.toUpperCase().charAt(0);
        char s = cardCode.toUpperCase().charAt(1);
        Card c = toCard(f,s);
        findTab(c).pull(c);
    }

    public Tableau findTab(Card c){
        for (Tableau tab : arrayTabs)
            if (tab.stack.contains(c)) {
                lastTab = tab;
                return tab;
            }
        return null;
    }

    public void peekWaste(){
        if(wastePile.size()>0) System.out.println(wastePile.peek());
    }

    //you've got a temp stack. so when you pull a card, show it. if it doesn't go, put it back.
    //fix empty stack exceptions
    //draw shouldn't reprint every time. only print top of wastePile
    //build console class for solitaire printouts
    public void takeATurn() {
            console.println("Ready? Let's Play");
            while (!allFoundsFull() || !gameOver()) {
                String command = console.getInputString("What now?");
                switch (String.valueOf(command)) {
                    case "DRAW":
                        drawCard();
                        console.println("\nYou just drew " + wastePile.peek().toString2());
                        break;
                    case "P":
                        //try, catch, continue
                        try {
                            pickUp();
                            console.println("\nYou just picked up " + tempStack.peek().toString2());
                            dropToTab(console.getDropTab().charAt(0));
                            print();
                            peekWaste();
                            break;
                        } catch (EmptyStackException e) {
                            console.println("\nCan't pull from an empty draw pile");
                            break;
                        }
                    case "QUIT":
                        gameOver();
                    case "FOO":
                        cheatFoundations();
                    default:
                        pull(String.valueOf(command));
                        console.println("\nYou just pulled" + tempStack.peek().toString2());
                        dropToTab(console.getDropTab().charAt(0));
                        print();
                        break;
                }
            }
    }

    public void addPlayer(Player player) {

    }

    public void removePlayer(Player player) {

    }

    public Card draw() {
        return solitaireDeck.draw();
    }

    public void resetDeck(){
        solitaireDeck = new Deck();
    }

    public void print(){
        int i = 1;
        for (Tableau tab : arrayTabs) {
            System.out.println("\nCOLUMN " + i); i++;
            tab.stack.forEach(e -> System.out.println(e.toString2() + " "));
        }
    }

    public Boolean gameOver(){
        if(console.getInputString("Are you sure you want to quit?\nEnter Y to quit").equals("Y")) return true;
        return false;
    }

    public void end() {
        System.out.println("Congratulations!");
        System.out.println("Enter \'N\' to play again or press \'Q\' to quit");
        String command = in.next().toUpperCase();
        while (!command.equals("Q") || !command.equals("N")){
            if (command.equals("Q"));
            else if (command.equals("N")) start();
            else System.out.println("Invalid. Enter \'N\' to play again or press \'Q\' to quit");
        }
    }
}