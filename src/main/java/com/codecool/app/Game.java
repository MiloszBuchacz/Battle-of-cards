package com.codecool.app;

import java.util.ArrayList;
import java.util.List;
import com.codecool.utilities.UI;
import com.codecool.player.*;

public class Game{
    

    private List<Player> players;
    private List<Player> losers;
    private Battle battle;
    private UI userInterface;

    public Game(List<Player> players) {
        this.players = players;
        losers = new ArrayList<>();
        battle = new Battle();
        userInterface = new UI();
    }

    public void gameLoop() {
        while(nobodyWon()) {
            battle.makeTurn(players);
            Player playerToMove = players.get(0);
            players.remove(playerToMove);
            players.add(playerToMove);
            checkIfSomebodyLose();
        }
        losers.add(0, players.get(0));
        userInterface.endScreen(losers);
    }

    private boolean nobodyWon() {
        if (players.size() <= 1)
            return false;
        return true;
    }

    private void checkIfSomebodyLose() {
        for (int i = 0; i<players.size(); i++) {
            if (players.get(i).getAmountOfCards() == 0) {
                losers.add(0, players.get(i));
                userInterface.informationAboutDefeat(players.get(i).getName());
                players.remove(i);
                i--;
            }
        }
    }

}