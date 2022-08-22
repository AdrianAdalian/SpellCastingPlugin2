package com.perceus.spellcasting2.geo_spells;

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

public class SpellPoisonCloud extends BaseSpellCapsule
{

	public SpellPoisonCloud()
	{
		super(Material.SLIME_BALL, "§r§7§ko§r§7§lSpell: §r§fPoison Cloud§r§7§ko§r", "SpellPoisonCloud", 150, true, true, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fEmits a cloud of poison around the caster.","§r§fDeals 4 hearts of §r§cdamage§r§f over 10 seconds.","§r§fRange: 15 meters.","§r§fMana cost: 150 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(15, 15, 15).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
		
		for (Entity target : event.getPlayer().getNearbyEntities(15, 15, 15)) 
		{
			
			if (target instanceof LivingEntity)
	    	{
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FALLING_SPORE_BLOSSOM, null);
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
	    	}  
		}	
		
		return true;
	}
}
