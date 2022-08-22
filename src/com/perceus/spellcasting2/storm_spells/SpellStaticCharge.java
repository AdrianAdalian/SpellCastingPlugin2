package com.perceus.spellcasting2.storm_spells;

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

public class SpellStaticCharge extends BaseSpellCapsule
{

	public SpellStaticCharge()
	{
		super(Material.PURPLE_DYE, "§r§7§ko§r§7§lSpell: §r§fStatic Charge§r§7§ko§r", "SpellStaticCharge", 50, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §6Buff§f.","§r§fUsing §r§dStorm§r§f energy, the caster","§r§fundergoes a static charge which","§r§fmoderately increases movement and interact speed.","§r§fDuration: 30 seconds.","§r§fMana cost: 50 §r§9mana§r§f.");
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
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 0));
		
		new BukkitRunnable()
		{
		  @Override
		  public void run()
		  {
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
		  }
		}.runTaskLater(Eden.getInstance(), 605);
		return true;
	}

}
