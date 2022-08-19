package com.perceus.spellcasting2.darkmagic_spells;

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

public class SpellLawOfProgression extends BaseSpellCapsule
{

	public SpellLawOfProgression()
	{
		super(Material.ENCHANTED_BOOK, "§r§7§ko§r§7§lForbidden Spell: §r§fLaw of Progression§r§7§ko§r", "SpellLawOfProgression", 0, false, "§r§fElement: §r§4Dark Magic§r§f.", "§r§fA forbidden tome containing an incantation to", "§r§fgrant every possible positive effect of magic.","§r§fDuration: 1 minute.","§r§fAfter the effects have concluded,","§r§fevery negative defect of magic will be applied.","§r§fDuration: 1 minute.","§r§fSome positive/negative effects are excluded.", "§r§fThis spell by-passes absorption.","§r§4Blood Sacrifice§r§f: 9 hearts.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
		{
			event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
		}
		
		event.getPlayer().damage(18);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);

		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1200, 0, true));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 0, true));

		
		new BukkitRunnable()
		{
		  @Override
		  public void run()
		  {
			  event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 1200, 0, true));
			  event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1200, 0, true));
		  }
		}.runTaskLater(Eden.getInstance(), 1205);
		
		return true;
	}

}
