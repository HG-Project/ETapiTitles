package me.etblaky.titles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;

/**
 * Created by ETblaky on 10/11/2016.
 */
public class TitleApi extends JavaPlugin{

    public static void sendTitle(Player p, String msg, int fadeIn, int duration, int fadeOut){
        //PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a(toJjsonStr(msg)), fadeIn * 20, duration * 20, fadeOut * 20);
        //((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);


        try {
            Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, toJjsonStr(msg));

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(enumTitle, chat, fadeIn * 20, duration * 20, fadeOut * 20);

            sendPacket(p, packet);
        }

        catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    public static void sendSubTitle(Player p, String msg, int fadeIn, int duration, int fadeOut){
        //PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a(toJjsonStr(msg)), fadeIn * 20, duration * 20, fadeOut * 20);
        //((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);


        try {
            Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, toJjsonStr(msg));

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(enumTitle, chat, fadeIn * 20, duration * 20, fadeOut * 20);

            sendPacket(p, packet);
        }

        catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static String toJjsonStr(String s){
        s = "{\"text\":\"" + s + "\"}";

        return s;
    }

    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
