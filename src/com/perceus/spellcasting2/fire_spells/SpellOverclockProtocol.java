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

public class SpellOverclockProtocol extends BaseSpellCapsule
{

	public SpellOverclockProtocol()
	{
		super(Material.RED_DYE, "§r§7§ko§r§7§lSpell: §r§fOverclock Protocol§r§7§ko§r", "SpellOverclockProtocol", 250, true, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §6Buff§f.","§r§fDrastically overclocks caster's offensive","§r§fperformance granting a 200% §r§cdamage§r§f increase.","§r§fAlso slightly boosts interact speed.", "§r§fWhen the overclock has subsided,", "§r§fa brief period of shock will overcome the caster.","§r§fEffect duration: 30 seconds.", "§r§fShock duration: 15 seconds.","§r§fMana cost: 250 §r§9mana§r§f.");
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
		
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 3));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 2));
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 3));
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 3));
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			}
		}.runTaskLater(Eden.getInstance(), 605);	 
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			}
		}.runTaskLater(Eden.getInstance(), 910);	
		return true; 
	}

}
