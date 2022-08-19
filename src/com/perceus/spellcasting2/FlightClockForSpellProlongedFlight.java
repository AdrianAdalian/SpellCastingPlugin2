package com.perceus.spellcasting2;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

public class FlightClockForSpellProlongedFlight extends BukkitClock
{
	boolean isFlying = false;
	Player player;
	
	public FlightClockForSpellProlongedFlight(Player player)
	{
		super(20, false);
		this.player = player;
		this.player.setAllowFlight(true);
	}
	@Override
	public void executeCycle()
	{
		if(PlayerDataMana.getPlayerData(player).getCurrentMana() > PlayerDataMana.getPlayerData(player).getMinMana())
	    {
	       PlayerDataMana.getPlayerData(player).setCurrentMana(PlayerDataMana.getPlayerData(player).getCurrentMana() - 20);
	            
	       isFlying = true;
	    }
	        
	    if(PlayerDataMana.getPlayerData(player).getCurrentMana() < PlayerDataMana.getPlayerData(player).getMinMana())
	    {
	       PlayerDataMana.getPlayerData(player).setCurrentMana(PlayerDataMana.getPlayerData(player).getMinMana());
	            
	       isFlying  = false;
	    }
	     
	    ManaInterface.updateScoreBoard(player);
	        
	    if(isFlying && !player.isFlying())
	    {
	       player.setFlying(true);
	    }
	        
	    if(!isFlying && player.isFlying())
	    {
	        player.setFlying(false);
	        player.setAllowFlight(false);
			player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
	        this.stop();
	    }
	}
}
