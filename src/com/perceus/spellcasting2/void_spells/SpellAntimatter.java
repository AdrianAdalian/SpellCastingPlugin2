package com.perceus.spellcasting2.void_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellAntimatter extends BaseSpellCapsule
{

	public SpellAntimatter()
	{
		super(Material.ENDER_EYE, "§r§7§ko§r§7§lSpell: §r§fAntimatter§r§7§ko§r", "SpellAntimatter", 150, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fSummon a small concentration of antimatter","§r§fthat causes an explosion at any target block.","§r§fRange: 30 meters.","§r§fMana cost: 150 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Cast Method.");
			return false;
		}
		
		int TARGETRANGE = 30 ;
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		
		if (target.getType().equals(Material.AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
		target.getWorld().createExplosion(target.getLocation(), 5);
		
		return true;
	}

}
