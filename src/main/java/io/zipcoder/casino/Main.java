package io.zipcoder.casino;

public class Main {
    public static void main(String[] args) {
        String playerName = Console.getStringInput("\n\nWelcome to the Casino Lobby! What is your name?");
        Player player = new Player(playerName);
        Casino casino = new Casino(player);
        casino.chooseGame();
    }
}
