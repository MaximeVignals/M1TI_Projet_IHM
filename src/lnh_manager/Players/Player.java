/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh_manager.Players;

/**
 *
 * @author Maxime Vignals
 */
public class Player {
    String Nom;
    String id;
    String nbJaune, nbRouge;
    int tempsDeuxMin;

    public Player(String Nom, String id) {
        this.Nom = Nom;
        this.id = id;
        nbJaune = "0";
        nbRouge = "0";
    }
    public Player(){
        this.Nom = "";
        this.id = "-1";
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNbJaune() {
        return nbJaune;
    }

    public void setNbJaune(String nbJaune) {
        this.nbJaune = nbJaune;
    }

    public String getNbRouge() {
        return nbRouge;
    }

    public void setNbRouge(String nbRouge) {
        this.nbRouge = nbRouge;
    }
    

}
