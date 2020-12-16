package Modele.Role;

import Modele.RoleJeu;

public class PetiteFille extends RoleJeu {
    public PetiteFille() {
        super("PetiteFille");
    }

    @Override
    public void pouvoir() {
        //peut apercevoir les loups la nuit
    }
}
