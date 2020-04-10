package ru.frostdelta.discord;

import org.bukkit.ChatColor;
import ru.looprich.discordlogger.DiscordLogger;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static String buildCommand(String[] args) {
        StringBuilder cmd = new StringBuilder();
        for (String arg : args) {
            cmd.append(arg).append(" ");
        }
        return cmd.toString();
    }


    public static String getPermission(){
        return DiscordLogger.getInstance().getConfig().getString("admin-permission");
    }

    public static boolean checkContains(String command){
        for(String cmd : getIgnoredCmds()){
            if(command.contains(cmd)) return true;
        }
        return false;
    }

    public static List<String> getIgnoredCmds(){
        return DiscordLogger.getInstance().getConfig().getStringList("ignored-commands");
    }

    public static String removeCodeColors(String message) {
        return ChatColor.stripColor(message);
    }

    public static List<String> removeCodeColors(String[] messages) {
        List<String> message = new ArrayList<>();
        for (String msg : messages)
            message.add(ChatColor.stripColor(msg));
        return message;
    }

    public static String cancelFormatMessage(String message) {
        String[] array = message.split(" ");
        StringBuilder msg = new StringBuilder();
        int pos1, pos2, difference;
        for (int i = 0; i <= array.length - 1; i++) {
            StringBuilder buff = new StringBuilder(" " + array[i]);

            pos1 = buff.toString().indexOf('_');
            pos2 = buff.toString().lastIndexOf('_');
            difference = Math.max(pos1, pos2) - Math.min(pos1, pos2);
            if (pos1 != pos2 && difference != 1) buff.insert(pos1, "\\");

            pos1 = buff.toString().indexOf('*');
            pos2 = buff.toString().lastIndexOf('*');
            difference = Math.max(pos1, pos2) - Math.min(pos1, pos2);
            if (pos1 != pos2 && difference != 1) buff.insert(pos1, "\\");

            pos1 = buff.toString().indexOf('~');
            pos2 = buff.toString().lastIndexOf('~');
            difference = Math.max(pos1, pos2) - Math.min(pos1, pos2);
            if (pos1 != pos2 && difference != 1) buff.insert(pos1, " \\");

            msg.append(buff.toString());
        }
        return msg.toString();
    }

}
