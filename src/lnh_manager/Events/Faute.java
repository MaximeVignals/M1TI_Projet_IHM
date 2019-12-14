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
    int type; //0 Faute; 1 Carton Jaune; 2 Carton Rouge; 3 Carton Bleu; 4 2 Minutes

    public Faute(String timeEvent, Player playerEvent,int type) {
        super(timeEvent, playerEvent);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public String getTimeEvent() {
        return timeEvent;
    }

    @Override
    public Player getPlayerEvent() {
        return playerEvent;
    }
    
    @Override
    public String toString(){
        String s = timeEvent;
        
        switch(type){
            case 0:
                s+= " : Faute de ";

                break;
            case 1:
                s+= " : Carton Jaune pour ";
                break;
            case 2:
                s+= " : Carton Rouge pour ";
                break;
            case 3:
                s+= " : Carton Bleu pour ";
                break;
            case 4:
                s+= " : Deux minutes pour ";
                break;
        }
        s+= this.playerEvent.getNom();
        return s;
    }
   
}
