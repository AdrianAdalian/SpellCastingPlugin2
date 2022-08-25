package com.perceus.spellcasting2.void_spells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellEtherContinuity extends BaseSpellCapsule
{

	public SpellEtherContinuity()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fEther Continuity§r§f§ko§r", "SpellEtherContinuity", 0, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA spelltome with an incantation that", "§r§fmanipulates the caster's mana and health.","§r§fThis spell's effect differ from time of day.","§r§fDuring the §r§6Day§r§f, channel mana into health.","§r§fDuring the §r§7Night§r§f, channel health into mana.","§r§fIf §r§7Night§r§f:","§r§fTake 5 hearts of §r§cdamage§r§f.","§r§fRestore 750 §r§9mana§r§f.","§r§fIf §r§6Day§r§f:","§r§aHeal§r§f 10 hearts.","§r§fMana cost: 200 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		long time = Bukkit.getWorlds().get(0).getTime();
		
		if(time >= 0 && time <= 12000)
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 200);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			try
			{	
				event.getPlayer().setHealth(event.getPlayer().getHealth()+20);		
			}
			catch(IllegalArgumentException e)
			{			
				event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}	
			
			//While day, transform mana into health.
			return true;
		}
		
		if(time >=12005 && time <= 24000)
		{
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"You're already at full Mana.");
				return false;
			}
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			event.getPlayer().damage(10);
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 750);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			//While night, transform health into mana.
			return true;
		}
		return false;
	}

}
