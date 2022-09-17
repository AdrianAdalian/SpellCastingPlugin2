package com.perceus.spellcasting2.fusion_spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellFusionFlare extends BaseSpellCapsule
{

	public SpellFusionFlare()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("Fusion Tome: Blue Flare", "FF0000","1B7CED",""), "SpellFusionFlare", 0, false, "§r§fElement: §cFire§f and §9Water§f.",
				"§r§fLeft-Click to cycle spells.",
				"§r§7§lSpell§r§f: Chaos Embers",
				"§r§fSpell Type: §cOffensive§f.",
				"§r§fLaunch chaos embers at the target.",
				"§r§fMana Cost: 100 §9mana§f.",
				"§r§7§lSpell§r§f: Ice Spike",
				"§r§fSpell Type: §cOffensive§f and O§7Debuff§f.",
				"§r§fFire a sharp icicle that inflicts Slow (20s).",
				"§r§fMana Cost: 100 §9mana§f.",
				"§r§9Unique§f §r§7§lSpell§r§f: Fusion Flare",
				"§r§fFire a concentrated blast of §cFire and §9Water energy.",
				"§r§fBurns the target for 10 seconds.",
				"§r§fSlow (10s) and then cause an explosion.",
				"§r§fRange: 20 meters. Mana Cost: 200 §9mana§f.");
	}

	private static Map<UUID,Integer> intzFusionFlare = new HashMap<>();
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			if (!intzFusionFlare.containsKey(event.getPlayer().getUniqueId()))
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §cFire§f.");
				intzFusionFlare.put(event.getPlayer().getUniqueId(), 1);
				return true;
			}
			
			if (intzFusionFlare.get(event.getPlayer().getUniqueId()) == 1)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §9Water§f.");
				intzFusionFlare.put(event.getPlayer().getUniqueId(), 2);
				return true;
			}
			
			if (intzFusionFlare.get(event.getPlayer().getUniqueId()) == 2)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fFusion: §cFire§f and §9Water§f.");
				intzFusionFlare.put(event.getPlayer().getUniqueId(), 3);
				return true;
			}
			
			if (intzFusionFlare.get(event.getPlayer().getUniqueId()) == 3)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §cFire§f.");
				intzFusionFlare.put(event.getPlayer().getUniqueId(), 1);
				return true;
			}
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			if (!intzFusionFlare.containsKey(event.getPlayer().getUniqueId())) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fNo spell has been selected.");
				return false;
			}
			
			if (intzFusionFlare.get(event.getPlayer().getUniqueId()) == 1)
			{
				//fire spell
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
				SmallFireball fb = event.getPlayer().launchProjectile(SmallFireball.class);
				fb.setIsIncendiary(true);
				fb.setYield(5);
				fb.setFireTicks(600);
				fb.setGlowing(true);
				fb.setVisualFire(false);
				return true;
			}
			
			if (intzFusionFlare.get(event.getPlayer().getUniqueId()) == 2)
			{
				//water spell
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
				ManaInterface.updateScoreBoard(event.getPlayer());
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SNOWBALL_THROW, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
				Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				arrow.setCritical(false);
				arrow.setKnockbackStrength(0);
				arrow.setDamage(4);
				arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 400, 2), true);
				new BukkitRunnable()
				{
					@Override
					public void run()
					{
						arrow.remove();
					}
				}.runTaskLater(Eden.getInstance(), 100);
				return true;
			}
			
			if (intzFusionFlare.get(event.getPlayer().getUniqueId()) == 3) 
			{
				//fusion spell
				Entity target = getNearestEntityInSight(event.getPlayer(), 20);
				
				if (target == null) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false; 
				}
				
				if (!(target instanceof LivingEntity)) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false; 
				}
				
				if (target instanceof LivingEntity) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 200);
					ManaInterface.updateScoreBoard(event.getPlayer());
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.WATER_DROP, null);
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRIP_WATER, null);
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FLAME, null);
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.FLAME, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
					((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 3));
					((LivingEntity) target).setFireTicks(200);
					new BukkitRunnable()
					{
						@Override
						public void run()
						{							
							((LivingEntity) target).getWorld().createExplosion(target.getLocation(), 10);
						}
					}.runTaskLater(Eden.getInstance(), 200);
					
				}
				
				return true;
			}
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
