package me.nelson131.cwbank.commands.functions;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import static me.nelson131.cwbank.utils.MessageBuilder.deleteMessage;
import static me.nelson131.cwbank.utils.MessageBuilder.unsuitableChannelMessage;
import static me.nelson131.cwbank.utils.Properties.getCFG;

public class Delete extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        Member member = event.getMember();
        Channel channel = event.getChannel();
        Button delete = Button.danger("delete", getCFG("button-delete"));
        Button cancel = Button.success("cancel", getCFG("button-cancel"));
        String id = member.getId();

        if(event.getName().equals("delete")){
            if(channel.getName().equals(id)){
                event.replyEmbeds(deleteMessage())
                        .addActionRow(delete, cancel).queue();
            }
            else {
                event.replyEmbeds(unsuitableChannelMessage()).queue();
            }
        }
    }
}
