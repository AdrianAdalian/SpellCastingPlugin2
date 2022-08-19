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

public class SpellLightShift extends BaseSpellCapsule
{

	public SpellLightShift()
	{
		super(Material.LIGHT_GRAY_DYE, "§r§7§ko§r§7§lSpell: §r§fLight Shift§r§7§ko§r", "SpellLightShift", 75, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fCloaks the caster in §r§4§o§lUnholy§r§f energy,","§r§fshifting light around them making them less easily seen.","§r§fDuration: 30 seconds.","§r§fMana cost: 75 §r§9mana§r§f.");
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
		
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0));
			
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
