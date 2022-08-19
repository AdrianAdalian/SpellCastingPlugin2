package com.perceus.spellcasting2.geo_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellBoulder extends BaseSpellCapsule
{

	public SpellBoulder()
	{
		super(Material.BROWN_DYE, "§r§7§ko§r§7§lSpell: §r§fBoulder§r§7§ko§r", "SpellBoulder", 50, true, true, "§r§fElement: §r§6Geo§r§f.","§r§fHurl a large boulder that stuns any target upon impact.","§r§fStun for 5 seconds.","§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.LEFT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
		Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
		arrow.setPickupStatus(PickupStatus.DISALLOWED);
		arrow.setCritical(false);
		arrow.setKnockbackStrength(2);
		arrow.setDamage(0);	
		arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99), true);
		return true;
		
	}

}
