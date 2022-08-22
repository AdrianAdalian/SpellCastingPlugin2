package com.perceus.spellcasting2.geo_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSandstorm extends BaseSpellCapsule
{

	public SpellSandstorm()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fSandstorm§r§f§ko§r", "SpellSandstorm", 250, false, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §7Debuff§f §dAOE§f.","§r§fThe caster summons a brief sandstorm around them,","§r§fcausing any within to be briefly blinded and slowed.","§r§fAlso deals 3 hearts of §r§cdamage§r§f.","§r§fDuration: 15 seconds.","§r§fRange: 30 meters.","§r§fMana cost: 250 §r§9mana§r§f.");
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
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SAND_BREAK, SoundCategory.MASTER, 1, 1);
		
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30)) 
		{
			if (target instanceof LivingEntity)
			{
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CAMPFIRE_COSY_SMOKE, null);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 0, true));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0, true));
				((Damageable) target).damage(6);
			}
		}
		return true;
	}

}
