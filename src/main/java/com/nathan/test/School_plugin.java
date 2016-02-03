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

import java.io.*;
import java.util.*;

/**
 * Created by nathan on 2/1/2016.
 * This is a plugin for minecraft server for schools to use to add a timed schedual that only lets kids in on a spasific time
 */

public class School_plugin extends JavaPlugin implements Listener {
    private BufferedWriter co;
    private Calendar c = GregorianCalendar.getInstance();
    private List<String> time = new ArrayList<String>();
    private File f;
    private String i = "0|0";
    private Player pl;

    @Override
    public void onEnable() {
        time.clear();

        try {
            File ft = new File("timefile.txt");
            f=ft;
            if (ft.createNewFile()){
                System.out.println("File is created!");
            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            for (String retval : br.readLine().split("!")) {
                retval.trim();
                if(!(retval.equals(""))) {
                   // System.out.println(retval);
                    time.add(retval);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }


        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        String end="";
        for (int i = 0; (i < time.size()&&!(time.get(i).equals(null))&&!(time.get(i).equals("!"))); i++) {
            end=(end+"!"+time.get(i));
        }

            try {
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(end);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();

            }



    }

    @Override// comand to add time to list
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Server_Time")&&(args[0].equals("now"))) {
            Player p =(Player)sender;
            p.chat(String.valueOf(c.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(c.get(Calendar.MINUTE)));

        }
        System.out.println("test");
        if (cmd.getName().equalsIgnoreCase("Addtime")) { // If the player typed /basic then do the following...
            time.add(args[0]);
            //i=args[0];
            if (chaketime()) {
                for(Player all : Bukkit.getServer().getOnlinePlayers())
                {
                    all.kickPlayer("sorry time to go");
                }
            }



            return true;
        } else if (cmd.getName().equalsIgnoreCase("basic2")) {
            return true;
        }
        return false;
    }

    @EventHandler //player join event
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        pl = p;
        System.out.println(chaketime());
        if (chaketime()) {
            p.kickPlayer("not time yet");
        } else {
            p.sendMessage(ChatColor.RED + "Welcome to the server");

            /* int db = gettime(1)[0];
              int db1 = gettime(1)[1];
              p.chat(String.valueOf(db));
 -            p.chat(String.valueOf(db1));
 +            p.chat(String.valueOf(db1));*/
        }
        //}
    }

    //try to get time
    public boolean chaketime() {
        int i = 1;
        for (int j = 0; j < time.size(); j++) {
            //System.out.println(c.get(Calendar.HOUR));
            //System.out.println(gettime(i)[0]);
            //System.out.println(gettime(i)[1]);
           //System.out.println(gettime(i)[2]);
           // System.out.println(gettime(i)[3]);
            System.out.print((c.get(Calendar.HOUR_OF_DAY) >= gettime(i)[0]) && (c.get(Calendar.HOUR_OF_DAY) <= gettime(i)[1])&&(c.get(Calendar.MINUTE) >= gettime(i)[2]) && (c.get(Calendar.MINUTE) <= gettime(i)[3]));
            System.out.print(" "+gettime(i)[0]+"|"+gettime(i)[1]+"|"+gettime(i)[2]+"|"+gettime(i)[3]);
            System.out.println();

            if ((c.get(Calendar.HOUR_OF_DAY) >= gettime(i)[0]) && (c.get(Calendar.HOUR_OF_DAY) <= gettime(i)[1])&&(c.get(Calendar.MINUTE) >= gettime(i)[2]) && (c.get(Calendar.MINUTE) <= gettime(i)[3])){
                return true;
            }
            //pl.chat(String.valueOf(gettime(i)[1]));
            //pl.chat(String.valueOf(gettime(i)[1]));
            i++;
        }
        return false;
    }

    public int[] gettime(int k/*String in*/) {
        int[] timei = new int[4];
        int h=0;
        for (int i = k - 1; (i < time.size()) && (i < k); i++) {
            //System.out.println(in.get(i));
            for (String retval : time.get(i).split("\\|")) {
                //System.out.println(time.toString());
                timei[h] = Integer.parseInt(retval);
                // pl.setDisplayName(time.get(i));
                //pl.chat(time.get(i));
                h++;
            }
        }
        return timei;
    }

}