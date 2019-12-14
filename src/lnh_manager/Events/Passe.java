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

    public Passe(String timeEvent, Player playerEvent) {
        super(timeEvent, playerEvent);
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
        s += " : Passe décisive de ";
        s += this.playerEvent.getNom();
        
        return s;
    }
}
