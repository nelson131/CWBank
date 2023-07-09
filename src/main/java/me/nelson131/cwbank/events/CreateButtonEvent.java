package me.nelson131.cwbank.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;
import java.util.EnumSet;

import static me.nelson131.cwbank.database.MySQL.*;
import static me.nelson131.cwbank.utils.MessageBuilder.*;
import static me.nelson131.cwbank.utils.Properties.getCFG;
import static me.nelson131.cwbank.utils.Roles.addRole;
import static me.nelson131.cwbank.utils.Roles.getRole;

public class CreateButtonEvent extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        String button = event.getButton().getId();
        if(button.equals("account-create")){
            Guild guild = event.getGuild();
            Member member = event.getMember();
            Category category = event.getGuild().getCategoryById(getCFG("category-id"));

            if(!getRole(getCFG("owner-role"), member, guild)){
                TextChannel channel = guild.createTextChannel(member.getId(), category)
                        .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(guild.getRoleById(getCFG("moderator-role-id")), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .complete();

                try {
                    createAccount(member.getIdLong(), 0);
                    addRole(getCFG("owner-role"), member, guild);

                    channel.sendMessageEmbeds(startMessage()).queue();
                    channel.sendMessageEmbeds(getBalanceMessage(member.getIdLong(), member.getEffectiveName())).queue();
                    event.replyEmbeds(replyMessage()).setEphemeral(true).queue();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                event.replyEmbeds(alreadyHaveAccount()).setEphemeral(true).queue();
            }
        }
    }
}
