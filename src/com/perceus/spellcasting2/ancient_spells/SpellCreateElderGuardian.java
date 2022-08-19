package com.perceus.spellcasting2.ancient_spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCreateElderGuardian extends BaseSpellCapsule
{

	public SpellCreateElderGuardian()
	{
		super(Material.END_CRYSTAL, "§r§f§ko§r§e§lAncient Rune§f: Materialize Elder Guardian§r§f§ko§r", "SpellCreateElderGuardian", 0, false, "§r§fElement: §r§eAncient§r§f.","§r§fAn stable mass of §r§eAncient§r§f energy that allows","§r§fthe caster to summon world level entities.","§r§fThis rune will summon an Elder Guardian on target block.","§r§fThe rune will be consumed upon use.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Block target = event.getPlayer().getTargetBlock(null, 10) ;
		
		if (target == null)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
		}
		else
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		}
		
		Location newloc = target.getLocation().add(new Location(target.getWorld(), 0.5, 1, 0.5));
		
		newloc.getWorld().spawnEntity(newloc, EntityType.ELDER_GUARDIAN);
		
		SpellParticles.drawCylinder(newloc, 1, 50, 3, 1, Particle.ENCHANTMENT_TABLE, null);
		SpellParticles.drawDisc(newloc, 1, 1, 30, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.MASTER, 1, 1);
		
		return true;
	}

}
