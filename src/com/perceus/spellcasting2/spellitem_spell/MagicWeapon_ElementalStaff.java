package com.perceus.spellcasting2.spellitem_spell;

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
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicWeapon_ElementalStaff extends BaseSpellCapsule
{

	public MagicWeapon_ElementalStaff()
	{
		super(Material.STICK, "§r§f§lMagic Weapon §r§f: §4E§cl§6e§em§ae§9n§bt§da§5l§f Staff", "MagicWeapon_ElementalStaff", 0, true, "§r§fA magical weapon containing various elements.",
				"§r§fLeft-Click to cycle between different spells.",
				"§r§fRight-Click to cast each spell.",
				
				"§r§fElement: §cFire§f. §7Spell§f: Fire Blast. Spell Type: §cOffensive§f.","§r§fIgnite the target for 5 seconds, and launch","§r§fa small ball of fire, exploding on impact.","§r§fRange: 15 meters. Mana Cost: 50 §9mana§f.",
				"§r§fElement: §9Water§f. §7Spell§f: Ice Shard. Spell Type: §cOffensive§f.","§r§fLaunch an icicle at the target. Stuns (5s) on impact.","§r§fDeals 2 hearts of §cdamage§f. §r§fMana cost: 50 §9mana§f.",
				"§r§fElement: §dStorm§f. §7Spell§f: Lightning Bolt. Spell Type: §cOffensive§f.","§r§fCast down a small bolt of lightning on target.","§r§fThe lightning is primed for an explosion","§r§fand the target is briefly crippled. §r§fDeals 2 1/2 hearts of §cdamage§f.","§r§fRange: 20 meters. §r§fMana cost: 100 §9mana§f.",
				"§r§fElement: §6Geo§f. §7Spell§f: Tremor. SpellType: §cOffensive§f §dAOE§f.", "§r§fAll within a 10 meter radius are stunned for 5 seconds.","§r§fDeals 3 hearts of §cdamage§f. §r§fMana cost: 100 §9mana§f.");
	}
	private static Map<UUID,Integer> intz = new HashMap<>();

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
			if (!intz.containsKey(event.getPlayer().getUniqueId()))
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §cFire§f.");
				intz.put(event.getPlayer().getUniqueId(), 1);
				return true;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 1)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §9Water§f.");
				intz.put(event.getPlayer().getUniqueId(), 2);
				return true;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 2)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §dStorm§f.");
				intz.put(event.getPlayer().getUniqueId(), 3);
				return true;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 3)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §6Geo§f.");
				intz.put(event.getPlayer().getUniqueId(), 4);
				return true;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 4)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fElement Selected: §cFire§f.");
				intz.put(event.getPlayer().getUniqueId(), 1);
				return true;
			}
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			
			if (!intz.containsKey(event.getPlayer().getUniqueId())) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fNo spell has been selected.");
				return false;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 1)
			{
				//fire spell
				LivingEntity target = (LivingEntity) getNearestEntityInSight(event.getPlayer(), 15);
				if (target == null)
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false;
				}
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 50);
				ManaInterface.updateScoreBoard(event.getPlayer());

				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.FLAME, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
				
				event.getPlayer().launchProjectile(Fireball.class);
				target.setFireTicks(100);
				return true;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 2)
			{
				//water spell
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 50);
				ManaInterface.updateScoreBoard(event.getPlayer());
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SNOWBALL_THROW, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
				Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				arrow.setCritical(false);
				arrow.setKnockbackStrength(0);
				arrow.setDamage(2);
				arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99), true);
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
			
			if (intz.get(event.getPlayer().getUniqueId()) == 3) 
			{
				//storm spell
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
					PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
					ManaInterface.updateScoreBoard(event.getPlayer());
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
					target.getWorld().playSound((Location) target.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.MASTER, 1, 1);
					target.getWorld().strikeLightningEffect(target.getLocation());
					((LivingEntity) target).damage(5);
					((LivingEntity) target).getWorld().createExplosion(target.getLocation(), 2);
					((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 99));
				}
				
				return true;
			}
			
			if (intz.get(event.getPlayer().getUniqueId()) == 4) 
			{
				//geo spell
				if (event.getPlayer().getNearbyEntities(10, 10, 10).size() == 0)
				{
					PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
					return false;
				}
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STONE_BREAK, SoundCategory.MASTER, 1, 1);
				for (Entity target : event.getPlayer().getNearbyEntities(10, 10, 10))
				{
					if (!(target instanceof LivingEntity)) 
					{
						continue;
					}
					if (target instanceof LivingEntity)
					{
						SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CAMPFIRE_COSY_SMOKE, null);
						((Damageable) target).damage(6, event.getPlayer());
						((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99));
					}
				}
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
