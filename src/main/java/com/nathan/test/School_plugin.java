package com.nathan.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by nathan on 2/1/2016.
 */
public class School_plugin extends JavaPlugin implements Listener {
    public Calendar c = GregorianCalendar.getInstance();
    int[] times= new int[14];

    boolean timecheack =false;
    @Override
    public void onEnable() {
        times[0] = 5;
        times[1] = 6;
        times[2] = 7;
        times[3] = 8;
        times[4] = 11;
        times[5] = 13;
        // TODO Insert logic to be performed when the plugin is enabled
    }

    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }


    /* This is the player join event in this event i am cheching if the player can join*/
    @EventHandler
    public  void onPlayerJoin(PlayerJoinEvent event){
        start();
        if (timecheack){
            Player p = event.getPlayer();
            p.kickPlayer("it is not time ytou you to goine now");
        }
    }

    private void start() {
        for (int j = 0; j < times.length; j+=2) {
            if ((c.get(Calendar.HOUR) >= times[j]) && (c.get(Calendar.HOUR) <= times[j+1])){
                timecheack=true;
            }
        }
    }
}