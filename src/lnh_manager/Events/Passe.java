/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager.Events;

import lnh_manager.Players.Player;

/**
 *
 * @author Maxime
 */
public class Passe extends Event{
    Player joueurRecept;

    public Passe(String temps, Player p, Player joueurRecept) {
        this.joueurRecept = joueurRecept;
        this.playerEvent = p;
        this.timeEvent = temps;
    }

    public Player getJoueurRecept() {
        return joueurRecept;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public Player getPlayerEvent() {
        return playerEvent;
    }
    
    @Override
    public String toString(){
        String s = "";
        s += this.timeEvent;
        s += " : Passe de ";
        s += this.playerEvent.getNom();
        s += " à ";
        s += this.joueurRecept.getNom();
        
        return s;
    }
}
