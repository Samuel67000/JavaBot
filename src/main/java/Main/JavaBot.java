package Main;

import Observateur.Listener;
import Controller.JDAManager;
import Controller.Configuration;
import net.dv8tion.jda.api.JDA;

import java.io.IOException;

public class JavaBot {
    public static final Configuration CONFIG;
    static {
        Configuration configuration = null;
        try{
            configuration = new Configuration("config.json");

        }catch (IOException e) {
            e.printStackTrace();
        }
        CONFIG = configuration;
    }
    public static void main(String[] args) {
        if(CONFIG == null) {
            System.err.println("Config not found");
            return;
        }
        JDA jda = JDAManager.getJda();
        jda.addEventListener(new Listener());
        CONFIG.save();
    }
}
