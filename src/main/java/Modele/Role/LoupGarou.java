package Modele.Role;

import Modele.RoleJeu;

public class LoupGarou extends RoleJeu {
    public LoupGarou() {

        super("LoupGarou");
    }

    @Override
    public void pouvoir() {
        // La nuit peuvent ovter pour quelqu'un pour le manger et le tuer
    }
}
