package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.DragonFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellChaosFireball extends BaseSpellCapsule
{

	public SpellChaosFireball()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Chaos Fireball§r§f§ko§r", "SpellChaosFireball", 75, true, 
				"§r§fElement: §r§cFire§r§f.", 
				"§r§fSummons a ball of fire engulfed in void energy.",
				"§r§fMana cost: 75 §r§9mana§r§f.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.MASTER, 1, 1);
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		
		DragonFireball dragonfireball = event.getPlayer().launchProjectile(DragonFireball.class) ;
		dragonfireball.setVelocity(dragonfireball.getVelocity().multiply(2));
		return true;
	}

}
