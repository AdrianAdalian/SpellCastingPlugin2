package com.perceus.spellcasting2.manamechanic;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fish.yukiemeralis.eden.Eden;

public class PlayerManaRegeneration implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event) 
	{
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana())
		{
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					if(!event.getPlayer().isOnline()) 
					{
						this.cancel();
						return;
					}
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 10);
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
					{
						PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
					}
					ManaInterface.updateScoreBoard(event.getPlayer());
					return;
				}
			}.runTaskTimer(Eden.getInstance(), 0, 40);
		}
		return;
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) 
	{
		if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) 
		{
			event.getPlayer().setAllowFlight(false);
			event.getPlayer().setFlying(false);
		}
		
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent event) 
	{

		if (event.getEntity() instanceof Player) 
		{
			PlayerDataMana.getPlayerData(event.getEntity()).setCurrentMana(PlayerDataMana.getPlayerData(event.getEntity()).getMinMana());
			ManaInterface.updateScoreBoard(event.getEntity());
		}
		if (event.getEntity().getKiller() instanceof Player) 
		{
            PlayerDataMana.getPlayerData(event.getEntity()).setCurrentMana(PlayerDataMana.getPlayerData(event.getEntity()).getMaxMana());;
            ManaInterface.updateScoreBoard(event.getEntity().getKiller());
		}
		return;
	}
	
}