package me.nelson131.cwbank.commands.functions;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static me.nelson131.cwbank.utils.MessageBuilder.depositMessage;
import static me.nelson131.cwbank.utils.MessageBuilder.unsuitableChannelMessage;
import static me.nelson131.cwbank.utils.Properties.getCFG;

public class Deposit extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Channel channel = event.getChannel();
        Guild guild = event.getGuild();
        Role role = guild.getRoleById(getCFG("moderator-role-id"));
        String id = event.getMember().getId();


        if(event.getName().equals("deposit")){
            if(channel.getName().equals(id)){
                event.replyEmbeds(depositMessage()).queue();
                event.getChannel().sendMessage(role.getAsMention() + " " + getCFG("notify-staff")).queue();
            }
            else {
                event.replyEmbeds(unsuitableChannelMessage()).queue();
            }
        }
    }
}
