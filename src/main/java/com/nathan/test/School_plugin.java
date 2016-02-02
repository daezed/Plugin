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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        try {
            File file = new File("newfile.txt");
            f=file;
            if (file.createNewFile()){
                System.out.println("File is created!");
            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(f);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            // Always close files.
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            f + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + f + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        for (String retval : BufferedReader.readLine.split("!")) {
            // pl.setDisplayName(time.get(i));
            //pl.chat(time.get(i))
        }
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        String end="";
        for (int i = 0; (i < time.size()); i++) {
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
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        pl = p;

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
            if ((c.get(Calendar.HOUR) >= gettime(i)[0]) && (c.get(Calendar.HOUR) <= gettime(i)[1])) {
                return true;
            }
            pl.chat(String.valueOf(gettime(i)[1]));
            pl.chat(String.valueOf(gettime(i)[1]));
            i++;
        }
        return false;
    }

    public int[] gettime(int k/*String in*/) {
        int[] timei = new int[2];
        int h=0;
        for (int i = k - 1; (i < time.size()) && (i < k); i++) {
            //System.out.println(in.get(i));
            for (String retval : time.get(i).split("\\|")) {
                timei[h] = Integer.parseInt(retval);
                // pl.setDisplayName(time.get(i));
                //pl.chat(time.get(i));
                h++;
            }
        }
        return timei;
    }

}