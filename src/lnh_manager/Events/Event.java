
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
public abstract class Event {
    String timeEvent;
    Player playerEvent;

    public Event(String timeEvent, Player playerEvent) {
        this.timeEvent = timeEvent;
        this.playerEvent = playerEvent;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }

    public Player getPlayerEvent() {
        return playerEvent;
    }

    public void setPlayerEvent(Player playerEvent) {
        this.playerEvent = playerEvent;
    }

    @Override
    public String toString() {
        return "Event{" + "timeEvent=" + timeEvent + ", playerEvent=" + playerEvent + '}';
    }
    
}
