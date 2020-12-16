package Controller;

import Vues.CommandView;
import com.sun.javafx.image.IntPixelGetter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class EventManager {

    public static void selectedCommand(Message message) {
        String[] arg = message.getContentDisplay().split(" ");
        if(arg[0].equalsIgnoreCase("~start")){
            System.out.println("Start is detected");
            if(!message.getCategory().getName().equalsIgnoreCase("LG Préparation")){
                System.out.println("Wrong usage of start");
                CommandView.usage(message, arg[0]);
            }
            else
                System.out.println("start is launch");
                try{
                    VoiceChannel vc = JDAManager.getJda().getCategoriesByName("LG Préparation",true).get(0).getVoiceChannels().get(0);
                    ControllerMetier.createParti(vc.getMembers().size(),message);
                }catch (Exception e){
                    CommandView.usage(message,arg[0]);
                }

        }
        else if(arg[0].equalsIgnoreCase("~firstRun")){
            System.out.println("FirstRun is detected");
            if(arg.length != 1) {
                System.out.println("Wrong usage of FirstRun");
                CommandView.usage(message, arg[0]);
            }
            else{
                System.out.println("FirstRun is launch");
                CommandView.createRequired(message);
            }
        }
        else if(arg[0].equalsIgnoreCase("~newPartie")){
            System.out.println("newPartie is detected");
            if(arg.length != 1 || !message.getCategory().getName().equalsIgnoreCase("LG Préparation")){
                System.out.println("Wrong usage of NewPartie");
                CommandView.usage(message, arg[0]);
            }
            if(!ControllerMetier.config)
                message.getChannel().sendMessage("A party is already launch").complete();
            else{
                System.out.println("Newpartie is launch");
                ControllerMetier.configPartie(message);
            }
        }
        else if(arg[0].equalsIgnoreCase("~finishPartie")){
            System.out.println("FinishPartie is detected");
            if(arg.length != 1 || !message.getCategory().getName().equalsIgnoreCase("LG Préparation")){
                System.out.println("Wrong usage of FinishPartie");
                CommandView.usage(message, arg[0]);
            }
            else{
                System.out.println("FinishPartie is launch");
                ControllerMetier.finishPartie(message);
            }
        }
    }

}
