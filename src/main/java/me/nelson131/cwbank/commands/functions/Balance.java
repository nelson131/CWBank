package me.nelson131.cwbank.commands.functions;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

import static me.nelson131.cwbank.utils.MessageBuilder.getBalanceMessage;
import static me.nelson131.cwbank.utils.MessageBuilder.unsuitableChannelMessage;

public class Balance extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Channel channel = event.getChannel();
        String id = event.getMember().getId();
        String senderName = event.getMember().getEffectiveName();

        if(event.getName().equals("balance")){
            if(channel.getName().equals(id)){
                try {
                    event.replyEmbeds(getBalanceMessage(Long.valueOf(id), senderName)).queue();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                event.replyEmbeds(unsuitableChannelMessage()).queue();
            }
        }
    }
}
