package com.perceus.spellcasting2.fire_spells;

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

public class SpellExpulsion extends BaseSpellCapsule
{

	public SpellExpulsion()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fExpulsion§r§7§ko§r", "SpellExpulsion", 50, true, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §bUtility§f.", 
				"§r§fThe caster concentrates a large pool of","§r§cFire§r§f on whatever block they're looking at.","§r§fRange: 5 meters.",
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
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.MASTER, 1, 1);
		World world = event.getPlayer().getWorld();
		switch (event.getBlockFace()) 
		{
		case UP:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,1,0)).setType(Material.LAVA);
			return true;
		case DOWN:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,-1,0)).setType(Material.LAVA);
			return true;
		case EAST:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(1,0,0)).setType(Material.LAVA);
			return true;
		case NORTH:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,0,-1)).setType(Material.LAVA);
			return true;
		case SOUTH:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,0,1)).setType(Material.LAVA);
			return true;
		case WEST:
			world.getBlockAt(event.getClickedBlock().getLocation().clone().add(-1,0,0)).setType(Material.LAVA);
			return true;
		default:
			return false;	
		}
	}
}
