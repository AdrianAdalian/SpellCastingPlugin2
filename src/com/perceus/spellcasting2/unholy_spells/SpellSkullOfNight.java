package com.perceus.spellcasting2.unholy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSkullOfNight extends BaseSpellCapsule
{

	public SpellSkullOfNight()
	{
		super(Material.ENCHANTED_BOOK,"§r§fTome: Skull of Night", "SpellSkullOfNight", 100, false, "§r§fElement: §r§4§o§lUnholy§r§f.",
				"§r§fSpell Type: §cOffensive§f.",
				"§r§fCast out a slow-moving, cursed wither skull.",
				"§r§fDeals miniscule damage and explodes on impact.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.SMOKE_LARGE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		WitherSkull skull = event.getPlayer().launchProjectile(WitherSkull.class);
		skull.getVelocity().multiply(2);
		return true;
	}

}
