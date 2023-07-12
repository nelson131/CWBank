package me.nelson131.cwbank.commands.functions;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static me.nelson131.cwbank.utils.MessageBuilder.*;
import static me.nelson131.cwbank.utils.Properties.getCFG;

public class Withdraw extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Guild guild = event.getGuild();
        Member member = event.getMember();
        Channel channel = event.getChannel();
        Role role = guild.getRoleById(getCFG("moderator-role-id"));
        String id = member.getId();

        if(event.getName().equals("withdraw")){
            if(channel.getName().equals(id)){
                event.replyEmbeds(withdrawMessage()).queue();
                event.getChannel().sendMessage(role.getAsMention() + " " + getCFG("notify-staff")).queue();
            }
            else {
                event.replyEmbeds(unsuitableChannelMessage()).queue();
            }
        }
    }
}
