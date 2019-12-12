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
public class Faute extends Event {
    int type; //0 Faute 1 Carton Jaune 2 Carton Rouge 3 Carton Bleu 4 2 Minutes

    public Faute(String temps, Player p, int type) {
        this.type = type;
        this.playerEvent = p;
        this.timeEvent = temps;
    }

    public int getType() {
        return type;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public Player getPlayerEvent() {
        return playerEvent;
    }
    
    @Override
    public String toString(){
        String s ="";
        
        s+= this.timeEvent;
        s+= " : Faute de ";
        s+= this.playerEvent.getNom();
        
        return s;
    }
   
}
