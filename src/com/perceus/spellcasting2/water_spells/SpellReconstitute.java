package com.perceus.spellcasting2.water_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellReconstitute extends BaseSpellCapsule
{

	public SpellReconstitute()
	{
		super(Material.LIGHT_BLUE_DYE, "§r§7§ko§r§7§lSpell: §r§fReconstitute§r§7§ko§r", "SpellReconstitute", 25, true, true, "§r§fElement: §r§9Water§r§f.","§r§fA spell that allows the caster to","§r§fregenerate health while surrounded by water.","§r§aHeals §r§f3 hearts to self.","§r§fMana cost: 25 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().isSwimming() == false) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"You are not actively swimming.");
			return false;
		}
		if (event.getPlayer().isSwimming() == true) 
		{
			try
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, SoundCategory.MASTER, 1, 1);
				
				event.getPlayer().setHealth(event.getPlayer().getHealth()+6);		
			}
			catch(IllegalArgumentException e)
			{			
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, SoundCategory.MASTER, 1, 1);
				
				event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}
		}	
		
		return true;
	}

}
