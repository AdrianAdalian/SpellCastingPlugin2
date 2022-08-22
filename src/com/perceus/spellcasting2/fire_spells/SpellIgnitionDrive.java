package com.perceus.spellcasting2.fire_spells;

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

public class SpellIgnitionDrive extends BaseSpellCapsule
{

	public SpellIgnitionDrive()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fIgnition Drive§r§7§ko§r", "SpellIgnitionDrive", 1000, true, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §6Buff§f.","§r§fVastly overclocks caster's offensive capabilities","§r§fgranting a 300% §r§cdamage§r§f increase.","§r§fDue to the effects of this spell,", "§r§fthe caster goes into temporary", "§r§fshock after effects have ended.","§r§fDuration: 20 seconds.","§r§fShock duration: 60 seconds.","§r§fMana cost: 1000 §r§9mana§r§f.");
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
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 9));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 400, 4));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1200, 9));
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1200, 9));
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 4));
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			}
		}.runTaskLater(Eden.getInstance(), 405);	 
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			}
		}.runTaskLater(Eden.getInstance(), 1610);	
		return true; 
	}

}
