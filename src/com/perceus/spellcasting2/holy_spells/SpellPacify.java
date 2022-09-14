package com.perceus.spellcasting2.holy_spells;

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
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellPacify extends BaseSpellCapsule
{

	public SpellPacify()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Pacify", "SpellPacify", 0, false, "§r§fElement: §r§f§o§lHoly§r§f.",
				"§r§fSpell Type: §7Debuff§f and §bUtility§f §dAOE§f.", 
				"§r§fAn incantation capable of disabling target", 
				"§r§fagro or crippling player targets.",
				"§r§fIf target is a player, afflict Cripple.",
				"§r§fDuration: 20 seconds. Mana cost: 100 §r§9mana§r§f.",
				"§r§fIf the target is a mob, disable its agro.",
				"§r§fRange: 35 meters. Mana cost: 50 §9mana§f.",
				"§r§fIf there is no target,", 
				"§r§fAOE of both effects above applied.",
				"§r§fDuration: 20 seconds. Range: 35 meters.", 
				"§r§fMana cost: 100 §9mana§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		Entity target = getNearestPlayerInSight(event.getPlayer(), 35);
		
		if (target == null) 
		{
			if (event.getPlayer().getNearbyEntities(35, 35, 35).size() == 0)
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			for (Entity target2 : event.getPlayer().getNearbyEntities(35, 35, 35))
			{
				SpellParticles.drawLine(event.getPlayer().getLocation(), target2.getLocation(), 1, Particle.CLOUD, null);
				SpellParticles.drawDisc(target2.getLocation(), 1, 1, 20, Particle.CLOUD, null);
				if (target2 instanceof Mob) 
				{	
					((Mob) target2).setTarget(null);
				}
				if (target2 instanceof Player) 
				{					
					((Player) target2).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 99, true));
				}
			}
			return true;
		}
		
		if (target instanceof Player) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			((Player) target).playSound(((Player)target).getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 99, true));
			return true;
		}
		
		if (target instanceof Mob)
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 50);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CLOUD, null);
			((Mob) target).setTarget(null);
			return true;
		}
		
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}
	private Entity getNearestPlayerInSight(Player player, int range) 
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
