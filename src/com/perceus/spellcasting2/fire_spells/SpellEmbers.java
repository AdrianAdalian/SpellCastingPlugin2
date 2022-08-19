package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellEmbers extends BaseSpellCapsule
{

	public SpellEmbers()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fEmbers§r§7§ko§r", "SpellEmbers", 25, true, true, "§r§fElement: §r§cFire§r§f.", 
				"§r§fThe caster throws a handful of","§r§fhost embers burning whatever it hits.",
				"§r§fMana cost: 25 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
		SmallFireball largefireball = event.getPlayer().launchProjectile(SmallFireball.class) ;
		largefireball.setVelocity(largefireball.getVelocity().multiply(10));
		
		return true;
	}

}
