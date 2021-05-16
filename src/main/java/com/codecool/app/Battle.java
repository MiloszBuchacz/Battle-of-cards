package com.codecool.app;

import java.util.ArrayList;
import java.util.List;

import com.codecool.card.Card;
import com.codecool.card.StatsType;
import com.codecool.player.*;
import com.codecool.utilities.UI;

public class Battle {

    private UI userInterface;

    public Battle() {
        userInterface = new UI();
    }

    public void makeTurn(List<Player> players) {
        List<Player> fighters = new ArrayList<>();
        fighters.addAll(players);
        StatsType statsType = players.get(0).getStatsType(players.get(0).getTopCard()
                .getCardImage());
        List<Card> cardsInTurn = new ArrayList<>();
        List<Card> clipBoard = new ArrayList<>();
        do {
            if (nobodyHasCards(fighters)) {
                userInterface.nobodyWinsDraw(fighters);
                break;
            }
            printEachPlayerHand(fighters, cardsInTurn);
            List<Card> bestCards = getBestCards(cardsInTurn, statsType);
            String winnerName = getWinnerName(fighters, bestCards);
            userInterface.battleScreen(cardsInTurn, fighters, winnerName);
            getRidOfLosers(fighters, clipBoard, bestCards);
            cardsInTurn.removeAll(cardsInTurn);
        }while (fighters.size() != 1);
        dealCardFromBattle(fighters, clipBoard);
    }

    private void dealCardFromBattle(List<Player> fighters, List<Card> clipBoard) {
        if (fighters.size() == 1){
            for (Card card : clipBoard) {
                fighters.get(0).addCard(card);
            }
        } else {
            while (clipBoard.size() != 0) {
                try {
                    for (Player fighter : fighters) {
                        fighter.addCard(clipBoard.get(0));
                        clipBoard.remove(0);
                    }
                } catch (IndexOutOfBoundsException e) {}
            }
        }
    }

    private void getRidOfLosers(List<Player> fighters, List<Card> clipBoard, List<Card> bestCards) {
        for (int i = 0; i<fighters.size(); i++) {
            clipBoard.add(fighters.get(i).throwCard());
            if (!(bestCards.contains(clipBoard.get(clipBoard.size() - 1)))) {
                fighters.remove(i);
                i--;
            }
        }
    }

    private String getWinnerName(List<Player> fighters, List<Card> bestCards) {
        String winnerName = "DRAW";
        if (bestCards.size() == 1) {
            for (Player fighter : fighters) {
                if (fighter.getTopCard() == bestCards.get(0)) {
                    winnerName = fighter.getName();
                }
            }
        }
        return winnerName;
    }

    private void printEachPlayerHand(List<Player> fighters, List<Card> cardsInTurn) {
        for (int i = 0; i < fighters.size(); i++) {
            try{
                userInterface.printCardsHand(fighters.get(i).getTopCard().getCardImage(fighters.get(i).getAmountOfCards()),
                    fighters.get(i).getName());
                cardsInTurn.add(fighters.get(i).getTopCard());
            } catch (IndexOutOfBoundsException e) {
                userInterface.printCardsHand("You have got no cards!\nYou lose extra round!", fighters.get(i).getName());
                fighters.remove(i);
            }
            userInterface.pressEnterToContinue();
        }
    }

    private List<Card> getBestCards(List<Card> cardsInTurn, StatsType statsType) {
        List<Card> bestCards = new ArrayList<>();
        bestCards.add(cardsInTurn.get(0));
        for (int i = 1; i<cardsInTurn.size(); i++) {
            switch (bestCards.get(0).compareCard(cardsInTurn.get(i), statsType)) {
                case WORSER:
                    bestCards.removeAll(bestCards);
                case SAME:
                    bestCards.add(cardsInTurn.get(i));
                default:
                    break;
            }
        }
        return bestCards;
    }

    private boolean nobodyHasCards(List<Player> players) {
        for (Player player : players) {
            if (player.getAmountOfCards() > 0) {
                return false;
            }
        }
        return true;
    }

}