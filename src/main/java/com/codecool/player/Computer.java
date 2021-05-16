package com.codecool.player;

import java.util.Random;

import com.codecool.card.StatsType;
import com.codecool.utilities.UI;

public class Computer extends Player{

    private Random random;
    private UI userInterface;

    public Computer(String name) {
        super(name);
        random = new Random();
        userInterface = new UI();
    }

    public StatsType getStatsType(String cardImage) {
        int amountOfTypes = 3;
        switch (random.nextInt(amountOfTypes)) {
            case 0:
                userInterface.printCardsHand(cardImage, super.getName(), "HEALTH");
                return StatsType.HEALTH;
            case 1:
                userInterface.printCardsHand(cardImage, super.getName(), "STRENGTH");
                return StatsType.STRENGTH;
            default:
                userInterface.printCardsHand(cardImage, super.getName(), "MAGIC");
                return StatsType.MAGIC;
        }
    }
}