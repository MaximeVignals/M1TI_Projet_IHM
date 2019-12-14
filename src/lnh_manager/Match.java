/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager;

import java.util.ArrayList;
import lnh_manager.Events.Event;
import lnh_manager.Players.Equipe;
import lnh_manager.Players.Player;

/**
 *
 * @author Maxime
 */
public class Match {
    ArrayList <Event> events = new ArrayList<>();
    Equipe homeTeam;
    Equipe visitorTeam;
    int butHome;
    int butVisitor;

    public Match(Equipe homeTeam, Equipe visitorTeam) {
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        butHome = 0;
        butVisitor = 0;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Equipe getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Equipe homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Equipe getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Equipe visitorTeam) {
        this.visitorTeam = visitorTeam;
    }
    
    
}
