package com.codecool.player;

import com.codecool.card.StatsType;
import com.codecool.utilities.UI;

public class Person extends Player{

    private UI userInterface;

    public Person(String name){
        super(name);
        userInterface = new UI();
    }
    
    public StatsType getStatsType(String cardImage) {
        return userInterface.statisticsSelection(cardImage, this.getName());
    }

}