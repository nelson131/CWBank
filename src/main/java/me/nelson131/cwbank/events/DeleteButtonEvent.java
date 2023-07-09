package me.nelson131.cwbank.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

import static me.nelson131.cwbank.database.MySQL.deleteAccount;
import static me.nelson131.cwbank.utils.MessageBuilder.deleteNotifyMessage;
import static me.nelson131.cwbank.utils.Properties.getCFG;
import static me.nelson131.cwbank.utils.Roles.removeRole;

public class DeleteButtonEvent extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        Channel channel = event.getInteraction().getChannel();
        Member member = event.getMember();
        Guild guild = event.getGuild();
        String button = event.getButton().getId();
        String id = member.getId();


        if(button.equals("delete")){
            if(channel.getName().equals(id)){
                User user = event.getUser();
                event.reply("").queue();
                channel.delete().queue();
                sendPrivateChannel(user);
                try {
                    deleteAccount(member.getIdLong());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                removeRole(getCFG("owner-role"), member, guild);
                return;
            }
        }
        if(button.equals("cancel")){
            if(channel.getName().equals(id)){
                Message message = event.getInteraction().getMessage();
                message.delete().queue();
            }
        }
    }

    private static void sendPrivateChannel(User user){
        user.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessageEmbeds(deleteNotifyMessage()).queue();
        }));
    }
}
