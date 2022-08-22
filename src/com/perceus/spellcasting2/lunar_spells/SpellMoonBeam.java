package com.perceus.spellcasting2.lunar_spells;

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

public class SpellMoonBeam extends BaseSpellCapsule
{

	public SpellMoonBeam()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☽ Lunar Tome: Moon Beam ☽", "30D5C8","FFFFFF",""), "SpellMoonBeam", 1000, false, "§r§fElement: §r§f§o§l§bL§3u§bn§3α§br§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.","§r§fSpell Type: §cOffensive§f.",
				"§r§fThe spell within grows stronger during §7Nightfall§f.",
				"§r§fDuring the §r§6Day§r§f:",
				"§r§fCast down a weak beam of §r§f§o§l§bL§3u§bn§3a§br§r§f energy.",
				"§r§fDeals 5 hearts of §r§cdamage§r§f.",
				"§r§fStun the target for 10 seconds.",
				"§r§fRange: 25 meters.",
				"§r§fWhile during §r§7Nightfall§r§f:",
				"§r§fCast down a concentrated beam of §r§f§o§l§bL§3u§bn§3a§br§r§f energy.",
				"§r§fDeals 15 hearts of §r§cdamage§r§f.",
				"§r§fDebilitate the the target for 15 seconds.",
				"§r§fRange: 50 meters.",
				"§r§fTargets will also suffer §r§f§o§l§bL§3u§bn§3a§br§r§f sickness.",
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
		
		long time = Bukkit.getWorlds().get(0).getTime();

		if(time >= 0 && time <= 12000) //while time is day
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 25);
			
			if (target == null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			if (target instanceof LivingEntity) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.GLOW_SQUID_INK, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.GLOW_SQUID_INK, null);
				target.getWorld().strikeLightningEffect(target.getLocation());
				((Damageable) target).damage(10);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0, true));
			}
			
			return true;
		}
		
		if(time >=12005 && time <= 24000) //while time is night
		{
			
			Entity target = getNearestEntityInSight(event.getPlayer(), 50);
			
			if (target == null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			if (target instanceof LivingEntity) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.GLOW_SQUID_INK, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.GLOW_SQUID_INK, null);
				target.getWorld().strikeLightningEffect(target.getLocation());
				((Damageable) target).damage(30);
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 99, true));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 99, true));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 99, true));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0, true));
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
