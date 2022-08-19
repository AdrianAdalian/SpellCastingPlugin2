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

public class SpellTailWind extends BaseSpellCapsule
{

	public SpellTailWind()
	{
		super(Material.STRING, "§r§7§ko§r§7§lSpell: §r§fTailwind§r§7§ko§r", "SpellTailWind", 25, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fThe caster creates a current of wind behind them","§r§fwhich slightly increases their movement speed.","§r§fDuration: 15 seconds.","§r§fMana cost: 25 §r§9mana§r§f.");
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
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 0));
		new BukkitRunnable()
		{
		  @Override
		  public void run()
		  {
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
		  }
		}.runTaskLater(Eden.getInstance(), 305);
		return true;
	}

}
