package Controller;

import Main.JavaBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.ShardManager;
import javax.security.auth.login.LoginException;

public class JDAManager {
    private final static String token = JavaBot.CONFIG.getString("token", "Insert your token here");
    private static JDA jda = JDAManager.buildJDA();
    private static ShardManager shardManager = JDAManager.buildShard();

    public static JDA buildJDA(){
        try{
            return JDABuilder.createDefault(token).build();
        }catch(LoginException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ShardManager buildShard() {
        return new DefaultShardManager(token);
    }

    public static JDA getJda(){
        return JDAManager.jda;
    }

    public static ShardManager getShardManager(){
        return JDAManager.shardManager;
    }

}
