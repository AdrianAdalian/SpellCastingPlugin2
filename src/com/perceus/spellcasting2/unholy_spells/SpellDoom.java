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

public class SpellDoom extends BaseSpellCapsule
{

	public SpellDoom()
	{
		super(Material.GRAY_DYE, "§r§7§ko§r§7§lSpell: §r§fDoom§r§7§ko§r", "SpellDoom", 225, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fSpell Type: §7Debuff§f §dAOE§f.","§r§fEmit a burst of §r§4§o§lUnholy§r§f energy around the caster.","§r§fThose affected will be doomed","§r§fto living in darkness and decay.","§r§fDarkens the targets' field of vision","§r§fand withers their life force.","§r§fDeals 4-6 hearts of §r§cdamage§r§f over time.","§r§fDuration: 30 seconds.","§r§fRange: 30 meters.","§r§fMana cost: 225 §r§9mana§r§f.");
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
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 600, 0));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 600, 0));		
			}  
		}	
		return true; 
	}
}
