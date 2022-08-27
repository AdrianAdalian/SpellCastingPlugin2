package com.perceus.spellcasting2.solar_spells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellInferno extends BaseSpellCapsule
{

	public SpellInferno()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☀ Solar Tome: Inferno ☀", "F9812B","FFFFFF",""), "SpellInferno", 1000, false, "§r§fElement: §r§6S§eo§6l§eα§6r§r§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.","§r§fSpell Type: §cOffensive§f §dAOE§f.",
				"§r§fAn immesely powerful script within allows",
				"§r§fthe caster to to turn a part of", 
				"§r§fthe world into a desolate wasteland,", 
				"§r§fincreasing local temperature.",
				"§r§fAll within a 200 meter radius will be continuously burned.", 
				"§r§fAny stormy weather will immediately begin to clear.",
				"§r§fSpell Duration: 1 minute.",
				"§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(200, 200, 200).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_AMBIENT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
		
		boolean weather = Bukkit.getWorlds().get(0).isClearWeather();
		
		if(weather == false) 
		{
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.getWorlds().get(0).setStorm(false);
			Bukkit.getWorlds().get(0).setThundering(false);
			Bukkit.getWorlds().get(0).setClearWeatherDuration(12000);
		} 
		
		long time = System.currentTimeMillis();
		
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{		
				if(!event.getPlayer().isOnline()) 
				{
					this.cancel();
					return;
				}
				if (System.currentTimeMillis()-time >= 60000)
				{
					PrintUtils.sendMessage(event.getPlayer(),"The land has cooled down.");
					this.cancel();
					return;
				} 
				
				for (Entity target : event.getPlayer().getNearbyEntities(200, 200, 200)) 
				{
					if (!(target instanceof LivingEntity)) 
					{
						continue;
					}
					((LivingEntity) target).setFireTicks(30);
				}
			}
		}.runTaskTimer(Eden.getInstance(), 0, 20);
		
		return true;
	}
}
