package Modele.Role;

import Modele.RoleJeu;

public class Chasseur extends RoleJeu {

    public Chasseur() {
        super("Chasseur");
    }

    @Override
    public void pouvoir() {
        //à ça mort peut choisir une dernière victime
    }
}
