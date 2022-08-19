package com.perceus.spellcasting2.solar_spells;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellComet extends BaseSpellCapsule
{

	public SpellComet()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☀ Solar Tome: Comet ☀", "F9812B","FFFFFF",""), "SpellComet", 0, true, 
				"§r§fElement: §r§6S§eo§6l§eα§6r§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThe spell within grows stronger during the §6Day§f.",
				"§r§fDuring the §6Day§f:",
				"§r§fSummon a large comet in the air.",
				"§r§fIt will then decend upon the target.",
				"§r§fDeals major §r§cdamage§r§f and explodes on impact.",
				"§r§fRange: 50 meters.",
				"§r§fMana cost: 1000 §r§9mana§r§f.",
				"§r§fDuring §r§7Nightfall:",
				"§r§fSummon a small comet in the air.",
				"§r§fIt will then decend upon target block.",
				"§r§fCreates a minor explosion on impact.",
				"§r§fRange: 25 meters.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
		
		// TODO Auto-generated constructor stub
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
		
		if(time >= 0 && time <= 12000)
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 1000);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			Entity target = getNearestEntityInSight(event.getPlayer(), 50);
			
			Vector v = new Vector(0,-5,0);
			
			if (target == null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			
			Location newlocation = target.getLocation().add(new Location(event.getPlayer().getWorld(), 0, 25, 0)) ;
			newlocation.add(0, 25, 0);
			
			LargeFireball fireball = target.getWorld().spawn(newlocation, LargeFireball.class);
			fireball.setYield(35);
			fireball.setShooter(event.getPlayer());
			fireball.setDirection(v);
			return true; 
		}
		
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 100);
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
			PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
			return false;
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		
		Block target = event.getPlayer().getTargetBlock(null, 25);
		
		if (target == null)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		
		Vector v = new Vector(0,-5,0);
		Location newlocation = target.getLocation().add(new Location(event.getPlayer().getWorld(), 0, 25, 0));
		newlocation.add(0, 25, 0);
		
		LargeFireball fireball = target.getWorld().spawn(newlocation, LargeFireball.class);
		fireball.setYield(5);
		fireball.setShooter(event.getPlayer());
		fireball.setDirection(v);
		
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
