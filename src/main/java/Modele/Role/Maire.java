package Modele.Role;

import Modele.RoleJeu;

public class Maire extends RoleJeu {
    public Maire() {

        super("Maire");
    }

    @Override
    public void pouvoir() {
        //A l'issu du vote après le premier jour sa voix compte double
    }
}
