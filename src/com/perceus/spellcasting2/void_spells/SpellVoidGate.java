package com.perceus.spellcasting2.void_spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellVoidGate extends BaseSpellCapsule
{

	public SpellVoidGate()
	{
		super(Material.ENDER_EYE, "§r§7§ko§r§7§lSpell: §r§fVoid Gate§r§7§ko§r", "SpellVoidGate", 500, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §6Utility§f.","§r§fA highly unstable spell containing enough energy","§r§fto grant the caster safe travel between dimensions.","§r§fGate between the End and the Overworld.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		World nether = Bukkit.getWorld("world_the_end");
		
		World overworld = Bukkit.getWorlds().get(0);
		
		int xradius = 75;
		int zradius = 75;

		
		if(event.getPlayer().getWorld().getEnvironment().equals(Environment.NORMAL))
		{
			double baseX = event.getPlayer().getLocation().getX() / 8;
			double baseZ = event.getPlayer().getLocation().getX() / 8;
			for (int ix = xradius * -1; ix < xradius; ix++)
			{
				for (int iz = zradius * -1; iz < zradius; iz++)
				{
					for (int iy = 1; iy < 126; iy++)
					{
						
						Block current = nether.getBlockAt(new Location(nether, baseX + ix, iy, baseZ + iz));
						
						if (current.getType().equals(Material.LAVA) || current.getType().equals(Material.MAGMA_BLOCK) || current.getType().equals(Material.AIR)) 
						{
							continue;
						}
						
						if (current.getRelative(BlockFace.UP).getType().isAir() && current.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType().isAir()) 
						{
							
							Bukkit.getWorld("world_the_end").loadChunk(current.getChunk());
							
							current.setType(Material.END_STONE); 
							current.getRelative(BlockFace.UP).setType(Material.AIR);
							current.getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.AIR);
							
							event.getPlayer().teleport(new Location(nether, current.getLocation().getX(), current.getLocation().getY(), current.getLocation().getZ()).add(0.5, 1, 0.5));
							
							return true;
						  //Teleports the caster from the overworld to the End.
						}
					}
				}
			}
			PrintUtils.sendMessage(event.getPlayer(), "Unsafe Cast Location.");
			return false;
		}
		
		if(event.getPlayer().getWorld().getEnvironment().equals(Environment.THE_END)) 
		{
			Location loc2 = event.getPlayer().getLocation();
			int overworldX = (int) (loc2.getX() * 8);
			int overworldZ = (int) (loc2.getZ() * 8);
			Block target = Bukkit.getWorld("world").getHighestBlockAt(overworldX, overworldZ);
			
			event.getPlayer().teleport(new Location(overworld, overworldX, target.getLocation().getY(), overworldZ).add(0.5, 1, 0.5));
			return true;
				//Teleports the caster from the nether to the End.
		}

		return false;
	}

}
