package Modele;

public abstract class RoleJeu {
    private String name;
    private RoleJeu role;
    public RoleJeu(){
        this.name = null;
        this.role = null;
    }
    protected RoleJeu(String s){
        this.name = s;
        this.role = null;
    }

    public void addRole(RoleJeu role){
        this.role = role;
        this.name = role.name;
    }

    public String getName(){
        return this.name;
    }

    public abstract void pouvoir();



}
