package com.perceus.spellcasting2.accounts;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.CastListener;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.permissions.PlayerData;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellResetTome extends BaseSpellCapsule
{

	public SpellResetTome()
	{
		super(Material.ENCHANTED_BOOK, "§r§4§ko§r§4§lDeveloper Item: §r§fReset Tome§r§4§ko§r", "SpellResetTome", 0, true, "§r§fA tome containing unknown energy.", "§r§fA mysterious insription is written within that seemingly","§r§fallows the caster to reset all known knowledge of spells.","§r§4§lWARNING§r§f: using this tome is an action that cannot be undone.","§r§fUsing this item will reset all spellbook unlock progress.","§r§c§oDeveloper Item§r§f");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			event.setCancelled(true);
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		PlayerData account = Eden.getPermissionsManager().getPlayerData(event.getPlayer());
		for (String spell : CastListener.spell_registry.keySet()) 
		{
			if (account.getModuleData("unlockedspells").getModuleData().containsKey(spell)) 
			{
				account.getModuleData("unlockedspells").setValue(spell, false);
			}
		}

		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.DRAGON_BREATH, null);
		PrintUtils.sendMessage(event.getPlayer(), "Progress has been reset.");
		return true;
	}
	
}