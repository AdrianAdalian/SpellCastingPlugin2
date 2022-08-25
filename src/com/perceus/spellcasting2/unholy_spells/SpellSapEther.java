package com.perceus.spellcasting2.unholy_spells;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSapEther extends BaseSpellCapsule
{

	public SpellSapEther()
	{
		super(Material.LIGHT_GRAY_DYE, "§r§7§ko§r§7§lSpell: §r§fSap Ether§r§7§ko§r", "SpellSapEther", 0, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fSpell Type: §cOffensive§f.",
				"§r§fRadiate a player target with §r§4§o§lUnholy§r§f energy,",
				"§r§fSapping their ether and thus",
				"§r§fdraining their §r§9mana§r§f in exchange",
				"§r§ffor half of the caster's current max health.",
				"§r§4Drain§r§f 500 §r§9mana§r§f from the target.",
				"§r§fRestore 500 §r§9mana§r§f to self.",
				"§r§fRange: 15 meters.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
		PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
		return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 15);
		
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
		
		if (event.getPlayer().getHealth() <= 1.0 ) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You don't have enough health to cast this spell.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana() == PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMinMana()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"That player doesn't have any mana left.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You're already at full mana.");
			return false;
		}
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
		event.getPlayer().setHealth(event.getPlayer().getHealth() / 2.0);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		((Player) target).playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
		PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana() - 500);
		if (PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana()<=PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMinMana()) 
		{
			PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMinMana());
		}
		ManaInterface.updateScoreBoard((Player) target);
		PrintUtils.sendMessage(((Player) target),"You're mana has been drained.");
		
		PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 500);
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		PrintUtils.sendMessage(event.getPlayer(),"Your mana has been restored.");
		
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
