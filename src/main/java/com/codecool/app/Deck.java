package com.codecool.app;

import java.util.Collections;
import java.util.List;

import com.codecool.card.Card;
import com.codecool.utilities.UI;
import com.codecool.parser.CardParser;
import com.codecool.player.*;

public class Deck {
    private List<Card> deck;

    public Deck() {
        getCardfromXML();
    }

    public void addCardToDeck(Card card) {
        deck.add(card);
    }

    private void getCardfromXML() {
        CardParser cardsParser = new CardParser();
        deck = cardsParser.parse();
    }

    public void dealCards(List<Player> players) {
        int amount = new UI().choseAmountToDeal(deck.size(), players.size());
        for (int i = 0; i < amount; i++) {
            for (Player player : players) {
                player.addCard(deck.get(0));
                deck.remove(0);
            }
        }
    }

    public void shuffleCards() {
        Collections.shuffle(deck);

    }

}