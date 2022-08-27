package com.perceus.spellcasting2.manamechanic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fish.yukiemeralis.eden.utils.JsonUtils;


public class ManaInterface implements Listener
{
	@EventHandler
	public void uponLogin(PlayerJoinEvent event) 
	{
		PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId());
		setScoreBoard(event.getPlayer());
	}
	
	@EventHandler
	public void uponLogout(PlayerQuitEvent event) 
	{
		JsonUtils.toJsonFile("./plugins/Eden/playerdata/data/" + event.getPlayer().getUniqueId().toString() + ".json", PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()));
	}
	
	public void setScoreBoard(Player player) 
	{
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("§r§f| §9Mana §f|", "§r§f| §9Mana §f|", "§r§f| §9Mana §f|");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore(ChatColor.BLACK + "§r§fMaximum Mana:" + ChatColor.WHITE).setScore(PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana());
        obj.getScore(ChatColor.BLACK + "§r§fCurrent Mana:" + ChatColor.WHITE).setScore(PlayerDataMana.getPlayerData(player.getUniqueId()).getCurrentMana());
        if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 500) 
   	 	{
        	obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(10);
   	 	}
        if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 750) 
   	 	{
        	obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(10);
   	 	}
   	 	if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 1000) 
   	 	{
   	 		obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(15);
   	 	}
   	 	if (PlayerDataMana.getPlayerData(player.getPlayer().getUniqueId()).getMaxMana() > 1000 && PlayerDataMana.getPlayerData(player.getPlayer().getUniqueId()).getMaxMana() < 2000)
   	 	{
   	 		obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(20);
   	 	}
		if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 2000)
		{
			obj.getScore(ChatColor.BLACK + "§r§fRegen/s:" + ChatColor.WHITE).setScore(20);
		}
        player.setScoreboard(board);
        //Sets the scoreboard once we've made it.
	}    
    public static void updateScoreBoard(Player player) 
    {
    	 Scoreboard board = player.getScoreboard();
    	 Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
    	 obj.getScore(ChatColor.BLACK + "§r§fMaximum Mana:" + ChatColor.WHITE).setScore(PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana());
    	 obj.getScore(ChatColor.BLACK + "§r§fCurrent Mana:" + ChatColor.WHITE).setScore(PlayerDataMana.getPlayerData(player.getUniqueId()).getCurrentMana());
    	 
    	 if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 500) 
    	 {
    		 obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(10);
    	 }
    	 
    	 if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 750) 
    	 {
    		 obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(10);
    	 }
    	 
    	 if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 1000) 
    	 {
    		 obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(15);
    	 }
    	 
    	 if (PlayerDataMana.getPlayerData(player.getPlayer().getUniqueId()).getMaxMana() > 1000 && PlayerDataMana.getPlayerData(player.getPlayer().getUniqueId()).getMaxMana() < 2000)
    	 {
    		 obj.getScore(ChatColor.BLACK + "§r§fRegen/2s:" + ChatColor.WHITE).setScore(20);
    	 }
    		 
		 if (PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana() == 2000)
		 {
			 obj.getScore(ChatColor.BLACK + "§r§fRegen/s:" + ChatColor.WHITE).setScore(20);
		 }
	}
    
}
