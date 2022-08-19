package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Location;
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

public class SpellLivingRecall extends BaseSpellCapsule
{

	public SpellLivingRecall()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Recall§r§f§ko§r", "SpellLivingRecall", 100, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fA spelltome with an incantation within that allows","§r§fthe caster to recall to their spawnpoint.","§r§fMana cost: 100 §r§9mana§r§f.");
		// TODO Auto-generated constructor stub
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
		
		if (event.getPlayer().getBedSpawnLocation() == null) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.CLOUD, null);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.CLOUD, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
				}
			}.runTaskLater(Eden.getInstance(), 5);
			Location worldspawn = event.getPlayer().getWorld().getSpawnLocation();
			event.getPlayer().teleport(worldspawn);
			return true;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.CLOUD, null);
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 50, Particle.CLOUD, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
			}
		}.runTaskLater(Eden.getInstance(), 5);
		Location spawnpoint = event.getPlayer().getBedSpawnLocation();
		event.getPlayer().teleport(spawnpoint);
		return true;
	}

}
