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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by nathan on 2/1/2016.
 */

public class School_plugin extends JavaPlugin implements Listener{
    public Calendar c = GregorianCalendar.getInstance();
    public List<String> time= new ArrayList<String>();
    public String i="0|0";
    public Player pl;
    @Override
    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable(){

    }
    @Override// comand to add time to list
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Addtime")) { // If the player typed /basic then do the following...
                time.add(args[0]);
            //i=args[0];
            Player p = (Player) sender;
            p.chat(i);
                return true;
        } else if (cmd.getName().equalsIgnoreCase("basic2")) {
            return true;
        }
        return false;
    }
    @EventHandler //player join event
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        pl=p;

            if (chaketime()) {
                p.kickPlayer("not time yet");
            } else {
                p.sendMessage(ChatColor.RED + "Welcome to the server");
            int db = gettime(1)[0];
            int db1 = gettime(1)[1];
            p.chat(String.valueOf(db));
            p.chat(String.valueOf(db1));
            }
        //}
    }
    //try to get time
    public boolean chaketime() {
        int i =1;
        for (int j = 0; j < time.size(); j++) {
        if ((c.get(Calendar.HOUR) >= gettime(i)[0]) && (c.get(Calendar.HOUR) <= gettime(i)[1])) {
            return true;
        }
            i++;
    }
            return false;
    }
    public int[] gettime(int k/*String in*/){
        int[] timei = new int[2];
      // int i=0;
        for (int i = k-1; (i < time.size())&&(i<=k); i++) {
            //System.out.println(in.get(i));
            for (String retval: time.get(i).split("\\|")){
                timei[i]=Integer.parseInt(retval);
                //pl.chat(time.get(i));
                i++;
            }
        }
        return timei;
    }
}

