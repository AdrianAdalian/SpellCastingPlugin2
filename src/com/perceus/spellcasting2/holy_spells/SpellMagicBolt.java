package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellMagicBolt extends BaseSpellCapsule
{

	public SpellMagicBolt()
	{
		super(Material.SPECTRAL_ARROW, "§r§7§ko§r§7§lSpell: §r§fMagic Missile§r§7§ko§r", "SpellMagicBolt", 25, true, true, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fExpell a bolt of light that deals minor damage.","§r§fDeals 1 heart of §r§cdamage§r§f.","§r§fMana cost: 25 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
		arrow.setPickupStatus(PickupStatus.DISALLOWED);
		arrow.setCritical(false);
		arrow.setKnockbackStrength(0);
		arrow.setDamage(2);	
		return true;
	}

}
