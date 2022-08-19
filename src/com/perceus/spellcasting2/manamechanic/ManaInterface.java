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
		PlayerDataMana.getPlayerData(event.getPlayer());
		setScoreBoard(event.getPlayer());
	}
	
	@EventHandler
	public void uponLogout(PlayerQuitEvent event) 
	{
		JsonUtils.toJsonFile("./plugins/Eden/playerdata/data/" + event.getPlayer().getUniqueId().toString() + ".json", PlayerDataMana.getPlayerData(event.getPlayer()));
	}
	
	public void setScoreBoard(Player player) 
	{
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		//Makes a new scoreboard.
        Objective obj = board.registerNewObjective("§r§f| §9Mana §f|", "§r§f| §9Mana §f|", "§r§f| §9Mana §f|");
        //Creates our new objective and scoreboard. For whatever reason, Java wants a format of 3's in the cast of scoreboard titles.
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        //Sets the display to the right side of the screen.
        obj.getScore(ChatColor.BLACK + "§r§fCurrent Mana:" + ChatColor.WHITE).setScore(PlayerDataMana.getPlayerData(player).getCurrentMana());
        //Gets the title and the basic score for our mana.
        player.setScoreboard(board);
        //Sets the scoreboard once we've made it.
	}    
    public static void updateScoreBoard(Player player) 
    {
    	 Scoreboard board = player.getScoreboard();
    	 Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
    	 
    	 obj.getScore(ChatColor.BLACK + "§r§fCurrent Mana:" + ChatColor.WHITE).setScore(PlayerDataMana.getPlayerData(player).getCurrentMana());
	}
    
}
