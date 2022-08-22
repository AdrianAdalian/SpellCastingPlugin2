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

public class SpellEarthquake extends BaseSpellCapsule
{

	public SpellEarthquake()
	{
		super(Material.BROWN_DYE, "§r§7§ko§r§7§lSpell: §r§fEarthquake§r§7§ko§r", "SpellEarthquake", 500, true, true, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §dAOE§f.","§r§fCause an earthquake that damages","§r§fand stuns all within the radius.","§r§fDeals 5 hearts of §r§cdamage§r§f to all affected.","§r§fStun for 5 seconds.","§r§fRange: 30 meters.","§r§fMana cost: 500 §r§9mana§r§f.");
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
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STONE_BREAK, SoundCategory.MASTER, 1, 1);
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30))
		{
			if (target instanceof Damageable)
			{
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CAMPFIRE_COSY_SMOKE, null);
				((Damageable) target).damage(10, event.getPlayer());
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99));
			}
		}
		return true; 
	}

}
