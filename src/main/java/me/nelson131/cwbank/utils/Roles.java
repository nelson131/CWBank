package me.nelson131.cwbank.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Roles {

    public static void addRole(String id, Member member, Guild guild){
        Role role = guild.getRoleById(id);
        guild.addRoleToMember(member, role).queue();
    }

    public static boolean getRole(String id, Member member, Guild guild){
        Role role = guild.getRoleById(id);
        return  member.getRoles().contains(role);
    }

    public static void removeRole(String id, Member member, Guild guild){
        Role role = guild.getRoleById(id);
        guild.removeRoleFromMember(member, role).queue();
    }
}
