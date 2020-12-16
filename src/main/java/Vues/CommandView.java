package Vues;

import Controller.ControllerMetier;
import Main.JavaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;


public class CommandView {

    public static Guild guild = null;
    public static boolean configPrint = false;

    public static void usage(Message msg,String cmd){
        String usage = JavaBot.CONFIG.getString(cmd, "Usage: Enter usage");
        msg.getGuild().getTextChannelById(msg.getTextChannel().getId()).sendMessage(usage).queue();
    }

    public static void launch(int nbJoueur, Guild guild){

        Category lgPrep = guild.getCategoriesByName("LG Préparation", false).get(0);
        Category lgPartie = guild.getCategoriesByName("LG Partie", false).get(0);

        VoiceChannel from  = lgPrep.getVoiceChannels().get(0);
        VoiceChannel to = lgPartie.getVoiceChannels().get(0);
        VoiceChannel test = guild.getVoiceChannelById(787979298790834218L);
        System.out.println(to.getName());
        System.out.println(from.getName());
        for(Member m:from.getMembers()){
            System.out.println(m.getEffectiveName());
            try{
                guild.moveVoiceMember(m,to).queue();
            }catch (Exception e){
                System.out.println("Je passe par la");
                e.printStackTrace();
            }

        }

    }

    private static void sendFatalError(){
    }


    public static void createParti(Category lg){
        TextChannel village = lg.createTextChannel("Village").complete();
        village.getManager().putPermissionOverride(guild.getPublicRole(),null, Collections.singleton(Permission.VIEW_CHANNEL)).complete();
        lg.createVoiceChannel("Village").complete().getManager().putPermissionOverride(guild.getPublicRole(),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete();
        lg.createTextChannel("Tanière des loups").complete().getManager().putPermissionOverride(guild.getPublicRole(),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete();
        lg.createTextChannel("Lit nuptial").complete().getManager().putPermissionOverride(guild.getPublicRole(),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete();
        lg.createTextChannel("Lieu de culte").complete().getManager().putPermissionOverride(guild.getPublicRole(),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete();
        lg.createTextChannel("Landeau du bébé").complete().getManager().putPermissionOverride(guild.getPublicRole(),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete();
        lg.createTextChannel("Les marécages").complete().getManager().putPermissionOverride(guild.getPublicRole(),null,EnumSet.of(Permission.VIEW_CHANNEL)).complete();
    }

    public static void createRequired(Message message) {
        Guild guild = message.getGuild();
        CommandView.guild = guild;
        List<Category> listcatLG = guild.getCategoriesByName("LG Préparation", false);
        Category lgPrep;
        if(listcatLG.size() == 0){
            lgPrep = guild.createCategory("LG Préparation").complete();
        }
        else
            lgPrep = listcatLG.get(0);

        for (GuildChannel c:listcatLG.get(0).getChannels()) {
            c.delete().queue();
        }

        lgPrep.createTextChannel("Préparation").complete().getManager().putPermissionOverride(guild.getPublicRole(),EnumSet.of(Permission.VIEW_CHANNEL),null);
        lgPrep.createVoiceChannel("Préparation").complete().getManager().putPermissionOverride(guild.getPublicRole(),EnumSet.of(Permission.VIEW_CHANNEL),null);

        List<Category> listLGPartie = guild.getCategoriesByName("LG Partie", false);
        Category catPartie = null;
        if(listLGPartie.size() == 0){
            catPartie = guild.createCategory("LG Partie").complete();

        }
        else{
            catPartie = listLGPartie.get(0);
            for (GuildChannel c:catPartie.getChannels()) {
                c.delete().queue();
            }
        }

       CommandView.createParti(catPartie);

    }

    public static void sendConfig(Message message) {
        TextChannel tc = message.getTextChannel();
        List<Message> list = tc.getHistory().retrievePast(50).complete();
        tc.deleteMessages(list).queue();
        CommandView.sendEmbed(tc,1,"Chasseur");
        CommandView.sendEmbed(tc,1,"Cupidon");
        CommandView.sendEmbed(tc,5,"loupGarou");
        CommandView.sendEmbed(tc,1,"Maire");
        CommandView.sendEmbed(tc,1,"PetiteFille");
        CommandView.sendEmbed(tc,5,"Villageois");
        CommandView.sendEmbed(tc,1,"Sorciere");
        CommandView.sendEmbed(tc,1,"Voleur");
        CommandView.sendEmbed(tc,1,"Voyante");
    }

    public static void sendEmbed(TextChannel channel,int numberemote,String role){
        File file = new File("C:\\Users\\Samuel\\IdeaProjects\\JavaBot\\src\\main\\resources\\" + role + ".png");
        EmbedBuilder msg = new EmbedBuilder();
        msg.setColor(Color.red);
        msg.setAuthor("Préparation au Loup Garou").setDescription("Combien en voulez-vous dans la partie ?");
        msg.setImage("attachment://img.png");
        msg.setTitle("Rôle : " + role);
        msg.setFooter("Tag:Config");
        Message send = channel.sendMessage(msg.build()).addFile(file,"img.png").complete();
        for(int i = 0; i <= numberemote; i++){
            String unicode = String.format("U+003%d U+20E3",i);
            send.addReaction(unicode).queue();
        }

    }

    public static void finishPartie(Message message) {
    }
}
