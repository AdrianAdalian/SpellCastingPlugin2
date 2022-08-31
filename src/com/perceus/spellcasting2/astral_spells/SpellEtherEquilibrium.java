package com.perceus.spellcasting2.astral_spells;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellEtherEquilibrium extends BaseSpellCapsule
{

	public SpellEtherEquilibrium()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Ether Equilibrium ✩", "8B008B","FFFFFF",""), "SpellEtherEquilibrium", 150, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §aSupport§f or §cOffensive§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis tome swaps the mana value of target player.", 
				"§r§fSet player target mana equal to",
				"§r§fcaster current mana and vice versa.",  
				"§r§fRange: 20 meters.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Illegal Argument: This spell cannot be cast in creative mode.");
			return false;
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 20);
		
		if(target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (!(target instanceof Player)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		int casterMana = PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana();
		int targetMana = PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana();
		
		PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(targetMana);
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		PrintUtils.sendMessage(event.getPlayer(),"Mana has been swapped with " + ((Player) target).getDisplayName() + ".");
		
		PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).setCurrentMana(casterMana);
		if (PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMaxMana()) 
		{
			PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMaxMana());
		}
		ManaInterface.updateScoreBoard((Player) target);
		PrintUtils.sendMessage(((Player) target),"Mana has been swapped with " + event.getPlayer().getDisplayName() + ".");
		
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
