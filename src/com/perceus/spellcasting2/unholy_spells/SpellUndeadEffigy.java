package com.perceus.spellcasting2.unholy_spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellUndeadEffigy extends BaseSpellCapsule
{

	public SpellUndeadEffigy()
	{
		super(Material.TOTEM_OF_UNDYING, "§r§7§ko§r§7§lSpell: §r§fUndead Effigy§r§7§ko§r", "SpellUndeadEffigy", 500, true, true, "§r§fElement: §r§4§o§lUnholy§r§f.","§r§fHarness §r§4§o§lUnholy§r§f energy into an undead effigy,","§r§fmarking a specific location of rebirth upon death.","§r§fThis effigy, while in hand,","§r§fcan also act as a totem of Undying.","§r§4§lWARNING§r§f: Spellbook will be consumed.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Location loc = event.getPlayer().getLocation();
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 10, Particle.SMOKE_LARGE, null);
		
		event.getPlayer().setBedSpawnLocation(loc, true);
		PrintUtils.sendMessage(event.getPlayer(),"Respawn Location Set.");
		return true;
	}

}
