package me.nelson131.cwbank.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event){
        List<CommandData> commandDataList = new ArrayList<>();
        commandDataList.add(
                Commands.slash("welcome", "Sends a default message for create bank-create")
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        );
        commandDataList.add(Commands.slash("balance", "Sends to you a balance of your account"));
        commandDataList.add(Commands.slash("transfer", "Transfer your diamonds to another account")
                .addOption(OptionType.STRING, "discord-id", "To whom?")
                .addOption(OptionType.INTEGER, "amount", "How many?"));
        event.getGuild().updateCommands().addCommands(commandDataList).queue();
    }
}
