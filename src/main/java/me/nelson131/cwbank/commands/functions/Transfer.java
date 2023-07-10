package me.nelson131.cwbank.commands.functions;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.sql.SQLException;

import static me.nelson131.cwbank.database.MySQL.checkTransferOption;
import static me.nelson131.cwbank.database.MySQL.transferBalance;
import static me.nelson131.cwbank.utils.MessageBuilder.*;

public class Transfer extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Channel channel = event.getChannel();
        String id = event.getMember().getId();

        if(event.getName().equals("transfer")){
            if(channel.getName().equals(id)){

                if (event.getOption("discord-id") == null && event.getOption("amount") == null) {
                    event.replyEmbeds(negativeTransferMessage()).queue();
                    return;
                }

                Long getterId = event.getOption("discord-id").getAsLong();
                int amount = event.getOption("amount").getAsInt();

                if(amount <= 0){
                    event.replyEmbeds(negativeAmountMessage()).queue();
                    return;
                }
                try {
                    if(checkTransferOption(getterId, Long.valueOf(id), amount)){
                        transferBalance(getterId, Long.valueOf(id), amount);
                        event.replyEmbeds(positiveTransferMessage(getterId, event.getMember().getIdLong(), amount)).queue();
                    }
                    else {
                        event.replyEmbeds(negativeTransferMessage()).queue();
                    }
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
