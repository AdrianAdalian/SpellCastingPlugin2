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

public class SpellDebilitate extends BaseSpellCapsule
{

	public SpellDebilitate()
	{
		super(Material.GRAY_DYE, "§r§7§ko§r§7§lSpell: §r§fDebilitate§r§7§ko§r", "SpellDebilitate", 300, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fThe caster emits a force of §r§4§o§lUnholy§r§f","§r§fenergy that utterly debilitates any target around them.","§r§fEffects: Slowness, Fatigue, Weakness, Nausea and Starvation.","§r§fRange: 20 meters.","§r§fDuration: 25 seconds.","§r§fMana cost: 300 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
		PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
		return false;
		}
	
		if (event.getPlayer().getNearbyEntities(30, 30, 30).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30)) 
		{
			if (target instanceof LivingEntity)
			{	
				
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 2));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 2));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 2));
				
			}  
		}	
		return true; 
	}

}
