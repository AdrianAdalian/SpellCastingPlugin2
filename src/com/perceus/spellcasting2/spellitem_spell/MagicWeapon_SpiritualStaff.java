package com.perceus.spellcasting2.spellitem_spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicWeapon_SpiritualStaff extends BaseSpellCapsule
{

	public MagicWeapon_SpiritualStaff()
	{
		super(Material.STICK, "§r§f§lMagic Weapon §r§f: Sp§3ir§bit§cua§4l §fStaff", "MagicWeapon_SpiritualStaff", 0, true, "§r§fA magical weapon containing various elements.",
				"§r§fLeft-Click to cycle between different spells.",
				"§r§fRight-Click to cast each spell.",
				"§r§fElement: §r§f§o§lHoly§r§f. §7Spell§f: Empower. Spell Type: §aSupport§f §6Buff§f.","§r§fGrant target player speed, strength, and haste for 30 seconds.","§r§fRange: 30 meters. §r§fMana cost: 100 §9mana§f.",
				"§r§fElement: §r§4§o§lUnholy§r§f. §7Spell§f: Exhaust. Spell Type: §7Debuff§f.","§r§fExhaust target player with slow, weakness, and fatigue for 30 seconds.","§r§fRange: 30 meters. §r§fMana cost: 150 §9mana§f.",
				"§r§fElement: §r§3§lVOID§r§f. §7Spell§f: Void Surge. Spell Type: §6Buff§f.","§r§fAdd a random positive buff to caster for 30 seconds.","§r§fMana cost: 250 §9mana§f.");
	}
	
	private static Map<Player,Integer> intz = new HashMap<>();

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
			if (!intz.containsKey(event.getPlayer())) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §r§3§lVOID§r§f.");
				intz.put(event.getPlayer(), 1);
				return true;
			}
			
			if (intz.containsValue(1))
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §r§f§o§lHoly§r§f.");
				intz.put(event.getPlayer(), 2);
				return true;
			}
			
			if (intz.containsValue(2))
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §r§4§o§lUnholy§r§f.");
				intz.put(event.getPlayer(), 3);
				return true;
			}
			
			if (intz.containsValue(3))
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §r§3§lVOID§r§f.");
				intz.put(event.getPlayer(), 1);
				return true;
			}
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			if (intz.containsValue(2)) 
			{
				//holy spell
				Entity target = getNearestEntityInSight(event.getPlayer(), 30);
				
				if (target == null) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false; 
				}
				
				if (!(target instanceof Player)) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false; 
				}
				
				if (target instanceof Player) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 100);
					ManaInterface.updateScoreBoard(event.getPlayer());
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
				    SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
					((Player) target).playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 1));
				}
				
				return true;
			}
			
			if (intz.containsValue(3)) 
			{
				//unholy spell
				Entity target = getNearestEntityInSight(event.getPlayer(), 30);
				
				if (target == null) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false; 
				}
				
				if (!(target instanceof Player)) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false; 
				}
				
				if (target instanceof Player) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 100);
					ManaInterface.updateScoreBoard(event.getPlayer());
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
				    SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.SMOKE_LARGE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
					((Player) target).playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 1));
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 1));
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 600, 1));
				}
				
				return true;
			}
			
			if (intz.containsValue(1)) 
			{
				//void spell
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 250);
				ManaInterface.updateScoreBoard(event.getPlayer());
				List<PotionEffectType> posEffects = List.of(
						PotionEffectType.ABSORPTION,
						PotionEffectType.CONDUIT_POWER,
						PotionEffectType.DAMAGE_RESISTANCE,
						PotionEffectType.DOLPHINS_GRACE,
						PotionEffectType.FAST_DIGGING,
						PotionEffectType.FIRE_RESISTANCE,
						PotionEffectType.HEALTH_BOOST,
						PotionEffectType.HERO_OF_THE_VILLAGE,
						PotionEffectType.INCREASE_DAMAGE,
						PotionEffectType.JUMP,
						PotionEffectType.LUCK,
						PotionEffectType.NIGHT_VISION,
						PotionEffectType.REGENERATION,
						PotionEffectType.SATURATION,
						PotionEffectType.SPEED,
						PotionEffectType.WATER_BREATHING);
				
				Random random = new Random();
				PotionEffectType randomPotionEffect = PotionEffectType.values()[random.nextInt(PotionEffectType.values().length)];
				
				while (!(posEffects.contains((PotionEffectType)randomPotionEffect))) 
				{
					randomPotionEffect = PotionEffectType.values()[random.nextInt(PotionEffectType.values().length)];
				}
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
				event.getPlayer().addPotionEffect(new PotionEffect(randomPotionEffect, 600, 0, true));
				return true;
			}
		}
		
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
