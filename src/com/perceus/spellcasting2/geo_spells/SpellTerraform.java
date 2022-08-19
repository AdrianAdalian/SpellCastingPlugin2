package com.perceus.spellcasting2.geo_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellTerraform extends BaseSpellCapsule
{

	public SpellTerraform()
	{
		super(Material.BROWN_DYE, "§r§7§ko§r§7§lSpell: §r§fTerraform§r§7§ko§r", "SpellTerraform", 25, true, true, "§r§fElement: §r§6Geo§r§f.","§r§fThe caster summons a block of dirt from","§r§fconcentrated §r§6Geo§r§f essence.","§r§fRange: 5 meters.","§r§fMana cost: 25 §r§9mana§r§f.");
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
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		
		if (target == null)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GRASS_PLACE, SoundCategory.MASTER, 1, 1);
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				World world = event.getPlayer().getWorld();
				switch (event.getBlockFace()) 
				{
				case UP:
					world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,1,0)).setType(Material.DIRT);
					break;
				case DOWN:
					world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,-1,0)).setType(Material.DIRT);
					break;
				case EAST:
					world.getBlockAt(event.getClickedBlock().getLocation().clone().add(1,0,0)).setType(Material.DIRT);
					break;
				case NORTH:
					world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,0,-1)).setType(Material.DIRT);
					break;
				case SOUTH:
					world.getBlockAt(event.getClickedBlock().getLocation().clone().add(0,0,1)).setType(Material.DIRT);
					break;
				case WEST:
					world.getBlockAt(event.getClickedBlock().getLocation().clone().add(-1,0,0)).setType(Material.DIRT);
					break;
				default:
					break;
				}
			}
		}.runTaskLater(Eden.getInstance(), 1);
		return true;
	}
}
