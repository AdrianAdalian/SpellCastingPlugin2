package com.perceus.spellcasting2.geo_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellTillEarth extends BaseSpellCapsule
{

	public SpellTillEarth()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fTill Earth§r§f§ko§r", "SpellTillEarth", 10, false, "§r§fElement: §r§6Geo§r§f.","§r§fA simple incantation that agrigates","§r§fthe target dirt or grass block.","§r§fPodzol is able to be tilled.","§r§fMana cost: 10 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}	
		
		Block target = event.getPlayer().getTargetBlock(null, 5);
		
		if (target == null)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (target.getType().equals(Material.GRASS_BLOCK)) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_HOE_TILL, SoundCategory.MASTER, 1, 1);
			target.setType(Material.FARMLAND);
			return true;
		}
		if (target.getType().equals(Material.DIRT)) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_HOE_TILL, SoundCategory.MASTER, 1, 1);
			target.setType(Material.FARMLAND);
			return true;
		}
		if (target.getType().equals(Material.COARSE_DIRT)) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_HOE_TILL, SoundCategory.MASTER, 1, 1);
			target.setType(Material.FARMLAND);
			return true;
		}
		if (target.getType().equals(Material.ROOTED_DIRT)) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_HOE_TILL, SoundCategory.MASTER, 1, 1);
			target.setType(Material.FARMLAND);
			return true;
		}
		if (target.getType().equals(Material.PODZOL)) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_HOE_TILL, SoundCategory.MASTER, 1, 1);
			target.setType(Material.FARMLAND);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
		return false;
	}

}
