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
    int nbJaune, nbRouge;

    public Player(String Nom, String id) {
        this.Nom = Nom;
        this.id = id;
        nbJaune = 0;
        nbRouge = 0;
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

    public int getNbJaune() {
        return nbJaune;
    }

    public void setNbJaune(int nbJaune) {
        this.nbJaune = nbJaune;
    }

    public int getNbRouge() {
        return nbRouge;
    }

    public void setNbRouge(int nbRouge) {
        this.nbRouge = nbRouge;
    }
    

}
