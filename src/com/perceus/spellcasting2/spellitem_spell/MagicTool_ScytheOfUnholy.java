package com.perceus.spellcasting2.spellitem_spell;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class MagicTool_ScytheOfUnholy extends BaseSpellCapsule
{

	public MagicTool_ScytheOfUnholy()
	{
		super(Material.NETHERITE_HOE, "§r§f§lMagic Tool§r§f: Netherite Hoe§e+", "MagicTool_ScytheOfUnholy", 0, false, "§r§fElement: §r§6§lConstruct§r§f.",
				"§r§fA Netherite Hoe infused with §r§4§o§lUnholy§r§f energy.",
				"§r§6Ability§f: Farmer's Rage.",
				"§r§fOn Right-Click Air:",
				"§r§fDeal 5 hearts of §cdamage§f to target. §4Drain§f 2 hearts.",
				"§r§fUndead targets are immune.",
				"§r§fRange: 15 meters.",
				"§r§fMana cost: 100 §r§9mana§r§f.",
				"§r§6Ability§f: Farmer's Harvest.",
				"§r§fOn Right-Click Block:",
				"§r§fIf the target is a fully grown crop,",
				"§r§fbreak it and others applicable around it.",
				"§r§fInitial range: 5 meters. Range of harvest: 30 meters.",
				"§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 10);
			
			List<EntityType> entity = List.of(EntityType.SKELETON,
					EntityType.ZOMBIE,
					EntityType.DROWNED,
					EntityType.STRAY,
					EntityType.HUSK,
					EntityType.ZOMBIE_VILLAGER,
					EntityType.ZOMBIFIED_PIGLIN,
					EntityType.WITHER_SKELETON,
					EntityType.VEX,
					EntityType.ZOMBIE_HORSE,
					EntityType.SKELETON_HORSE,
					EntityType.ZOGLIN,
					EntityType.WARDEN,
					EntityType.WITHER);
			
			if (target==null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target");
				return false;
			}
			
			if (entity.contains(target.getType())) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target");
				return false;	
			}
			
			if (target instanceof Damageable)
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 100);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
				
				((Damageable) target).damage(10, event.getPlayer());
				try 
				{
				event.getPlayer().setHealth(event.getPlayer().getHealth()+4);
				}
				catch(IllegalArgumentException e)
				{
					event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				}
				return true;
			}
			return false;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			Block target = event.getPlayer().getTargetBlock(null, 5) ;
			
			if (!(target.getBlockData() instanceof Ageable)) 
			{
				return true;
			}
			
			if (target.getBlockData() instanceof Ageable) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 50);
				ManaInterface.updateScoreBoard(event.getPlayer());
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.CLOUD, null);
				int radius = 30; 
				for (int iy = (radius * -1); iy < (radius * 2); iy++)
				{
					for (int ix = (radius * -1); ix < (radius * 2); ix++)
					{
						for (int iz = (radius * -1); iz < (radius * 2); iz++)
						{
							
							Block target1 = target.getWorld().getBlockAt(target.getLocation().add(new Location(target.getWorld(), ix, iy, iz)));
							
							if (target1.getBlockData() != null)
							{
								if (target1.getBlockData() instanceof Ageable) // org.bukkit.blockdata.Ageable, not org.bukkit.entity.Ageable
								{
									
									Ageable data = (Ageable) target1.getBlockData();
//									data.getMaximumAge(); 
//									data.setAge(data.getMaximumAge());
//									target1.setBlockData(data);
									
									if (data.getAge() == data.getMaximumAge())
									{
										SpellParticles.drawLine(event.getPlayer().getLocation(), target1.getLocation(), 1, Particle.CLOUD, null);
										target1.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, target1.getLocation().add(new Location(target1.getWorld(), 0,1,0)), 1);
										target1.getWorld().playSound(target1.getLocation(), Sound.BLOCK_CROP_BREAK, SoundCategory.MASTER, 1, 1);
										target1.breakNaturally();
									}	
								}
							}	
						}
					}
				}
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
