package Modele;

import java.util.ArrayList;

public class Partie {
    private ArrayList<Joueur> listJoueur;
    public Partie(ArrayList<Joueur> joueurs){
        listJoueur = joueurs;
    }

    public ArrayList<Joueur> getJoueur(){
        return this.listJoueur;
    }
}
