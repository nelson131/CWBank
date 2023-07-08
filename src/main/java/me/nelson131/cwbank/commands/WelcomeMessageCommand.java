package me.nelson131.cwbank.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import static me.nelson131.cwbank.utils.MessageBuilder.welcomeMessage;
import static me.nelson131.cwbank.utils.Properties.getCFG;

public class WelcomeMessageCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Button button = Button.success("account-create", getCFG("button-welcome"));

        if(event.getName().equals("welcome")){
            event.replyEmbeds(welcomeMessage())
                    .addActionRow(button).queue();
        }
    }
}
