package com.perceus.spellcasting2.spellitem_spell;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;

public class Armament_War extends BaseSpellCapsule
{

	public Armament_War()
	{
		super(Material.NETHERITE_AXE, ChatUtils.of("Armament: War", "FFE748","FFFFFF","§l§o"), "Armament_War", 0, false, "§r§fElement: §r§6§lConstruct§r§f.",
				"§r§fA Netherite Axe infused with §dStorm§f energy.",
				"§r§fThis weapon has two unique abilities.",
				"§r§6Ability§r§f: Ion Beam.",
				"§r§fRight-Click Target:",
				"§r§fEmit an electrical discharge,", 
				"§r§fcripping and stunning the target for 5 seconds.",
				"§r§fRange: 15 meters.",
				"§r§fMana cost: 150 §r§9mana§f.",
				"§r§6Ability§r§f: Warcry.",
				"§r§fRight-Click Air:",
				"§r§fGrant Lv1 speed and haste for 30 seconds.",
				"§r§fMana cost: 100 §r§9mana§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 15);
			
			if (target==null) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.ELECTRIC_SPARK, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0, true));
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 0, true));
				return true;
			}
			if (target instanceof LivingEntity) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 150);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				target.getWorld().playSound((Location) target.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.MASTER, 1, 1);
				target.getWorld().strikeLightningEffect(target.getLocation());
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 99));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99));
				return true;
			}
		}
		
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
