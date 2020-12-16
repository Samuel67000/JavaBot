package Modele;


import net.dv8tion.jda.api.entities.Member;

public class Joueur {
    private Member member;
    private String name;
    private RoleJeu role;
    public Joueur(Member member, String name){
        this.member = member;
        this.name = name;
    }
}
