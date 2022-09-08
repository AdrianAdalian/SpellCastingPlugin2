package com.perceus.spellcasting2.unholy_spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellUndeadRecall extends BaseSpellCapsule
{

	public SpellUndeadRecall()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Undead Recall", "SpellUndeadRecall", 250, false, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA spelltome with an incantation within that allows","§r§fthe caster to recall to the last point of death.","§r§fMana cost: 250 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			event.setCancelled(true);
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getStatistic(Statistic.DEATHS) >= 1) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.SMOKE_LARGE, null);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.SMOKE_LARGE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
				}
			}.runTaskLater(Eden.getInstance(), 5);
			Location lastdeath = event.getPlayer().getLastDeathLocation();
			event.getPlayer().teleport(lastdeath);
			return true;
			//if a player has died, find their last death and "recall"/teleport to it.
		}
		//if no deaths are on record, stop the code.
		PrintUtils.sendMessage(event.getPlayer(),"You haven't died yet.");
		return false;
	}

}
