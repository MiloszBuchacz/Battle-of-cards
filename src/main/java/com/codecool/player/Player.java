package com.codecool.player;

import com.codecool.card.Card;
import com.codecool.card.StatsType;

import java.util.ArrayList;
import java.util.List;

public abstract class Player{
    private List<Card> hand;
    private String name;

    public Player(String name){
        hand = new ArrayList<>();
        this.name = name;
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getTopCard() throws IndexOutOfBoundsException {
        return hand.get(0);
    }
    
    public Card throwCard(){
        Card card = hand.get(0);
        hand.remove(0);
        return card;
    }

    public int getAmountOfCards(){
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public abstract StatsType getStatsType(String cardImage);


}


