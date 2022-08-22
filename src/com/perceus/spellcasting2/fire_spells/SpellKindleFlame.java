package com.perceus.spellcasting2.fire_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellKindleFlame extends BaseSpellCapsule
{

	public SpellKindleFlame()
	{
		super(Material.BLAZE_POWDER, "§r§7§ko§r§7§lSpell: §r§fKindle Flame§r§7§ko§r", "SpellKindleFlame", 20, true, true, "§r§fElement: §r§cFire§r§f.","§r§fSpell Type: §bUtility§f.","§r§fIgnites the nearby target block on fire.","§r§fRange: 10 meters.","§r§fMana cost: 20 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getClickedBlock().getType().equals(Material.FIRE) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			event.setCancelled(true);
			return false;
		}
		
		int TARGETRANGE = 10;
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		
		if (target==null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.FLAME, null);
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, SoundCategory.MASTER, 1, 1);
		
		target.getRelative(BlockFace.UP).setType(Material.FIRE);
		return true; 
	}

}
