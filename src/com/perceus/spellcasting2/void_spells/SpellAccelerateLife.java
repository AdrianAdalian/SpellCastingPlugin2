package com.perceus.spellcasting2.void_spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
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
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellAccelerateLife extends BaseSpellCapsule
{

	public SpellAccelerateLife()
	{
		super(Material.CYAN_DYE, "§r§7§ko§r§7§lSpell: §r§fAccelerate Life§r§7§ko§r", "SpellAccelerateLife", 0, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fA spell radiating unstable §r§3§lVOID§r§f energy.","§r§fAccelerates all life at the target's source.","§r§fRange for vegitative acceleration: 5 meters.", "§r§fMana cost: 25 §r§9mana§r§f.","§r§fRange for living creature acceleration: 15 meters.","§r§fMana cost: 750 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if(event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		int TARGETRANGE = 5 ;
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			if (target.getBlockData() != null)
			{
				
				if(!(target.getBlockData() instanceof Ageable))
				{
					PrintUtils.sendMessage(event.getPlayer(),"Block target is not Ageable.");
					return false;
				}
				
				if (target.getBlockData() instanceof Ageable) // org.bukkit.blockdata.Ageable, not org.bukkit.entity.Ageable
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 25);
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
					{
						PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
						PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
						return false;
					}
					
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BONE_MEAL_USE, SoundCategory.MASTER, 1, 1);
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
					Ageable data = (Ageable) target.getBlockData();
					data.getMaximumAge(); 
					data.setAge(data.getMaximumAge());
					target.setBlockData(data);
					
					return true;
				}
			}	
		}
		
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			Entity target2 = getNearestEntityInSight(event.getPlayer(), 15);
			
			if (target2==null) 
			{
				PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
				return false;
			}
			
			if (target2 instanceof LivingEntity) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 750);
				if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
					PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
					return false;
				}
				
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
				((LivingEntity) target2).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 12000, 2));
				
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
