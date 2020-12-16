package Controller;

import Modele.Partie;
import Vues.CommandView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.io.File;
import java.security.MessageDigest;
import java.util.concurrent.ExecutionException;

public class ControllerMetier {
    public static boolean config = true;
    public static User proprietaire = null;

    public static void createParti(int nbJoueur, Message msg) {
        CommandView.launch(nbJoueur, msg.getGuild());
    }

    public static void configPartie(Message message) {
        config = false;
        proprietaire = message.getAuthor();
        CommandView.sendConfig(message);
    }

    public static void finishPartie(Message message) {
        config = true;
        proprietaire = null;
        CommandView.finishPartie(message);
    }
}
