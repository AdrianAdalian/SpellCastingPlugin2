package com.perceus.spellcasting2.unholy_spells;

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

public class SpellRot extends BaseSpellCapsule
{

	public SpellRot()
	{
		super(Material.GRAY_DYE, "§r§7§ko§r§7§lSpell: §r§fRot§r§7§ko§r", "SpellRot", 200, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fSpell Type: §7Debuff§f §dAOE§f.", "§r§fEmit a burst of §r§4§o§lUnholy§r§f energy around the caster","§r§fcausing those within range to begin to rot.","§r§fThe targets are slowed as a result of acute necrosis.","§r§4Wither §r§f8-10 hearts over 10 seconds.","§r§fRange: 15 meters.","§r§fMana cost: 200 §r§9mana§r§f.");
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
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(15, 15, 15)) 
		{
			if (target instanceof LivingEntity)
			{	
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 9));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
			}  
		}	
		return true; 
	}

}
