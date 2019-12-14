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
public class Tir extends Event {
    int outcome;  // 0 But 1 Arrêt 2 Poteau 3 Tir raté
    int position; //0 : aile gauche, 1 : arrière gauche, 2 : demi-centre, 3 : arrière droite, 4 : aile droite, 5 : pivot, 6 : jet de 7m
    int cadrage; 

    public Tir(int outcome, int position, int cadrage, String timeEvent, Player playerEvent) {
        super(timeEvent, playerEvent);
        this.outcome = outcome;
        this.position = position;
        this.cadrage = cadrage;
    }



    public int getOutcome() {
        return outcome;
    }

    public int getPosition() {
        return position;
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
        
        //TODO FAIRE UN SWITCH POUR REMPLIR LE TEXTE SELON LA POSITION
        s += " depuis la position ";
        s += position;
        return s;
    }   
    
    
    
}
