/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager.Players;

import java.util.ArrayList;

/**
 *
 * @author Maxime
 */
public class Equipe {  
    String trainer;
    ArrayList <Player> teamIn = new ArrayList<>();
    ArrayList <Player> teamOut = new ArrayList<>();

    public Equipe(String trainer) {
        this.trainer = trainer;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
    public void addTeamIn (Player p){
        teamIn.add(p);
    }
    public void addTeamOut (Player p){
        teamOut.add(p);
    }
}
