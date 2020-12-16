package Modele.Role;

import Modele.RoleJeu;

public class Voyante extends RoleJeu {
    public Voyante() {

        super("Voyante");
    }

    @Override
    public void pouvoir() {
        //Chaque nuit peut observer le role de qqln
    }
}
