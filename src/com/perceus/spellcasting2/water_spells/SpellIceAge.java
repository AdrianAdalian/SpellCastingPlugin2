package com.perceus.spellcasting2.water_spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellIceAge extends BaseSpellCapsule
{

	public SpellIceAge()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fIce Age§r§f§ko§r", "SpellIceAge", 1000, false, "§r§fElement: §r§9Water§r§f.","§r§fSpell Type: §bUtility§f and §cOffensive§f §dAOE§f.","§r§fAn unstable incantation is written within this tome","§r§fthat allows the caster to summon an intense ice storm.", "§r§fOn Right-Click: Temporarily freeze nearby water.", "§r§fOn Left-Click: The storm wreaks havoc on all within range.", "§r§fTargets take 1/2 heart of §r§cdamage§r§f/s for 20 seconds.","§r§fFrozen duration: 20 seconds.","§r§fRange to freeze water: 100 meters.","§r§fRange to freeze targets: 20 meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			int radius = 100; 
			int radius2 = 1;
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			for (int iy = (radius2 * -1); iy < (radius2 * 1); iy++)
			{
				for (int ix = (radius * -1); ix < (radius * 1); ix++)
				{
					for (int iz = (radius * -1); iz < (radius * 1); iz++)
					{
						
						Block target1 = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().add(new Location(event.getPlayer().getWorld(), ix, iy, iz)));
							
						if (target1.getType().equals(Material.WATER))
						{
							target1.setType(Material.FROSTED_ICE);
						}	
					}
				}
			}
			return true;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			if (event.getPlayer().getNearbyEntities(20, 20, 20).size() == 0)
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			
			for (Entity target : event.getPlayer().getNearbyEntities(20, 20, 20)) 
			{
				if (!(target instanceof LivingEntity)) 
				{
					continue;
				}
				
				if (target instanceof LivingEntity) 
				{
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRIP_WATER, null);
					((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 99));
					((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 99));
				}
				
				long time = System.currentTimeMillis();
				
				new BukkitRunnable() 
				{
					@Override
					public void run() 
					{		
						if (System.currentTimeMillis()-time >= 20000)
						{
							this.cancel();
							return;
						} 
						
						((LivingEntity) target).damage(1);
						
					}
				}.runTaskTimer(Eden.getInstance(), 0, 20);
			}
			return true;
		}
		
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}
}
