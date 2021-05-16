package com.codecool.card;

public class Card implements Comparable {
    private String name;
    private int health;
    private int strength;
    private int magic;

    public Card(String name, int health, int strength, int magic){
      this.name = name;
      this.health = health;
      this.strength = strength;
      this.magic = magic;
    }
    
    public String getCardImage() {
        return getCardImage(1);
    }
      
    public String getCardImage(int amountOfCards) {
        int cardWidth = 25;
        String cardImage = String.format(" %s%s\n %s%s\\\n%s%-25s|\n%s%s|\n%s%-25s|\n%s%-25s|\n%s%-25s|\n %s%s/\n",
                " ".repeat(amountOfCards), "_".repeat(cardWidth-2),
                "/".repeat(amountOfCards), " ".repeat(cardWidth-2),
                "|".repeat(amountOfCards), this.name,
                "|".repeat(amountOfCards), "-".repeat(cardWidth),
                "|".repeat(amountOfCards), String.format("health: %d", this.health),
                "|".repeat(amountOfCards), String.format("strenght: %d", this.strength),
                "|".repeat(amountOfCards), String.format("magic: %d", this.magic),
                "\\".repeat(amountOfCards), "_".repeat(cardWidth-2));
        return cardImage;

    }


	public int getStat(StatsType statsType) {
		switch (statsType.ordinal()) {
      case 0:
        return this.health;
      case 1:
        return this.strength;
      case 2:
        return this.magic;
    }
    return 0;
	}

    @Override
	public ComparableType compareCard(Card player2Card, StatsType statsType) {
    if (this.getStat(statsType) > player2Card.getStat(statsType)) {
      return ComparableType.BETTER;
    }
    if(this.getStat(statsType) == player2Card.getStat(statsType)) {
      return ComparableType.SAME;
    }
    return ComparableType.WORSER;
	}


	
        
}
