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
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSolarBolt extends BaseSpellCapsule
{

	public SpellSolarBolt()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☀ Solar Tome: Solar Bolt ☀", "F9812B","FFFFFF",""), "SpellSolarBolt", 150, false, "§r§fElement: §r§6S§eo§6l§eα§6r§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.","§r§fSpell Type: §cOffensive§f.",
				"§r§fThe spell within grows stronger during the §6Day§f.",
				"§r§fDuring §r§7Nightfall§r§f:",
				"§r§fCast a weak bolt of §r§6S§eo§6l§eα§6r§r§f energy.",
				"§r§fDeals 2 hearts of §r§cdamage§r§f.",
				"§r§fBurn the target for 10 seconds.",
				"§r§fRange: 10 meters.",
				"§r§fWhile during the §r§6Day§r§f:",
				"§r§fCast out a concentrated bolt of §r§6S§eo§6l§eα§6r§r§f energy.",
				"§r§fDeals 5 hearts of §r§cdamage§r§f.",
				"§r§fBurn the the target for 25 seconds.",
				"§r§fRange: 20 meters.",
				"§r§fTargets will also suffer §r§6S§eo§6l§eα§6r§r§f sickness.",
				"§r§fMana cost: 150 §r§9mana§r§f.");
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

		if(time >= 0 && time <= 12000) //while time is day
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 20);
			
			if (target == null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			if (target instanceof LivingEntity) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.LAVA, null);
				((Damageable) target).damage(10);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 500, 4, true));
				target.setFireTicks(500);
			}
			
			return true;
		}
		
		if(time >=12005 && time <= 24000) //while time is night
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 10);
			
			if (target == null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			if (target instanceof LivingEntity) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.LAVA, null);
				((Damageable) target).damage(4);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 4, true));
				target.setFireTicks(200);
			}
			
			return true;
			
		}
		
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
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
