package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellItem_HealthCrystal extends BaseSpellCapsule
{

	public SpellItem_HealthCrystal()
	{
		super(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fHealth Crystal§r§7§ko§r", "SpellItem_HealthCrystal", 0, true, "§r§fElement: §r§f§o§lHoly§r§f.", "§r§fA conglomerate of crystalized ether.","§r§fThis crystal, when broken,","§r§aheals §r§f5 hearts of health.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}	
		
		if (event.getPlayer().getHealth() == event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You're already at maximum Health.");
			return false;
		}
		
		if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
		}
		else
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		}
		
		try
		{
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().setHealth(event.getPlayer().getHealth()+10);		
		}
		catch(IllegalArgumentException e)
		{			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		}	
		return true;
	}

}
