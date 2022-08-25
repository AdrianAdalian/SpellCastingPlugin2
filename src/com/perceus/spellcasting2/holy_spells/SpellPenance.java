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
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellPenance extends BaseSpellCapsule
{

	public SpellPenance()
	{
		super(Material.NETHER_STAR, "§r§7§ko§r§7§lSpell: §r§fPenance§r§7§ko§r", "SpellPenance", 0, true, false,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §aSupport§f.",
				"§r§fIf the target of this spell is a Player:", 
				"§r§fExpend lifeforce at the benefit of the target's mana regen.", 
				"§r§fDeal §r§cdamage§r§f to self equal to current max health.", 
				"§r§fRange: 15 meters.",
				"§r§fRestore 750 §r§9mana§r§f to target.", 
				"§r§fIf there is no target of this spell:",
				"§r§fExpend life force at the benefit of the caster's mana regen.",
				"§r§fDeal §r§cdamage§r§f to self equal to current max health.",
				"§r§fRestore 450 §r§9mana§r§f to self.");
	}
	//event.getPlayer().setHealth(event.getPlayer().getHealth() / 2.0);
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 15);
		
		if (target == null) 
		{
			if (event.getPlayer().getHealth() <= 1.0 ) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Not Enough Health.");
				return false;
			}
			
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() == PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"You're already at full Mana.");
				return false;
			}
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().setHealth(event.getPlayer().getHealth() / 2.0);
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 450);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			return true;
		}
		
		if (!(target instanceof Player)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (event.getPlayer().getHealth() <= 1.0 ) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Not Enough Health.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana() == PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMaxMana()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"That player is already at maximum mana.");
			return false;
		}
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().setHealth(event.getPlayer().getHealth() / 2.0);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
		((Player) target).playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
		PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana() + 750);
		if (PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getCurrentMana()>PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMaxMana()) 
		{
			PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(((Player) target).getUniqueId()).getMaxMana());
		}
		ManaInterface.updateScoreBoard((Player) target);
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
