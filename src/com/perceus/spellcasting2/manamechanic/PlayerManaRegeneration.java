package com.perceus.spellcasting2.manamechanic;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fish.yukiemeralis.eden.Eden;

public class PlayerManaRegeneration implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event) 
	{
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana() && PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getNegMana()) 
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
					
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
					{
						this.cancel();
						return;
					}
					
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, true));
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 2, true));
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2, true));
					return;
				}
			}.runTaskTimer(Eden.getInstance(), 0, 35);
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() == 500)
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
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() == 750)
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
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() == 1000)
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
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 15);
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
					{
						PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
					}
					ManaInterface.updateScoreBoard(event.getPlayer());
					return;
				}
			}.runTaskTimer(Eden.getInstance(), 0, 40);
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() > 1000 && PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() < 2000)
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
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 20);
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
					{
						PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
					}
					ManaInterface.updateScoreBoard(event.getPlayer());
					return;
				}
			}.runTaskTimer(Eden.getInstance(), 0, 40);
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() && PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana() == 2000)
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
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 20);
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
					{
						PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
					}
					ManaInterface.updateScoreBoard(event.getPlayer());
					return;
				}
			}.runTaskTimer(Eden.getInstance(), 0, 20);
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