package me.nelson131.cwbank;

import me.nelson131.cwbank.commands.WelcomeMessageCommand;
import me.nelson131.cwbank.commands.CommandManager;
import me.nelson131.cwbank.commands.functions.Balance;
import me.nelson131.cwbank.commands.functions.Delete;
import me.nelson131.cwbank.commands.functions.Deposit;
import me.nelson131.cwbank.commands.functions.Transfer;
import me.nelson131.cwbank.commands.functions.staff.RealDeposit;
import me.nelson131.cwbank.events.CreateButtonEvent;
import me.nelson131.cwbank.events.DeleteButtonEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static me.nelson131.cwbank.utils.Properties.getCFG;

public class CWBank {

    public static final Properties config = new Properties();
    public static JDA jda;

    public static void main(String[] args) throws IOException, InterruptedException {
        config.load(new FileInputStream("config.properties"));

        jda = JDABuilder.createDefault(getCFG("bot-token"))
                .setActivity(Activity.playing(getCFG("activity-playing")))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new WelcomeMessageCommand(), new CommandManager(), new CreateButtonEvent(), new Balance(), new Transfer(), new Delete(), new DeleteButtonEvent(), new Deposit(), new RealDeposit())
                .build();

        jda.awaitReady();
    }
}
