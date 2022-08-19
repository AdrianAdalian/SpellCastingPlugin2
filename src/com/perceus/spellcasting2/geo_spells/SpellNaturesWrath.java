package com.perceus.spellcasting2.geo_spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellNaturesWrath extends BaseSpellCapsule
{

	public SpellNaturesWrath()
	{
		super(Material.NETHER_STAR, "§r§7§ko§r§7§lSpell: §r§fNature's Wrath§r§7§ko§r", "SpellNaturesWrath", 750, true, true, "§r§fElement: §r§6Geo§r§f.",
				"§r§fThe caster concentrates pure §r§6Geo§r§f energy,",
				"§r§fCausing all wildlife to rapidly decay around the caster.",
				"§r§fRange: 50 square meters.",
				"§r§fMana cost: 750 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		int radius = 50;
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NYLIUM_BREAK, SoundCategory.MASTER, 1, 1);
		
		for (int iy = (radius * -1); iy < (radius * 2); iy++)
		{
			for (int ix = (radius * -1); ix < (radius * 2); ix++)
			{
				for (int iz = (radius * -1); iz < (radius * 2); iz++)
				{
					
					Block target = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().add(new Location(event.getPlayer().getWorld(), ix, iy, iz)));
						
					if (target.getBlockData() != null)
					{
						if (target.getBlockData() instanceof Ageable) // org.bukkit.blockdata.Ageable, not org.bukkit.entity.Ageable
						{	
							Ageable data = (Ageable) target.getBlockData();
							if (data.getMaximumAge() == data.getAge())
							{
								data.setAge(0);
								target.setBlockData(data);
							}
						}
					}	
				}
			}
		}	
		return true;
	}
}