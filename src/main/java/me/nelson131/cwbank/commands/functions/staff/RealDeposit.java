package me.nelson131.cwbank.commands.functions.staff;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.privileges.IntegrationPrivilege;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.sql.SQLException;

import static me.nelson131.cwbank.database.MySQL.depositBalance;
import static me.nelson131.cwbank.database.MySQL.editBalance;
import static me.nelson131.cwbank.utils.MessageBuilder.*;
import static me.nelson131.cwbank.utils.Properties.getCFG;

public class RealDeposit extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Guild guild = event.getGuild();
        Channel channel = event.getChannel();
        TextChannel textChannel = guild.getTextChannelById(getCFG("staff-channel"));
        if(event.getName().equals("realdeposit")){
            if(channel.equals(textChannel)){

                if(event.getOption("discord-id") == null && event.getOption("amount") == null){
                    event.replyEmbeds(negativeDepositMessage()).queue();
                    return;
                }

                Long id = event.getOption("discord-id").getAsLong();
                int amount = event.getOption("amount").getAsInt();

                if(amount <= 0){
                    event.replyEmbeds(negativeAmountMessage()).queue();
                }

                else {
                    try {
                        depositBalance(id, amount);
                        event.reply(getCFG("accept-deposit")).queue();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            else {
                event.replyEmbeds(unsuitableChannelMessage()).queue();
            }
        }
    }
}
