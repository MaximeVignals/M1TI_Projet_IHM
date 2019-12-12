/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager.Events;

import lnh_manager.Player;

/**
 *
 * @author Maxime
 */
public class Tir extends Event {
    int outcome;  // 0 But 1 Arrêt 2 Poteau 3 Tir raté
    int position; // 

    public Tir(String temps, Player p, int outcome, int position) {
        this.timeEvent = temps;
        this.playerEvent = p;
        this.outcome = outcome;
        this.position = position;
    }

    public int getOutcome() {
        return outcome;
    }

    public int getPosition() {
        return position;
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
        s += " : ";
        switch(outcome){
            case 0:
                s += "But de ";
                break;
            case 1:
                s += "Tir arrêté par le gardien de ";
                break;
            case 2:
                s += "Poteau de ";
                break;
            case 3:
                s += "Tir non cadré de ";
                break;
        } 
        s += this.playerEvent.getNom();
        return s;
    }   
    
    
    
}
