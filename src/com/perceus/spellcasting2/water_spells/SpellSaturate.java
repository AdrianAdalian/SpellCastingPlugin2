package com.perceus.spellcasting2.water_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSaturate extends BaseSpellCapsule
{

	public SpellSaturate()
	{
		super(Material.LAPIS_LAZULI, "§r§7§ko§r§7§lSpell: §r§fSaturate§r§7§ko§r", "SpellSaturate", 50, true, true, "§r§fElement: §r§9Water§r§f.","§r§fThe caster concentrates a large pool of","§r§9Water§r§f on whatever block they're looking at.","§r§fRange: 5 meters.",
				"§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}	
		
		int TARGETRANGE = 5;
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE);
		
		if (target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
		World world = event.getPlayer().getWorld();
		switch (event.getBlockFace()) 
		{
		case UP:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,1,0)).setType(Material.WATER);
			return true;
		case DOWN:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,-1,0)).setType(Material.WATER);
			return true;
		case EAST:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(1,0,0)).setType(Material.WATER);
			return true;
		case NORTH:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,0,-1)).setType(Material.WATER);
			return true;
		case SOUTH:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,0,1)).setType(Material.WATER);
			return true;
		case WEST:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(-1,0,0)).setType(Material.WATER);
			return true;
		default:
			return false;	
		}
	}

}
