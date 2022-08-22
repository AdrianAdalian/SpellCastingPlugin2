package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellOverheat extends BaseSpellCapsule
{

	public SpellOverheat()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Overheat§r§f§ko§r", "SpellOverheat", 200, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fSet all targets within a small radius ablaze.","§r§fDeals 10 hearts of §r§cdamage§r§f over 16 seconds to each target.","§r§fRange: 15 meters.","§r§fMana cost: 200 §r§9mana§r§f.");
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
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		event.getPlayer().getWorld().spawnParticle(Particle.FLAME, event.getPlayer().getLocation(), 20);
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_AMBIENT, SoundCategory.MASTER, 1, 1);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
		
		for (Entity target : event.getPlayer().getNearbyEntities(15, 15, 15)) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FLAME, null);
			target.setFireTicks(400);
		}
		return true; 
	}

}
//,"§r§fSpell Type: §7Debuff§f §dAOE§f."