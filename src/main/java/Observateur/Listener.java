package Observateur;

import Controller.ControllerMetier;
import Controller.EventManager;
import Controller.JDAManager;
import Vues.CommandView;
import net.dv8tion.jda.api.entities.Message;
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
        if(event.getUser().isBot()) return;
        Message message = event.retrieveMessage().complete();
        String tag = message.getEmbeds().get(0).getFooter().getText().split(":")[1];
        if(tag.equalsIgnoreCase("config")){
            if(event.getUser() != ControllerMetier.proprietaire){
                System.out.println(event.getReaction().getReactionEmote().getId());
                message.removeReaction(event.getReaction().getReactionEmote().getId()).queue();
            }
        }

        System.out.println(message.getEmbeds().get(0).getTitle());


    }
}
