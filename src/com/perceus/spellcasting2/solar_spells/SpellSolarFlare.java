package com.perceus.spellcasting2.solar_spells;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSolarFlare extends BaseSpellCapsule
{

	public SpellSolarFlare()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☀ Solar Tome: Solar Flare ☀", "F9812B","FFFFFF",""), "SpellSolarFlare", 0, true, 
				"§r§fElement: §r§6S§eo§6l§eα§6r§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThe spell within grows stronger during the §6Day§f.",
				"§r§fDuring the §6Day§f:",
				"§r§fSummon a congealed ball of §r§6S§eo§6l§eα§6r§r§f energy.",
				"§r§fIt will cause a massive explosion upon collision.",
				"§r§fMana cost: 1000 §r§9mana§r§f.",
				"§r§fDuring §7Nightfall§f:",
				"§r§fBurn the target with §r§6S§eo§6l§eα§6r§r§f flames.",
				"§r§fTarget will continuously be burned for 1 minute.",
				"§r§fRange: 20 meters.",
				"§r§fMana cost: 200 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		long time = Bukkit.getWorlds().get(0).getTime();
		long time2 = System.currentTimeMillis();
		
		if(time >= 0 && time <= 12000)
		{		
			
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 1000);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 

			Location loc = event.getPlayer().getEyeLocation().toVector().add(event.getPlayer().getLocation().getDirection().multiply(2)).toLocation(event.getPlayer().getWorld(), event.getPlayer().getLocation().getYaw(), event.getPlayer().getLocation().getPitch());
			LargeFireball fireball = event.getPlayer().getWorld().spawn(loc, LargeFireball.class);
			fireball.setShooter(event.getPlayer());
			fireball.setVelocity(new Vector(0,0,0));
			fireball.setYield(40);
			return true;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 10);
		
		if (target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (target instanceof Player) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 200);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
			PrintUtils.sendMessage((Player) target,"A solar flare boils your flesh.");
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.LAVA, null);

			new BukkitRunnable() 
			{
				@Override
				public void run() 
				{		
					if (System.currentTimeMillis()-time2 >= 60000)
					{
						PrintUtils.sendMessage((Player) target,"You have cooled down.");
						this.cancel();
						return;
					} 
					
					((LivingEntity) target).setFireTicks(30);
					
				}
			}.runTaskTimer(Eden.getInstance(), 0, 20);
			return true;			
		}
		
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 200);
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
			PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
			return false;
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.LAVA, null);

		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{		
				if (System.currentTimeMillis()-time2 >= 60000)
				{
					this.cancel();
					return;
				} 
				
				((LivingEntity) target).setFireTicks(30);
				
			}
		}.runTaskTimer(Eden.getInstance(), 0, 20);
		return true;	
		
	}
	private Entity getNearestEntityInSight(Player player, int range) 
	{
		
	    ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
	    ArrayList<Block> sightBlock = null;
	    try 
	    {
	        sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
	    } catch (IllegalStateException error) 
	    {
	        return null;
	    }
	     
	    ArrayList<Location> sight = new ArrayList<Location>();
	    for (int i = 0;i<sightBlock.size();i++)
	        sight.add(sightBlock.get(i).getLocation());
	    for (int i = 0;i<sight.size();i++) {
	        for (int k = 0;k<entities.size();k++) {
	            if (entities.get(k) instanceof LivingEntity && !(entities.get(k) instanceof ArmorStand)) {
	                if (Math.abs(entities.get(k).getLocation().getX()-sight.get(i).getX())<1.3) {
	                    if (Math.abs(entities.get(k).getLocation().getY()-sight.get(i).getY())<1.5) {
	                        if (Math.abs(entities.get(k).getLocation().getZ()-sight.get(i).getZ())<1.3) {
	                                
	                        	return entities.get(k);
	                        	
	                        }
	                    }
	                }
	            }
	        }
	    }
	    	return null; // Return null if no entity was found
	}
}
