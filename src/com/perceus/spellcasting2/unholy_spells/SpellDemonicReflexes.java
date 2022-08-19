package com.perceus.spellcasting2.unholy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellDemonicReflexes extends BaseSpellCapsule
{

	public SpellDemonicReflexes()
	{
		super(Material.LIGHT_GRAY_DYE, "§r§7§ko§r§7§lSpell: §r§fDemonic Reflexes§r§7§ko§r", "SpellDemonicReflexes", 50, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fBoost caster's ability to vertically scale objects.","§r§fAlso slightly boosts movement speed.","§r§fDuration: 20 seconds.","§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
		 SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
		 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 2));
		 event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
			
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			}
		}.runTaskLater(Eden.getInstance(), 405);
		return true;
	}

}
