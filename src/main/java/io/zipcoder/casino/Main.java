package io.zipcoder.casino;

public class Main {
    public static void main(String[] args) {
        String playerName = InputOutput.getStringInput("\n\nWelcome to Thunder Theta Casino! \n\nWhat is your name?");
        Player player = new Player(playerName);
        Casino casino = new Casino(player);
        casino.chooseGame();
    }
}
