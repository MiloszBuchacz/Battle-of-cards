package com.codecool;

import java.util.List;

import com.codecool.app.Deck;
import com.codecool.app.Game;
import com.codecool.parser.CardParser;
import com.codecool.player.*;
import com.codecool.utilities.UI;

public class Main {

    public static void main(String[] args) {
        
        UI display = new UI();
        CardParser cardParser = new CardParser();
        boolean isRunning = true;

        while(isRunning) {
            switch (display.startScreen()) {
                case "start":
                    List<Player> players = display.providePlayers();
                    Deck deck = new Deck();
                    deck.dealCards(players);
                    Game game = new Game(players);
                    game.gameLoop();
                    break;
                case "cards":
                    display.cardScreen();
                    break;
                case "new":
                    cardParser.addCard();
                    break;
                case "delete":
                    cardParser.deleteCard();
                    break;
                case "info":
                    display.infoScreen();
                    break;
                case "quit":
                    isRunning = false;
                    break;
            }
        }  
    }

}