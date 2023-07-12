package me.nelson131.cwbank.utils;

import me.nelson131.cwbank.database.MySQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;

import java.awt.*;
import java.sql.SQLException;

import static me.nelson131.cwbank.utils.Properties.getCFG;

public class MessageBuilder {

    private static final Color main = new Color(124, 0, 207);
    private static final Color problem = new Color(255, 0, 0);

    public static MessageEmbed welcomeMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("acc-create-title"));
        ed.addField(getCFG("acc-create-field1-title"), getCFG("acc-create-field1"), false);
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        ed.setImage(getCFG("acc-create-image"));
        return ed.build();
    }

    public static MessageEmbed startMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("start-message-title"));
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        ed.addField(getCFG("start-message-field-title"), getCFG("start-message-field"), true);
        return ed.build();
    }

    public static MessageEmbed replyMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("reply-message-title"));
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        return ed.build();
    }

    public static MessageEmbed getBalanceMessage(Long id, String name) throws SQLException {
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("balance-title") + " " + name);
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        ed.addField(getCFG("balance-field-title"),MySQL.getBalance(id) + " diamonds", true);
        return ed.build();
    }

    public static MessageEmbed unsuitableChannelMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("unsuit-title"));
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        ed.setDescription(getCFG("unsuit-desc"));
        return ed.build();
    }

    public static MessageEmbed negativeAmountMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("negative-title"));
        ed.setDescription(getCFG("negative-desc"));
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        return ed.build();
    }

    public static MessageEmbed negativeTransferMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("neg-transfer-title"));
        ed.setDescription(getCFG("neg-transfer-desc"));
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        return ed.build();
    }

    public static MessageEmbed positiveTransferMessage(Long getter, Long sender, int amount){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("pos-transfer-title"));
        ed.addField(getCFG("pos-transfer-field-title") + " " + getter + " " + getCFG("pos-transfer-field-title2") + " " + sender, getCFG("pos-transfer-field") + " " + amount, true);
        ed.setThumbnail(getCFG("acc-create-thumbnail"));
        return ed.build();
    }

    public static MessageEmbed alreadyHaveAccount(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("al-acc-title"));
        ed.setDescription(getCFG("al-acc-desc"));
        return ed.build();
    }

    public static MessageEmbed deleteMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("delete-title"));
        ed.setDescription(getCFG("delete-desc"));
        return ed.build();
    }

    public static MessageEmbed deleteNotifyMessage(){
        EmbedBuilder ed  = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("del-notify-title"));
        ed.setDescription(getCFG("del-notify-desc"));
        return ed.build();
    }

    public static MessageEmbed depositMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("deposit-title"));
        return ed.build();
    }

    public static MessageEmbed negativeDepositMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("neg-dep-title"));
        ed.setDescription(getCFG("neg-dep-desc"));
        return ed.build();
    }

    public static MessageEmbed withdrawMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("withdraw-title"));
        return ed.build();
    }

    public static MessageEmbed negativeWithdrawMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("neg-with-title"));
        ed.setDescription(getCFG("neg-with-desc"));
        return ed.build();
    }

    public static MessageEmbed bigAmountMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(problem);
        ed.setTitle(getCFG("big-amount"));
        return ed.build();
    }
}
