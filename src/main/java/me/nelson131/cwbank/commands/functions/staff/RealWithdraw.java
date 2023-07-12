package me.nelson131.cwbank.commands.functions.staff;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

import static me.nelson131.cwbank.database.MySQL.checkWithdrawOption;
import static me.nelson131.cwbank.database.MySQL.withdrawBalance;
import static me.nelson131.cwbank.utils.MessageBuilder.*;
import static me.nelson131.cwbank.utils.Properties.getCFG;

public class RealWithdraw extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getName().equals("realwithdraw")){
            Guild guild = event.getGuild();
            Channel channel = event.getChannel();
            TextChannel textChannel = guild.getTextChannelById(getCFG("staff-channel"));

           if(channel.equals(textChannel)){
               if(event.getOption("discord-id") == null && event.getOption("amount") == null){
                   event.replyEmbeds(negativeWithdrawMessage()).queue();
                   return;
               }

               Long id = event.getOption("discord-id").getAsLong();
               int amount = event.getOption("amount").getAsInt();

               if(amount <= 0){
                   event.replyEmbeds(negativeAmountMessage()).queue();
               }

               try {
                   if(checkWithdrawOption(id, amount)){
                       withdrawBalance(id, amount);
                       event.reply(getCFG("accept-withdraw")).queue();
                   }
                   else {
                       event.replyEmbeds(bigAmountMessage()).queue();
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
