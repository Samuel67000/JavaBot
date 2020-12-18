package Observateur;

import Controller.ControllerMetier;
import Controller.EventManager;
import Controller.JDAManager;
import Vues.CommandView;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if(event.getMessage().getContentDisplay().startsWith("~")){
            System.out.println("Command detected...");
            EventManager.selectedCommand(event.getMessage());

        }

    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        super.onMessageReactionAdd(event);
        if(event.getUser().isBot()) {
            return;
        }
        Message message = event.retrieveMessage().complete();
        String tag = message.getEmbeds().get(0).getFooter().getText().split(":")[1];
        if(tag.equalsIgnoreCase("config")){
            if(event.getUser() != ControllerMetier.proprietaire){
                message.removeReaction(event.getReaction().getReactionEmote().getAsReactionCode(), event.getUser()).queue();
            }
            else{

                for(MessageReaction react:message.getReactions()){
                    if(react.getCount() > 1 && !react.toString().equals(event.getReaction().toString())){
                        message.removeReaction(react.getReactionEmote().getAsReactionCode(),ControllerMetier.proprietaire).queue();
                    }
                }
            }
        }



    }
}
