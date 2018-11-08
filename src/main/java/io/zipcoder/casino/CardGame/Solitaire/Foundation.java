package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.CardGame.Face;
import io.zipcoder.casino.CardGame.Suit;

import java.util.Stack;

public class Foundation {

    public static Stack<Card> clubStack = new Stack<>();
    public static Stack<Card> diamondStack = new Stack<>();
    public static Stack<Card> heartStack = new Stack<>();
    public static Stack<Card> spadeStack = new Stack<>();

    public Foundation() {

    }

    Tableau tab = new Tableau();
    Deck deck = new Deck();
    Card card;


    public static void whichSuit(Stack<Card> tempStackCard) {

        if (tempStackCard.peek().getSuit() == Suit.CLUBS) {
            //Checks if the Clubs Foundation is Empty
            if (clubStack.empty() && tempStackCard.peek().getFace() == Face.ACE) {
                clubStack.push(tempStackCard.peek());
            }
            //Checks if the Temp Card is 1 more than what
            // is on the Foundation

            if (tempStackCard.peek().getFace().getPrimaryValue() == clubStack.peek().getFace().getPrimaryValue() + 1) {
                clubStack.push(tempStackCard.peek());

            }
        }

        if (tempStackCard.peek().getSuit() == Suit.DIAMONDS) {

            //Checks if the Diamonds Foundation is Empty
            if (diamondStack.empty() && tempStackCard.peek().getFace() == Face.ACE) {
                diamondStack.push(tempStackCard.peek());
            }
            //Checks if the Temp Card is 1 more than what
            // is on the Foundation
            if (tempStackCard.peek().getFace().getPrimaryValue() == diamondStack.peek().getFace().getPrimaryValue() + 1) {
                diamondStack.push(tempStackCard.peek());
            }
        }

        if (tempStackCard.peek().getSuit() == Suit.HEARTS) {

            //Checks if the Heart Foundation is Empty
            if (heartStack.empty() && tempStackCard.peek().getFace() == Face.ACE) {
                heartStack.push(tempStackCard.peek());
            }
            //Checks if the Temp Card is 1 more than what
            // is on the Foundation
            if (tempStackCard.peek().getFace().getPrimaryValue() == heartStack.peek().getFace().getPrimaryValue() + 1) {
                heartStack.push(tempStackCard.peek());
            }
        }

        if (tempStackCard.peek().getSuit() == Suit.SPADES) {


            if (spadeStack.empty() && tempStackCard.peek().getFace() == Face.ACE) {
                spadeStack.push(tempStackCard.peek());
            }
            if (tempStackCard.peek().getFace().getPrimaryValue() == spadeStack.peek().getFace().getPrimaryValue() + 1) {
                spadeStack.push(tempStackCard.peek());
            }
        }
    }

    public static Boolean allFoundsFull() {
        if (clubStack.size() == 13 && spadeStack.size() == 13
                && heartStack.size() == 13  && diamondStack.size() == 13)
            return true;
        else return false;
    }
}
