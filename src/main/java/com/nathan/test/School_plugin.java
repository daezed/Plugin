package com.nathan.test;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by nathan on 2/1/2016.
 */
public class School_plugin extends JavaPlugin {
    public Calendar c = GregorianCalendar.getInstance();
    @Override
    public void onEnable(){


    }

    @Override
    public void onDisable(){

    }
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(c.get(Calendar.HOUR)==2){
            p.kickPlayer("not time yet");
        }
        p.sendMessage(ChatColor.RED + "Welcome to the server");
    }
}
