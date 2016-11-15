package me.etblaky.titles;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ETblaky on 10/11/2016.
 */
public class TitleApi extends JavaPlugin{

    public static void sendTitle(Player p, String msg, int fadeIn, int duration, int fadeOut){
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(toJjsonStr(msg)), fadeIn * 20, duration * 20, fadeOut * 20);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
    }

    public static void sendSubTitle(Player p, String msg, int fadeIn, int duration, int fadeOut){
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(toJjsonStr(msg)), fadeIn * 20, duration * 20, fadeOut * 20);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
    }

    public static String toJjsonStr(String s){

        //{\"text\":\"Welcome\"} ""

        s = "{\"text\":\"" + s + "\"}";

        return s;
    }

}
