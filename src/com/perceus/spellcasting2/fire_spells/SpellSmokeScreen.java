package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSmokeScreen extends BaseSpellCapsule
{

	public SpellSmokeScreen()
	{
		super(Material.GUNPOWDER, "§r§7§ko§r§7§lSpell: §r§fSmokeScreen§r§7§ko§r", "SpellSmokescreen", 50, true, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §7Debuff§f §dAOE§f.","§r§fEmit a burst of ash that blinds all within range.","§r§fDuration: 20 seconds.","§r§fRange: 7 meters.","§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(7, 7, 7).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BONE_MEAL_USE, SoundCategory.MASTER, 1, 1);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(7, 7, 7)) 
		{
			if (target instanceof LivingEntity)
	    	{	
				SpellParticles.drawDisc(target.getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FLAME, null);
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 0, true));		
	    	}  
		}	
		return true; 
	}

}
