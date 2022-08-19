package com.perceus.spellcasting2.water_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellWaveWake extends BaseSpellCapsule
{

	public SpellWaveWake()
	{
		super(Material.LIGHT_BLUE_DYE, "§r§7§ko§r§7§lSpell: §r§fWave Wake§r§7§ko§r", "SpellWaveWake", 75, true, true, "§r§fElement: §r§9Water§r§f.","§r§fGreatly boosts the caster through water.","§r§fMana cost: 75 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().isSwimming() == false) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You are not actively swimming.");
			return false;
		}
		
		if (event.getPlayer().isSwimming() == true)
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(4.5));
			new BukkitRunnable()
			{
				 @Override
				  public void run()
				  {
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, SoundCategory.MASTER, 1, 1);
				  }
			}.runTaskLater(Eden.getInstance(), 15);
			return true;
		}
		return false;
	}

}
