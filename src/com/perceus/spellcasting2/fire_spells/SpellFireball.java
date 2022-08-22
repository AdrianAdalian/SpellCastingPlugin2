package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Fireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellFireball extends BaseSpellCapsule
{

	public SpellFireball()
	{
		super(Material.FIRE_CHARGE, "§r§7§ko§r§7§lSpell: §r§fFireBall§r§7§ko§r", "SpellFireBall", 35, true, true,"§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §cOffensive§f.",
				"§r§fSummons a small ball of fire that explodes on impact.","§r§fMana cost: 35 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		//casting animation for Element: Fire.
		event.getPlayer().launchProjectile(Fireball.class);
		return true;
	}
}
